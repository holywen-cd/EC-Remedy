
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class GetEntryParameters {
    /**
    * Label: Form Name, type: entry
    */
    String formName
    /**
    * Label: Entry ID, type: entry
    */
    String entryID
    /**
    * Label: , type: entry
    */
    String resultPropertyPath

    static GetEntryParameters initParameters(StepParameters sp) {
        GetEntryParameters parameters = new GetEntryParameters()

        def formName = sp.getRequiredParameter('Form Name').value
        parameters.formName = formName

        def entryID = sp.getRequiredParameter('Entry ID').value
        parameters.entryID = entryID

        def resultPropertyPath = sp.getParameter('resultPropertyPath').value
        parameters.resultPropertyPath = resultPropertyPath  ?: '/myJob/entriesList'

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: 2d896efd6455718d250b3ef6c7be66e7 ===
