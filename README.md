EC-Remedy Plugin

EC-Remedy



Plugin version 2.0.0

Revised on Fri Nov 24 16:46:18 ICT 2023


* * *


Contents



*   [Overview](#overview)
*   [Plugin Configurations](#plugin-configurations)
*   [Plugin Procedures](#plugin-procedures)
    *   [Create Change Request](#create-change-request)
    *   [Update Change Request](#update-change-request)
    *   [Get Change Request](#get-change-request)
    *   [Get Change Request by Infrastructure Change Id](#get-change-request-by-infrastructure-change-id)
    *   [Create Entry](#create-entry)
    *   [Update Entry](#update-entry)
    *   [Get Entry](#get-entry)
    *   [Create Incident](#create-incident)
    *   [Update Incident](#update-incident)
    *   [Get Incident](#get-incident)
    *   [Get Incident By Incident Number](#get-incident-by-incident-number)
    *   [Get Service Request By Request Number](#get-service-request-by-request-number)
    *   [Wait for Change Request Window and Approval](#wait-for-change-request-window-and-approval)

## Overview


Cloudbees CD integration for the <a href="https://docs.bmc.com/docs/ars1902/integrating-ar-system-forms-with-a-third-party-application-by-using-rest-api-846052091.html">BMC Remedy System REST API</a>




## Plugin Configurations

Plugin configurations are sets of parameters that can be applied across some, or all, of the plugin procedures. They can reduce the repetition of common values, create predefined parameter sets, and securely store credentials. Each configuration is given a unique name that is entered in the designated parameter for the plugin procedures that use them.  

### Creating Plugin Configurations

*   To create plugin configurations in CloudBees CD/RO, complete the following steps:
*   Navigate to DevOps Essentials  Plugin Management  Plugin configurations.
*   Select Add plugin configuration to create a new configuration.
*   In the New Configuration window, specify a Name for the configuration.
*   Select the Project that the configuration belongs to.
*   Optionally, add a Description for the configuration.
*   Select the appropriate Plugin for the configuration.
*   Configure the parameters per the descriptions below.

Configuration Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Unique name for the configuration |
| Description | Configuration description |
| **Remedy REST Endpoint** | REST API Endpoint |
| Debug Level | This option sets debug level for logs. If info is selected, only summary information will be shown, for debug, there will be some debug information and for trace the whole requests and responses will be shown. |

## Plugin Procedures

**IMPORTANT** Note that the names of **Required** parameters are marked in *bold** in the parameter description table for each procedure.




## Create Change Request

Create Remedy Change Request

### Create Change Request Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Description** | An change request description. |
| **Status** | An change request status. |
| **Urgency** | An change request urgency. |
| **Impact** | An change request impact. |
| **First Name** | An change request first name. |
| **Last Name** | An change request last name. |
| **Location Company** | An change request location company. |
| **Values** | Fields for change request (JSON object). e.g.<br>{<br>  "First Name": "Allen",<br>  "Last Name": "Allbrook"<br>}<br> |
| resultPropertyPath | The property path to store the result of the procedure.<br> |


#### Output Parameters

| Parameter | Description |
| --- | --- |
| Change_Entry_ID | Entry ID of the created change request. |
| Infrastructure Change Id | Infrastructure Change Id of the created change request. |



## Update Change Request

Update Remedy Change Request

### Update Change Request Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Entry ID** | Entry ID of a change request. |
| Description | An change request description. |
| Status | An change request status. |
| Urgency | An change request urgency. |
| Impact | An change request impact. |
| First Name | An change request first name. |
| Last Name | An change request last name. |
| Location Company | An change request location company. |
| Values | Fields for change request (JSON object). e.g.<br>{<br>  "First Name": "Allen",<br>  "Last Name": "Allbrook"<br>}<br> |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Get Change Request

Get Remedy Change Request

### Get Change Request Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Entry ID** | Entry ID of a change request. |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Get Change Request by Infrastructure Change Id

Get Remedy Change Request by Infrastructure Change Id

### Get Change Request by Infrastructure Change Id Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Infrastructure Change Id** | Infrastructure Change Id of a change request. |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Create Entry

Create Remedy Entry

### Create Entry Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Form Name** | Remedy form name, e.g. RMS:ReleaseInterface_Create |
| **Values** | Fields for entry (JSON object), e.g.<br>{<br>  "values": {<br>    "First Name": "Allen",<br>    "Last Name": "Allbrook",<br>    "Company": "Calbro Services",<br>    "Milestone": "Initiate",<br>    "Impact": "1-Extensive/Widespread",<br>    "Urgency": "1-Critical",<br>    "Risk Level": "Risk Level 1",<br>    "Description": "Test Created Release",<br>    "Location Company": "Calbro Services",<br>    "Business Justification": "Defect"<br>  }<br>}<br> |
| resultPropertyPath | The property path to store the result of the procedure.<br> |


#### Output Parameters

| Parameter | Description |
| --- | --- |
| entryId | Entry ID of the created entry. |



## Update Entry

Update Remedy Entry

### Update Entry Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Form Name** | Remedy form name, e.g. RMS:ReleaseInterface_Create |
| **Entry ID** | Remedy entry ID |
| **Values** | Fields for entry (JSON object), e.g.<br>{<br>  "values":{<br>    "Submitter":"Allen",<br>    "Short Description":"testing 123"<br>  }<br>}<br> |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Get Entry

Fetches Remedy entry with the specified form name and entry ID

### Get Entry Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Form Name** | Remedy form name, e.g. RMS:ReleaseInterface_Create |
| **Entry ID** | Remedy entry ID |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Create Incident

Create Remedy Incident

### Create Incident Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Description** | An incident description. |
| **Status** | An incident status. |
| **Urgency** | An incident urgency. |
| **Impact** | An incident impact. |
| **First Name** | An incident first name. |
| **Last Name** | An incident last name. |
| **Service_Type** | An change request service type. |
| **Reported Source** | An change request reported source. |
| **Values** | Fields for change request (JSON object).<br> |
| resultPropertyPath | The property path to store the result of the procedure.<br> |


#### Output Parameters

| Parameter | Description |
| --- | --- |
| entryId | Entry ID of the created incident. |



## Update Incident

Update Remedy Incident

### Update Incident Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Entry ID** | Entry ID of a change request. |
| Description | An incident description. |
| Status | An incident status. |
| Urgency | An incident urgency. |
| Impact | An incident impact. |
| First Name | An incident first name. |
| Last Name | An incident last name. |
| Values | Fields for change request (JSON object).<br> |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Get Incident

Get Remedy incident details

### Get Incident Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Entry ID** | Entry ID of a change request. |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Get Incident By Incident Number

Get Remedy Incident Details By Incident Number

### Get Incident By Incident Number Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Incident Number** | Incident Number of a incident. |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Get Service Request By Request Number

Get Remedy Service Request Details By Request Number

### Get Service Request By Request Number Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Request Number** | Request Number of a Service Request. |
| resultPropertyPath | The property path to store the result of the procedure.<br> |



## Wait for Change Request Window and Approval

Wait for Change Request Time Window and Approval

### Wait for Change Request Window and Approval Parameters

| Parameter | Description |
| --- | --- |
| **Configuration Name** | Previously defined configuration for the plugin |
| **Infrastructure Change Id** | Infrastructure Change Id of a change request. |
