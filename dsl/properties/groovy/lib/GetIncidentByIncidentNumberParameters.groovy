
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class GetIncidentByIncidentNumberParameters {
    /**
    * Label: Incident Number, type: entry
    */
    String incidentNumber
    /**
    * Label: , type: entry
    */
    String resultPropertyPath

    static GetIncidentByIncidentNumberParameters initParameters(StepParameters sp) {
        GetIncidentByIncidentNumberParameters parameters = new GetIncidentByIncidentNumberParameters()

        def incidentNumber = sp.getRequiredParameter('Incident Number').value
        parameters.incidentNumber = incidentNumber

        def resultPropertyPath = sp.getParameter('resultPropertyPath').value
        parameters.resultPropertyPath = resultPropertyPath  ?: '/myJob/entriesList'

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: ca4317355901b3987718e66e38666e76 ===
