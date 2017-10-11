package server.service;

import intercom.buildrelationship.object.criteria.offices.Offices;
import intercom.buildrelationship.object.response.CustomerResponse;

import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
public class CustomerRelationshipManagement {

    public static CustomerRelationshipManagement create() {
        return new CustomerRelationshipManagement();
    }

    private CustomerRelationshipManagement() {

    }

    public List<CustomerResponse> getCustomers(final Offices offices) {

        final intercom.buildrelationship.server.customer.relationshipmanagement.organizer.CustomerRelationshipManagement customerRelationshipManagement =
                intercom.buildrelationship.server.customer.relationshipmanagement.organizer.CustomerRelationshipManagement.create();

        return customerRelationshipManagement.getCustomers(offices);
    }
}
