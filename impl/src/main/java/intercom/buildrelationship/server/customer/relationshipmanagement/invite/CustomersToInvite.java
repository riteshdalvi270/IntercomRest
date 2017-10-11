package intercom.buildrelationship.server.customer.relationshipmanagement.invite;

import intercom.buildrelationship.object.response.CustomerResponse;
import org.codehaus.jettison.json.JSONException;

/**
 * Interface which determines the customers to invite. Depending on the functionality required the invite method can be different for different implementations of this interface.
 * @author Ritesh Dalvi
 */
public interface CustomersToInvite {

    /**
     * Invites customer based on requirements of the system.
     * @param customer The customer information (may be null,empty or blank).
     * @return possibly null {@link CustomerResponse} if input provided is null,empty or blank or if the customer information does not satisfy the requirements.
     * @throws JSONException {@link JSONException}.
     */
    public CustomerResponse invite(final String customer) throws JSONException;
}
