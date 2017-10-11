package server.service.object;

import intercom.buildrelationship.object.response.CustomerResponse;

import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
public class CustomerList {

    private List<CustomerResponse> customerResponses;

    public List<CustomerResponse> getCustomerResponses() {
        return customerResponses;
    }

    public void setCustomerResponses(List<CustomerResponse> customerResponses) {
        this.customerResponses = customerResponses;
    }
}
