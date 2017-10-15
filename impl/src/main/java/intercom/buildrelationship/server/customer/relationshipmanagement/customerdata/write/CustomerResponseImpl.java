package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.write;

import intercom.buildrelationship.object.response.CustomerResponse;

public class CustomerResponseImpl implements CustomerResponse {

	String name;
	
	String userId;

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	@Override
	public String getCustomerName() {
		return name;
	}

	@Override
	public String getCustomerUserId() {
		return userId;
	}
	
	
}
