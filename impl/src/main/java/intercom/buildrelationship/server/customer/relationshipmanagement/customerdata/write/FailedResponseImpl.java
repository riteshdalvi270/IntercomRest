package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.write;

import intercom.buildrelationship.object.response.FailedResponse;

class FailedResponseImpl implements FailedResponse {

	private Exception error;
	private String reasonForFailure;
	
	void setException(final Exception exception) {
		this.error = exception;
	}
	
	void setReasonForFailure(String reasonForFailure) {
		this.reasonForFailure = reasonForFailure;
	}
	
	@Override
	public Exception getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ReasonForFailure() {
		// TODO Auto-generated method stub
		return null;
	}

}
