package intercom.buildrelationship.server.customer.relationshipmanagement.invite.impl;

import intercom.buildrelationship.object.response.CustomerResponse;

/**
 * Represents information of the customer.
 * Created by ritesh on 9/23/17.
 */
class CustomerResponseImpl implements CustomerResponse {

    private String name;
    private String userId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCustomerName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCustomerUserId() {
        return userId;
    }

    /***
     * Sets the customer name.
     * @param name the customer name (cannot be null,empty or blank).
     */
    public void setCustomerName(final String name) {
        this.name = name;
    }

    /**
     * Sets the customer user id.
     * @param userId The user id of the customer (cannot be null,empty or blank).
     */
    public void setCustomerUserId(final String userId) {
        this.userId = userId;
    }
}
