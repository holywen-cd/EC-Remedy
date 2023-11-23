// DO NOT EDIT THIS BLOCK BELOW=== rest client imports starts ===
import com.cloudbees.flowpdf.*
import com.cloudbees.flowpdf.client.Constants
import com.cloudbees.flowpdf.client.RESTRequest
import com.cloudbees.flowpdf.client.REST
import com.cloudbees.flowpdf.client.RESTConfig
import com.cloudbees.flowpdf.client.RESTResponse
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.InheritConstructors
import sun.reflect.generics.reflectiveObjects.NotImplementedException
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== rest client imports ends, checksum: 13f768133315b40c755fd1b028e2f22b ===
// Place for the custom user imports, e.g. import groovy.xml.*
// DO NOT EDIT THIS BLOCK BELOW=== rest client starts ===
@InheritConstructors
class InvalidRestClientException extends Exception {

}

class ProxyConfig {
    String url
    String userName
    String password
}

class ECRemedyRESTClient {

    private static String BEARER_PREFIX = 'Bearer'
    private static String USER_AGENT = 'ECRemedyRESTClient REST Client'
    private static String CONTENT_TYPE = 'application/json'
    private static OAUTH1_SIGNATURE_METHOD = 'RSA-SHA1'

    String endpoint
    String procedureName
    Map<String, String> procedureParameters

    private Log log
    private REST client
    private ProxyConfig proxyConfig

    ECRemedyRESTClient(String endpoint, RESTConfig restConfig, FlowPlugin plugin) {
        this.endpoint = endpoint
        this.log = plugin.log
        this.client = new REST(restConfig)
    }

    /**
     * Will create a ECRemedyRESTClient object from the plugin Config object.
     * Convenient as it can use pre-defined configuration fields.
     */
    static ECRemedyRESTClient fromConfig(Config config, FlowPlugin plugin) {
        Map params = [:]
        String endpoint = config.getRequiredParameter('endpoint').value.toString()
        Log log = plugin.log
        Credential credential
        RESTConfig restConfig = new RESTConfig()
            .withEndpoint(endpoint)
        if ((credential = config.getCredential('bearer_credential')) && credential.secretValue) {
            if (!credential.userName)
                credential.userName = BEARER_PREFIX
            restConfig.withCredentialForScheme('bearer', credential)
            log.debug "Using bearer credential in REST Client"
        } else if ((credential = config.getCredential('basic_credential'))) {
            restConfig.withCredentialForScheme('basic', credential)
        } else if (config.isParameterHasValue('authScheme') && config.getParameter('authScheme').value == 'anonymous') {
            log.debug "Using anonymous auth scheme"
            restConfig.withAuthScheme('anonymous')
        } else {
            restConfig.withAuthScheme('anonymous')
        }

        if (config.isParameterHasValue('httpProxyUrl')) {
            restConfig.withProxyParameters(config.getParameter('httpProxyUrl').value, config.getCredential('proxy_credential'))
        }

        return new ECRemedyRESTClient(endpoint, restConfig, plugin)
    }

    // Handles templates like , taking values from the params
    private static String renderOneLineTemplate(String uri, Map params) {
        for (String key in params.keySet()) {
            Object value = params.get(key)
            if (uri =~ /\{\{$key\}\}/) {
                if (value) {
                    uri = uri.replaceAll(/\{\{$key\}\}/, value as String)
                } else {
                    throw new InvalidRestClientException("A field $key is empty in params but required in the template")
                }
            }
        }
        return uri
    }

    /**
     * This is the main request method
     * requestMethod - GET|POST|PUT - request method
     * pathUrl - uri path (without the endpoint)
     * queries - uri.query
     * payload - payload for POST/PUT requests
     * headers - headers for the request
     */
    Object makeRequest(String requestMethod, String pathUrl, Map queries, def payload, Map headers) {

        RESTRequest restRequest = new RESTRequest()
            .withRequestMethod(requestMethod)
            .withPathUrl(pathUrl)
            .withQueries(queries)
            .withHeaders(headers) as RESTRequest

        if (payload) {
            if (payload instanceof byte[]) {
                restRequest.withContentBytes(payload)
            } else {
                restRequest.withContentString(encodePayload(payload))
            }
        }

        if ((restRequest.requestMethod == "POST") || (restRequest.requestMethod == "PUT") || (restRequest.requestMethod == "PATCH")) {
            if (!restRequest.requestContentType) {
                restRequest.requestContentType = Constants.CT_JSON
            }
        }

        RESTResponse restResponse = client.doRequest(augmentRequest(client.prepareRequest(restRequest)), true)

        Object body = restResponse.content

        Object processedResponse = processResponse(restResponse, body)
        if (processedResponse) {
            return processedResponse
        }
        Object parsed = parseResponse(restResponse, body)
        return parsed
    }

    private static payloadFromTemplate(String template, Map params) {
        Object object = new JsonSlurper().parseText(template)
        object = fillFields(object, params)
        return object
    }

    private static def fillFields(def o, Map params) {
        def retval
        if (o instanceof Map) {
            retval = [:]
            for (String key in o.keySet()) {
                key = renderOneLineTemplate(key, params)
                def value = o.get(key)
                if (value instanceof String) {
                    value = renderOneLineTemplate(value, params)
                } else {
                    value = fillFields(value, params)
                }
                retval.put(key, value)
            }
        } else if (o instanceof List) {
            retval = []
            for (def i in o) {
                i = fillFields(i, params)
                retval.add(i)
            }
        } else if (o instanceof String) {
            o = renderOneLineTemplate(o, params)
            retval = o
        } else if (o instanceof Integer || o instanceof Boolean) {
            retval = o
        } else {
            throw new NotImplementedException()
        }
        return retval
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/CHG:ChangeInterface_Create
    * Do not change this code
    */
    def createChangeRequest(Map<String, Object> params) {
        this.procedureName = 'createChangeRequest'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/CHG:ChangeInterface_Create'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('POST', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/CHG:ChangeInterface_Create/{{Entry ID}}
    * Do not change this code
    * Entry ID: in path
    */
    def updateChangeRequest(Map<String, Object> params) {
        this.procedureName = 'updateChangeRequest'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/CHG:ChangeInterface_Create/{{Entry ID}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('PUT', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/CHG:ChangeInterface_Create/{{Entry ID}}
    * Do not change this code
    * Entry ID: in path
    */
    def getChangeRequest(Map<String, Object> params) {
        this.procedureName = 'getChangeRequest'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/CHG:ChangeInterface_Create/{{Entry ID}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('GET', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/{{Form Name}}
    * Do not change this code
    * Form Name: in path
    * Values: in raw body
    */
    def createEntry(Map<String, Object> params) {
        this.procedureName = 'createEntry'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/{{Form Name}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        payload = new JsonSlurper().parseText(params.get('Values'))
        log.debug "Raw body payload: $payload"

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('POST', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/{{Form Name}}/{{Entry ID}}
    * Do not change this code
    * Form Name: in path
    * Entry ID: in path
    * Values: in raw body
    */
    def updateEntry(Map<String, Object> params) {
        this.procedureName = 'updateEntry'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/{{Form Name}}/{{Entry ID}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        payload = new JsonSlurper().parseText(params.get('Values'))
        log.debug "Raw body payload: $payload"

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('PUT', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/{{Form Name}}/{{Entry ID}}
    * Do not change this code
    * Form Name: in path
    * Entry ID: in path
    */
    def getEntry(Map<String, Object> params) {
        this.procedureName = 'getEntry'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/{{Form Name}}/{{Entry ID}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('GET', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/HPD:IncidentInterface_Create
    * Do not change this code
    */
    def createIncident(Map<String, Object> params) {
        this.procedureName = 'createIncident'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/HPD:IncidentInterface_Create'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('POST', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/HPD:IncidentInterface_Create/{{Entry ID}}
    * Do not change this code
    * Entry ID: in path
    */
    def updateIncident(Map<String, Object> params) {
        this.procedureName = 'updateIncident'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/HPD:IncidentInterface_Create/{{Entry ID}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('PUT', uri, query, payload, headers)
    }

    /** Generated code for the endpoint /api/arsys/v1/entry/HPD:IncidentInterface_Create/{{Entry ID}}
    * Do not change this code
    * Entry ID: in path
    */
    def getIncident(Map<String, Object> params) {
        this.procedureName = 'getIncident'
        this.procedureParameters = params

        String uri = '/api/arsys/v1/entry/HPD:IncidentInterface_Create/{{Entry ID}}'
        log.debug("URI template $uri")
        uri = renderOneLineTemplate(uri, params)

        Map query = [:]

        log.debug "Query: ${query}"

        Object payload

        String jsonTemplate = ''''''
        if (jsonTemplate) {
            payload = payloadFromTemplate(jsonTemplate, params)
            log.debug("Payload from template: $payload")
        }
        //TODO clean empty fields
        Map headers = [:]
        return makeRequest('GET', uri, query, payload, headers)
    }
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== rest client ends, checksum: 5588d99c09d57d10eebe7215badf3164 ===
    /**
     * Use this method for any request pre-processing: adding custom headers, binary files, etc.
     */
    RESTRequest augmentRequest(RESTRequest request) {
        if(procedureName == 'createChangeRequest'  || procedureName == 'createIncident' ) {
            request.setQuery('fields', 'values(Infrastructure Change Id)')
        }

        if(procedureName == 'createChangeRequest' || procedureName == 'updateChangeRequest' \
            || procedureName == 'createIncident' || procedureName == 'updateIncident' ) {
            request.withContentString(JsonOutput.toJson(procedureParameters.payload))
            log.trace("payload: ${request.contentString}")
        }
        return request
    }

    /**
     * Use this method to provide a custom encoding for you payload (XML, yaml etc)
     */
    String encodePayload(def payload) {
        return JsonOutput.toJson(payload)
    }

    /**
     * Use this method to parse/alter response from the server
     */
    def parseResponse(RESTResponse restResponse, Object body) {
        //Relying on http builder content type processing
        if(body != '') {
            def slurper = new JsonSlurper()
            log.trace("Response body: ${body}")
            return slurper.parseText(body)
        }
        return body
    }

    /**
     * Use this method to alter default server response processing.
     * The response from this method will be returned as is, if any.
     * To disable response, just return null.
     */
    def processResponse(RESTResponse restResponse, Object body) {
        return null
    }

}