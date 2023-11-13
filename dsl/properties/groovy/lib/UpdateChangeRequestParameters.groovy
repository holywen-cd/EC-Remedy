
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class UpdateChangeRequestParameters {
    /**
    * Label: Entry ID, type: entry
    */
    String entryID
    /**
    * Label: Description, type: textarea
    */
    String description
    /**
    * Label: Status, type: entry
    */
    String status
    /**
    * Label: Urgency, type: entry
    */
    String urgency
    /**
    * Label: Impact, type: entry
    */
    String impact
    /**
    * Label: First Name, type: entry
    */
    String firstName
    /**
    * Label: Last Name, type: entry
    */
    String lastName
    /**
    * Label: Location Company, type: entry
    */
    String locationCompany
    /**
    * Label: Values, type: textarea
    */
    String values
    /**
    * Label: , type: entry
    */
    String resultPropertyPath

    static UpdateChangeRequestParameters initParameters(StepParameters sp) {
        UpdateChangeRequestParameters parameters = new UpdateChangeRequestParameters()

        def entryID = sp.getRequiredParameter('Entry ID').value
        parameters.entryID = entryID

        def description = sp.getParameter('Description').value
        parameters.description = description

        def status = sp.getParameter('Status').value
        parameters.status = status

        def urgency = sp.getParameter('Urgency').value
        parameters.urgency = urgency

        def impact = sp.getParameter('Impact').value
        parameters.impact = impact

        def firstName = sp.getParameter('First Name').value
        parameters.firstName = firstName

        def lastName = sp.getParameter('Last Name').value
        parameters.lastName = lastName

        def locationCompany = sp.getParameter('Location Company').value
        parameters.locationCompany = locationCompany

        def values = sp.getParameter('Values').value
        parameters.values = values  ?: '{
}
'

        def resultPropertyPath = sp.getParameter('resultPropertyPath').value
        parameters.resultPropertyPath = resultPropertyPath  ?: '/myJob/entriesList'

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: 05ad9a89fde7049de1501e9917e97b34 ===
