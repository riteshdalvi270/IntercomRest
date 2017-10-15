package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.write;

import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.object.response.FailedResponse;
import intercom.buildrelationship.object.response.Response;

public class ResponseImpl implements Response {

	private CustomerResponse customerResponse;
	private FailedResponse failedResponse;
	
	public void setCustomerResponse(CustomerResponse customerResponse) {
		this.customerResponse = customerResponse;
	}
	
	public void setFailedResponse(FailedResponse failedResponse) {
		this.failedResponse = failedResponse;
	}
	
	@Override
	public CustomerResponse getCustomerResponse() {
		return customerResponse;
	}

	@Override
	public FailedResponse getFailedResponse() {
		return failedResponse;
	}

}
