# Customer Relationship Management

## Getting Started

### Maven Information
* __Group Id__: company.intercom
* __Artifact Id__: intercom

### Modules/Packages

* __objects__
Contains Criteria and Response as interfaces from the system. To use the customer relationship system, implement your own implementation of the interfaces anto send in the criteria to the  system
and accept the response from the system.

  1. criteria
     Contains input/criteria accepted by the sytem, the office coordinates, where the celebration is being held.

  2. response
     Contains the customer list to invite. Includes first name and user id.

* __server__
Contains the business logic to customer relationship. Determines which customers to invite, if the customers are in the radius of 100 km from the office where the festiie is being held, send them
invitation

  1. assistant
     Contains helper classes.

  2. customer.relationshipmanagement_
     Contains classes to perform the operation.

        a. read
           Reads costumer data from the file customer.text in the resources folder

        b. invite
            Contains business logic to determine whom to invite

        c. organizer
            Entry point into the system. Calls all other classes to perform the logic and send back a response.

* __exception__
The exception package which includes the exception to tbe thrown when input is invalid or malformed.

### Classes

* Offices.java
Criteria: Interface to implement when implementing new office. It is exposed as a public interface so that the internal implementation is not leaked, no changes will be done
by the consumers. If need to implement a new office, then please extend this interface and make the implementation package-protected, so it is not expose to out consumers.

* CustomerResponse.java
Response: Response send by the service, which includes customer's full name and user id. Response is send as a public interface to hide the internal implementation and not to
expose the implementation of the response.

* GeoCalculation.java
Assistant class to calculate the distance between two points (geo calculation) on a sphere (earth).

* JSONAssistant.java
Assistant class to perform reading/retrieving Json String from Json Object.

* CustomerInformationRetrieverImpl.java
Implementation of CustomerInformationRetriever. Retrieves the customer information from customer.text.

* CustomerInformationRetriever.java
Abstract class to retrieve the customer information. The method readCustomerData() has a default implementation provided to read the customer data. It can be overriden to provide different implementation of readCustomerData().

* CustomerResponseImpl.java
Implementation of CustomerResponse.

* CustomersToInviteOnGeoLocation.java
Implementation logic of CustomersToInvite.java. Overrides methods from CustomersToInvite interface to determine the customers to invite depending on the Geo location. Different implementations can be provided
depending on the different prefrences/requirements (business logic) on inviting the customers.

* CustomersToInvite.java
Interface which determines customers to invite depending on the preferences/requirements (business logic).

* CustomerRelationshipManager
Entry point into the system. Service to read customer information (full name and user id) based on requirements (geo location).

* Dublin.java
Implementation of Offices interface. Read customer info which are within 100 km of Dublin office.

* Verifier
Verifies input parameters to the system.

* VerifyException
The Custom exception extending the RuntimeException.


### How to run:
1. Run the file CustomerRelation shipManagement
2. If the file does not run, mark the java folder as source root folder
3. Similarly, with test, if you do not get option to run test as Junit test, then mark the test folder as test source folder