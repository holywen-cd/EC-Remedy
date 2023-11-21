// DO NOT EDIT THIS BLOCK BELOW=== rest client imports starts ===

import com.cloudbees.flowpdf.Config
import com.cloudbees.flowpdf.Credential
import com.cloudbees.flowpdf.FlowPlugin
import com.cloudbees.flowpdf.Log
import com.cloudbees.flowpdf.client.REST
import com.cloudbees.flowpdf.client.RESTConfig
import com.cloudbees.flowpdf.client.RESTRequest
import groovy.transform.InheritConstructors
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import static groovyx.net.http.ContentType.URLENC

@InheritConstructors
class ECRemedyDynamicTokenRESTClient extends ECRemedyRESTClient{

    private static String BEARER_PREFIX = 'AR-JWT'
    private static String USER_AGENT = 'ECRemedyRESTClient REST Client'
    private static String CONTENT_TYPE = 'application/json'
    private static OAUTH1_SIGNATURE_METHOD = 'RSA-SHA1'

    private String tokenId

    private Log log
    private REST client
    private ProxyConfig proxyConfig

    ECRemedyDynamicTokenRESTClient(String endpoint, RESTConfig restConfig, FlowPlugin plugin, String tokenId) {
        super(endpoint, restConfig, plugin)
        this.endpoint = endpoint
        this.tokenId = tokenId
        this.log = plugin.log
        this.client = new REST(restConfig)
    }

    /**
     * logout from the server and release the token
     */
    def logout(){
        def http = new HTTPBuilder(endpoint)
        http.ignoreSSLIssues()
        http.setHeaders([ 'Authorization': BEARER_PREFIX + ' ' +  tokenId])

        http.request(Method.POST) {
            uri.path = "/api/jwt/logout"
            send URLENC, [:]
            response.success = { resp, body ->
                log.debug resp.statusLine
                //log.debug body
            }
            response.failure = { resp, body ->
                log.info("logout: (ignored) Unable to release auth Token: ${resp.statusLine} ${body}")
            }
        }
    }
    /**
     * Will create a ECRemedyDynamicRESTClient object from the plugin Config object.
     * Convenient as it can use pre-defined configuration fields.
     */
    static ECRemedyDynamicTokenRESTClient fromConfig(Config config, FlowPlugin plugin) {
        Map params = [:]
        String tokenId
        String endpoint = config.getRequiredParameter('endpoint').value.toString()
        Log log = plugin.log
        Credential credential
        RESTConfig restConfig = new RESTConfig()
            .withEndpoint(endpoint)
            .withIgnoreSSLIssues(config.getParameter('ignoreSSLIssues'))

        credential = config.getCredential('basic_credential')

        if (config.isParameterHasValue('httpProxyUrl')) {
            restConfig.withProxyParameters(config.getParameter('httpProxyUrl').value, config.getCredential('proxy_credential'))
        }

        def queryBody = [ username: credential.userName, password: credential.secretValue , authString: config.getParameter('authString').value ]
        log.trace "queryBody: ${queryBody}"
        login(endpoint, queryBody, log, tokenId)

        return new ECRemedyDynamicTokenRESTClient(endpoint, restConfig, plugin, tokenId)
    }

    private static void login(String endpoint, queryBody, log, String tokenId) {
        def http = new HTTPBuilder(endpoint)
        http.ignoreSSLIssues()

        http.request(Method.POST) {
            uri.path = "/api/jwt/login"
            send URLENC, queryBody
            response.success = { resp, body ->
                log.debug resp.statusLine
                log.trace "token: ${body}"
                tokenId = body
            }
            response.failure = { resp, body ->
                throw new Exception("fatal: Unable to get auth Token: ${resp.statusLine} ${body}")
            }
        }
    }

    RESTRequest augmentRequest(RESTRequest request) {
        def newRequest = super.augmentRequest(request)
        newRequest.setHeader('Authorization', BEARER_PREFIX + ' ' + tokenId)
        return newRequest
    }
}