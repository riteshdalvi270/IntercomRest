package intercom.buildrelationship.server.customer.relationshipmanagement.organizer;

import intercom.buildrelationship.object.criteria.offices.Offices;
import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.CustomerInformationRetriever;
import intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.impl.CustomerInformationRetrieverImpl;

import java.awt.Point;
import java.io.File;
import java.util.List;

/**
 * We want to invite any customer within 100km of our Dublin office (GPS coordinates 53.3381985, -6.2592576) for some food and drinks on us.
 * Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100 km), sorted by user id (ascending).
 * @author Ritesh Dalvi.
 */
public class CustomerRelationshipManagement {

    /**
     * Prints out the customer name and user id to invite to the party which match 100km distance from dublin intercom office.
     */
    public static void main(String args[]) {

        final Offices dublin = new Dublin();
        final Point point = new Point();
        point.setLocation(53.3381985, -6.2592576);
        dublin.setCoordinates(point);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(dublin);
        final File directory = new File("./");

        List<CustomerResponse> customerResponses = customerInformationRetriever.readCustomerData(directory.getAbsolutePath() + "/src/main/resources/customer.text");

        System.out.println("Number of customers: " + customerResponses.size());

        for(final CustomerResponse customerResponse : customerResponses) {
            System.out.println("Customer ( Name: " + customerResponse.getCustomerName() + ", " + "UserId: " + customerResponse.getCustomerUserId() + " )");
        }
    }

    public static CustomerRelationshipManagement create() {
        return new CustomerRelationshipManagement();
    }

    private CustomerRelationshipManagement() {

    }

    public List<CustomerResponse> getCustomers(final Offices offices) {

        final Offices dublin = new Dublin();
        final Point point = offices.getCoordinates();
        dublin.setCoordinates(point);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(dublin);
        final File directory = new File("./");

        List<CustomerResponse> customerResponses = customerInformationRetriever.readCustomerData(directory.getAbsolutePath() + "/src/main/resources/customer.text");

        return customerResponses;
    }
}
