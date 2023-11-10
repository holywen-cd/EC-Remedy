EC-Remedy Plugin development how to

EC-Remedy
---

Revised on Tue Nov 08 16:53:47 ICT 2023

* * *

Contents
- [EC-Remedy](#EC-Remedy)
- [Overview](#overview)
- [Development Tool Setup](#development-tool-setup)
- [PDK development links](#pdk-development-links)
- [Development](#development)
- [Plugin troubleshooting](#plugin-troubleshooting)

## Overview

Integrate with Remedy REST [API](https://docs.bmc.com/docs/ars1902/api-overview-836458102.html)

## Development Tool Setup
* Install PDK
    * get pdk from: [PDK download](https://docs.cloudbees.com/docs/cloudbees-cd-pdk/latest/pdk/pdk)
    * add pdk to your PATH
* Choose your favorate IDE
    * VS Code or IDEA are OK

## PDK development links
* [Latest PDK document](https://docs.cloudbees.com/docs/cloudbees-cd-pdk/latest/pdk/)
* [EC-Remedy Plugin repo](https://alm-github.systems.uk.XXXX/cbcdflow/aro-EC-Remedy-plugin)

## Development
* Understand the authentication of Remedy api:
    * As documented [here](https://docs.bmc.com/docs/ars1902/authentication-and-permissions-in-the-rest-api-847208964.html), user will need to get a token and then use that token for api calls
    * So we implemented ECRemedyDynamicTokenRESTClient class for this process
      In fromConfig function, we made a query to get the tokenId before create the ECRemedyDynamicTokenRESTClient, and ECRemedyDynamicTokenRESTClient will use this tokenId for authentication in the following api calls

      ```
      def queryBody = [ username: credential.userName, password: credential.secretValue , authString: config.getParameter('authString').value ]
      log.trace "queryBody: ${queryBody}"
      login(endpoint, queryBody, log, tokenId)
      ```
* How to add some new api implementation
    1. check out code from: [EC-Remedy Plugin repo](https://alm-github.systems.uk.XXXX/cbcdflow/aro-EC-Remedy-plugin)
    2. update pluginspec.yaml
  For example: if we want to add a new api implementation, we need to first update the pluginspec.yaml in the config folder.
  since there are already some existing api implemented, you can copy one of those from the procedures section and then modify the information accordingly.

3. run command in code base dir

    ```
    pdk generate plugin
    ```

4. check ECRemedy.groovy and ECRemedyRESTClient.groovy, the new function of the new api call should have been added to these two files by pdk
5. update the new function in ECRemedy.groovy file, in the new function generated code block, replace `genECRemedyRESTClient` with `ECRemedyDynamicTokenRESTClient` because we need to use the ECRemedyDynamicTokenRESTClient class for the authentication
6. make changes to the generated new function to add extra logic to handle the response of the api call
7. download dependencies:

   ```
   gradle task copyDependencies
   ```

8. run command

    ```
    pdk build
    ```

9. find the built plugin from build\EC-Remedy.zip
10. you can install the plugin by installing the zip file to CDRO instance.

## Plugin troubleshooting
* Enable debug or trace logs
    1. Login to the web interface
    2. Go to left side nagivation bar ->  DevOps Essenntials -> Plugin Management -> Plugin configurations
    3. Find the plugin configuration you are used for task, and edit it
    4. On the editing page, find "Debug Level:" field, change the value to "debug" or "trace"
    5. Click OK to save
    6. run the task again, you should able to see more detailed log printings.