
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class GetChangeRequestByInfrastructureChangeIdParameters {
    /**
    * Label: Infrastructure Change Id, type: entry
    */
    String infrastructureChangeId
    /**
    * Label: , type: entry
    */
    String resultPropertyPath

    static GetChangeRequestByInfrastructureChangeIdParameters initParameters(StepParameters sp) {
        GetChangeRequestByInfrastructureChangeIdParameters parameters = new GetChangeRequestByInfrastructureChangeIdParameters()

        def infrastructureChangeId = sp.getRequiredParameter('Infrastructure Change Id').value
        parameters.infrastructureChangeId = infrastructureChangeId

        def resultPropertyPath = sp.getParameter('resultPropertyPath').value
        parameters.resultPropertyPath = resultPropertyPath  ?: '/myJob/entriesList'

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: 8c0f3a8ed3b19c1e747ee5cc484ee6a9 ===