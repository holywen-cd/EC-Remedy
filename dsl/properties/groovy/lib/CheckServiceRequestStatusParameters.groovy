
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class CheckServiceRequestStatusParameters {
    /**
    * Label: Request Number, type: entry
    */
    String requestNumber
    /**
    * Label: Statuses, type: entry
    */
    String statuses

    static CheckServiceRequestStatusParameters initParameters(StepParameters sp) {
        CheckServiceRequestStatusParameters parameters = new CheckServiceRequestStatusParameters()

        def requestNumber = sp.getRequiredParameter('Request Number').value
        parameters.requestNumber = requestNumber

        def statuses = sp.getRequiredParameter('Statuses').value
        parameters.statuses = statuses

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: bdc8708c7193355c0b9ba9b9814007ad ===
