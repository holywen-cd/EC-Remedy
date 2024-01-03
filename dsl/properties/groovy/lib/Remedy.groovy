import sun.reflect.generics.reflectiveObjects.NotImplementedException
import com.cloudbees.flowpdf.components.ComponentManager

import com.cloudbees.flowpdf.*
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import java.text.SimpleDateFormat
import groovy.time.*

/**
* Remedy
*/
class Remedy extends FlowPlugin {

    @Override
    Map<String, Object> pluginInfo() {
        return [
                pluginName     : '@PLUGIN_KEY@',
                pluginVersion  : '@PLUGIN_VERSION@',
                configFields   : ['config'],
                configLocations: ['ec_plugin_cfgs'],
                defaultConfigValues: [:]
        ]
    }
// === check connection ends ===

/**
     * This method returns REST Client object
     */
    ECRemedyRESTClient genECRemedyRESTClient() {
        Context context = getContext()
        ECRemedyRESTClient rest = ECRemedyRESTClient.fromConfig(context.getConfigValues(), this)
        return rest
    }

    ECRemedyRESTClient genECRemedyDynamicTokenRESTClient() {
        Context context = getContext()
        ECRemedyRESTClient rest = ECRemedyDynamicTokenRESTClient.fromConfig(context.getConfigValues(), this)
        return rest
    }
/**
     * Auto-generated method for the procedure Create Change Request/Create Change Request
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Description* Parameter: Status* Parameter: Urgency* Parameter: Impact* Parameter: First Name* Parameter: Last Name* Parameter: Location Company* Parameter: values* Parameter: resultPropertyPath
     */
    def createChangeRequest(StepParameters p, StepResult sr) {
        CreateChangeRequestParameters sp = CreateChangeRequestParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap

        def requiredParams = ['Description', 'Status', 'Urgency', 'Impact', 'First Name', 'Last Name', 'Location Company']
        def optionalParams = []
        def payload = preparePayload(requiredParams, optionalParams, requestParams, 'CREATE')
        restParams.put('payload', payload)

        try {
            Object response = rest.createChangeRequest(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.setOutputParameter('Infrastructure Change Id', response.values.get('Infrastructure Change Id'))
            sr.setOutputParameter('Change_Entry_ID', response.values.get('Change_Entry_ID'))

            sr.apply()
            log.info "Create Change Request completed successfully"
        } finally {
            rest.logout()
        }

    }

    private Map preparePayload(ArrayList<String> requiredParams, ArrayList<String> optionalParams, Map<String, Object> requestParams, String action) {
        Map payload = [:]
        Map values = [:]
        requiredParams?.each { param ->
            values.put(param, requestParams.get(param))
        }
        optionalParams?.each { param ->
            if (requestParams.get(param) != null && requestParams.get(param) != '')
                values.put(param, requestParams.get(param))
        }
        JsonSlurper slurper = new JsonSlurper()
        def paramValues = slurper.parseText(requestParams.get('Values'))
        paramValues.each { key, value ->
            if (value != null && value != '')
                values.put(key, value)
        }
        if (!values.z1D_Action) {
            values.put("z1D_Action", action)
        }
        payload.put("values", values)
        return payload
    }
/**
     * Auto-generated method for the procedure Update Change Request/Update Change Request
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Entry ID* Parameter: Description* Parameter: Status* Parameter: Urgency* Parameter: Impact* Parameter: First Name* Parameter: Last Name* Parameter: Location Company* Parameter: Values* Parameter: resultPropertyPath
     */
    def updateChangeRequest(StepParameters p, StepResult sr) {
        UpdateChangeRequestParameters sp = UpdateChangeRequestParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Entry ID', requestParams.get('Entry ID'))

        def requiredParams = []
        def optionalParams = ['Description', 'Status', 'Urgency', 'Impact', 'First Name', 'Last Name', 'Location Company']
        def payload = preparePayload(requiredParams, optionalParams, requestParams, 'MODIFY')
        restParams.put('payload', payload)

        try {
            Object response = rest.updateChangeRequest(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Update Change Request completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Get Change Request/Get Change Request
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Entry ID* Parameter: resultPropertyPath
     */
    def getChangeRequest(StepParameters p, StepResult sr) {
        GetChangeRequestParameters sp = GetChangeRequestParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Entry ID', requestParams.get('Entry ID'))

        try {
            Object response = rest.getChangeRequest(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Get Change Request completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Create Entry/Create Entry
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Form Name* Parameter: Values* Parameter: resultPropertyPath
     */
    def createEntry(StepParameters p, StepResult sr) {
        CreateEntryParameters sp = CreateEntryParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Form Name', requestParams.get('Form Name'))
        restParams.put('Values', requestParams.get('Values'))

        try {
            Object response = rest.createEntry(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.setOutputParameter('entryId', response.entryId)
            sr.apply()
            log.info "Create Entry completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Update Entry/Update Entry
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Form Name* Parameter: Entry ID* Parameter: Values* Parameter: resultPropertyPath
     */
    def updateEntry(StepParameters p, StepResult sr) {
        UpdateEntryParameters sp = UpdateEntryParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Form Name', requestParams.get('Form Name'))
        restParams.put('Entry ID', requestParams.get('Entry ID'))
        restParams.put('Values', requestParams.get('Values'))

        try {
            Object response = rest.updateEntry(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Update Entry completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Get Entry/Get Entry
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Form Name* Parameter: Entry ID* Parameter: resultPropertyPath
     */
    def getEntry(StepParameters p, StepResult sr) {
        GetEntryParameters sp = GetEntryParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Form Name', requestParams.get('Form Name'))
        restParams.put('Entry ID', requestParams.get('Entry ID'))

        try {
            Object response = rest.getEntry(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Get Entry completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Create Incident/Create Incident
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Description* Parameter: Status* Parameter: Urgency* Parameter: Impact* Parameter: First Name* Parameter: Last Name* Parameter: Service Type* Parameter: Reported Source* Parameter: Values* Parameter: resultPropertyPath
     */
    def createIncident(StepParameters p, StepResult sr) {
        CreateIncidentParameters sp = CreateIncidentParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap

        def requiredParams = ['Description', 'Status', 'Urgency', 'Impact', 'First Name', 'Last Name', 'Service_Type', 'Reported Source']
        def optionalParams = []
        def payload = preparePayload(requiredParams, optionalParams, requestParams, 'CREATE')
        restParams.put('payload', payload)

        try {
            Object response = rest.createIncident(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.setOutputParameter('entryId', response.entryId)
            sr.apply()
            log.info "Create Incident completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Update Incident/Update Incident
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Entry ID* Parameter: Description* Parameter: Status* Parameter: Urgency* Parameter: Impact* Parameter: First Name* Parameter: Last Name* Parameter: Values* Parameter: resultPropertyPath
     */
    def updateIncident(StepParameters p, StepResult sr) {
        UpdateIncidentParameters sp = UpdateIncidentParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Entry ID', requestParams.get('Entry ID'))

        def requiredParams = []
        def optionalParams = ['Description', 'Status', 'Urgency', 'Impact', 'First Name', 'Last Name']
        def payload = preparePayload(requiredParams, optionalParams, requestParams, 'MODIFY')
        restParams.put('payload', payload)

        try {
            Object response = rest.updateIncident(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Update Incident completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Get Incident/Get Incident
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Entry ID* Parameter: resultPropertyPath
     */
    def getIncident(StepParameters p, StepResult sr) {
        GetIncidentParameters sp = GetIncidentParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Entry ID', requestParams.get('Entry ID'))

        try {
            Object response = rest.getIncident(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Get Incident completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
    * waitForChangeRequestWindowAndApproval - Wait for Change Request Window and Approval/Wait for Change Request Window and Approval
    * Add your code into this method and it will be called when the step runs
    * @param config (required: true)
    * @param Entry ID (required: true)
    
    */
    def waitForChangeRequestWindowAndApproval(StepParameters p, StepResult sr) {
        WaitForChangeRequestWindowAndApprovalParameters sp = WaitForChangeRequestWindowAndApprovalParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Infrastructure Change Id', requestParams.get('Infrastructure Change Id'))
        def pollingInterval = sp.pollingInterval * 1000
        def notApprovedStates = ['Draft', 'Planning In Progress', 'Scheduled For Approval']
        def approvedStates = ['Scheduled', 'Implementation In Progress']

        try {
            Object response = rest.getChangeRequestByInfrastructureChangeId(restParams)
            log.info "Got rest.getChangeRequestByInfrastructureChangeId response from server: ${JsonOutput.toJson(response)}"

            def scheduledStartDate = response.entries[0].values.get('Scheduled Start Date')
            def scheduledEndDate = response.entries[0].values.get('Scheduled End Date')

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            def strtDate = sdf.parse(scheduledStartDate)
            def currentDate = new Date()
            long startTimeInSec = sdf.parse(scheduledStartDate).getTime() / 1000
            long currentTimeInSec = currentDate.getTime() / 1000
            long endTimeInSec = sdf.parse(scheduledEndDate).getTime() / 1000

            long timeDiffInSec = startTimeInSec - currentTimeInSec
            log.debug "timeDiffInSec: $timeDiffInSec"

            if(timeDiffInSec > 20) {
                long timeToSleepInSec = timeDiffInSec - 10
                TimeDuration duration = TimeCategory.minus(strtDate, currentDate)

                log.info "Change Request is not in window yet. Waiting for $duration"
                sleep(timeDiffInSec.intValue() * 1000)
            }

            log.info "Waiting for Change Request to be in approved status"
            //lets get the latest status
            response = rest.getChangeRequestByInfrastructureChangeId(restParams)
            log.debug "Got rest.getChangeRequestByInfrastructureChangeId response from server: ${JsonOutput.toJson(response)}"
            def status = response.entries[0].values.get("Change Request Status")
            while(status in notApprovedStates) {
                log.debug  "status (at the start of wait loop): $status"
                sleep pollingInterval
                response = rest.getChangeRequestByInfrastructureChangeId(restParams)
                print '.'
                log.debug "Got rest.getChangeRequestByInfrastructureChangeId response from server: ${JsonOutput.toJson(response)}"
                status = response.entries[0].values.get("Change Request Status")
                log.debug "status (at the end of wait loop): $status"
            }

            response = rest.getChangeRequestByInfrastructureChangeId(restParams)
            log.debug "Got rest.getChangeRequestByInfrastructureChangeId response from server: ${JsonOutput.toJson(response)}"
            status = response.entries[0].values.get("Change Request Status")
            if(status == 'Cancelled') {
                throw new Exception("Change Request is cancelled, Please raise a new Change Request")
            }
            log.debug 'status: ' + status
            log.info "Change Request is in approved status"
            sr.apply()
            log.info("step Wait for Change Request Window and Approval has been finished")
        } finally {
            rest.logout()
        }
    }

/**
    * Procedure parameters:
    * @param config
    * @param queryString
    * @param previewMode
    * @param transformScript
    * @param debug
    * @param releaseName
    * @param releaseProjectName
    
    */
    def collectReportingData(StepParameters paramsStep, StepResult sr) {
        def params = paramsStep.getAsMap()

        throw new NotImplementedException()

        if (params['debug']) {
            log.setLogLevel(log.LOG_DEBUG)
        }

        
        Reporting reporting = (Reporting) ComponentManager.loadComponent(ReportingRemedy.class, [
                reportObjectTypes  : ['incident'],
                metadataUniqueKey  : params['queryString'],
                payloadKeys        : ['fill me in'],
        ], this)
        reporting.collectReportingData()
        
    }
/**
     * Auto-generated method for the procedure Get Change Request by Infrastructure Change Id/Get Change Request by Infrastructure Change Id
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Infrastructure Change Id* Parameter: resultPropertyPath
     */
    def getChangeRequestByInfrastructureChangeId(StepParameters p, StepResult sr) {
        GetChangeRequestByInfrastructureChangeIdParameters sp = GetChangeRequestByInfrastructureChangeIdParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Infrastructure Change Id', requestParams.get('Infrastructure Change Id'))

        try {
            Object response = rest.getChangeRequestByInfrastructureChangeId(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Get Change Request by Infrastructure Change Id completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Get Incident By Incident Number/Get Incident By Incident Number
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Incident Number* Parameter: resultPropertyPath
     */
    def getIncidentByIncidentNumber(StepParameters p, StepResult sr) {
        GetIncidentByIncidentNumberParameters sp = GetIncidentByIncidentNumberParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Incident Number', requestParams.get('Incident Number'))

        try {
            Object response = rest.getIncidentByIncidentNumber(restParams)
            log.info "Got response from server: ${JsonOutput.toJson(response)}"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info "Get Incident By Incident Number completed successfully"
        } finally {
            rest.logout()
        }
    }
/**
     * Auto-generated method for the procedure Get Service Request By Request Number/Get Service Request By Request Number
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: Request Number* Parameter: resultPropertyPath
     */
    def getServiceRequestByRequestNumber(StepParameters p, StepResult sr) {
        GetServiceRequestByRequestNumberParameters sp = GetServiceRequestByRequestNumberParameters.initParameters(p)
        ECRemedyRESTClient rest = genECRemedyRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('Request Number', requestParams.get('Request Number'))

        try {
            Object response = rest.getServiceRequestByRequestNumber(restParams)
            log.info "Got response from server: $response"

            def resultPropertyPath = requestParams.get('resultPropertyPath')
            if(resultPropertyPath) {
                def result = JsonOutput.toJson(response)
                log.info "Setting step result property $resultPropertyPath to: $result"
                sr.setOutcomeProperty(resultPropertyPath, result)
            }

            sr.apply()
            log.info("step Get Service Request By Request Number has been finished")
        } finally {
            rest.logout()
        }
    }
/**
    * checkServiceRequestStatus - Check Service Request Status/Check Service Request Status
    * Add your code into this method and it will be called when the step runs
    * @param config (required: true)
    * @param Request Number (required: true)
    * @param Statuses (required: true)
    
    */
    def checkServiceRequestStatus(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        CheckServiceRequestStatusParameters sp = CheckServiceRequestStatusParameters.initParameters(p)
        ECRemedyDynamicTokenRESTClient rest = genECRemedyDynamicTokenRESTClient()

        Map restParams = [:]
        Map requestParams = p.asMap
        def Statuses = requestParams.get('Statuses').split ( ',' )

        restParams.put('Request Number', requestParams.get('Request Number'))

        try {
            Object response = rest.getServiceRequestByRequestNumber(restParams)
            log.info "Got response from server: $response"

            def status = response.entries[0].values.get('Status')
            if(status in Statuses) {
                log.info "Service Request is in $status status"
            } else {
                throw new Exception("Service Request is not in $Statuses status")
            }

            sr.apply()
            log.info("step Check Service Request Status has been finished")
        } finally {
            rest.logout()
        }

    }

// === step ends ===

}