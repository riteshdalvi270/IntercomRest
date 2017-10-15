package intercom.buildrelationship.object.response;

public interface FailedResponse {

	public Exception getError();
	
	public String ReasonForFailure();
	
}
