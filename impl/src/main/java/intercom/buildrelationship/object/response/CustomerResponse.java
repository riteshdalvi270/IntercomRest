package intercom.buildrelationship.object.response;

/**
 * Interface that represents the information of the customer.
 * @author Ritesh Dalvi.
 */
public interface CustomerResponse {

    /**
     * @return non-null,non-empty, non-blank full formatted name of the customer.
     */
    public String getCustomerName();

    /**
     * @return non-null,non-empty, non-blank user id of the customer.
     */
    public String getCustomerUserId();
}
