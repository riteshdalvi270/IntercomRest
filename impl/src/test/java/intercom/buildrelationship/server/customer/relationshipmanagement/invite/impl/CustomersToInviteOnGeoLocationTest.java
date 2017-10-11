package intercom.buildrelationship.server.customer.relationshipmanagement.invite.impl;

import intercom.buildrelationship.exception.VerifyException;
import intercom.buildrelationship.object.criteria.offices.Offices;
import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.CustomersToInvite;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.awt.*;

/**
 * JUnit test to test {@link CustomersToInviteOnGeoLocation}.
 * @author Ritesh Dalvi.
 */
public class CustomersToInviteOnGeoLocationTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    /**
     * Test to ensure that {@link VerifyException} is thrown when {@link CustomersToInviteOnGeoLocation#customersToInvite(Offices)} is called with null input.
     */
    @Test
    public void test_VerifyExceptionException_InputNull() {

        expectedException.expect(VerifyException.class);
        expectedException.expectMessage("Office:null");

        CustomersToInviteOnGeoLocation.customersToInvite(null);
    }

    /**
     * Test to ensure that null response is returned when {@link CustomersToInvite#invite(String)} is called with null customer input.
     */
    @Test
    public void test_InvalidCustomerInput_NullInput() throws JSONException {

        final Offices offices = Mockito.mock(Offices.class);

        final CustomersToInvite customersToInvite = CustomersToInviteOnGeoLocation.customersToInvite(offices);
        final CustomerResponse customerResponse = customersToInvite.invite(null);

        Assert.assertEquals(null,customerResponse);
    }

    /**
     * Test to ensure that null response is returned when {@link CustomersToInvite#invite(String)} is called with blank customer input.
     */
    @Test
    public void test_InvalidCustomerInput_BlankInput() throws JSONException {

        final Offices offices = Mockito.mock(Offices.class);

        final CustomersToInvite customersToInvite = CustomersToInviteOnGeoLocation.customersToInvite(offices);
        final CustomerResponse customerResponse = customersToInvite.invite("    ");

        Assert.assertEquals(null,customerResponse);
    }

    /**
     * Test to ensure that null response is returned when {@link CustomersToInvite#invite(String)} is called with empty customer input.
     */
    @Test
    public void test_InvalidCustomerInput_EmptyInput() throws JSONException {

        final Offices offices = Mockito.mock(Offices.class);

        final CustomersToInvite customersToInvite = CustomersToInviteOnGeoLocation.customersToInvite(offices);
        final CustomerResponse customerResponse = customersToInvite.invite("");

        Assert.assertEquals(null,customerResponse);
    }

    /**
     * Test to ensure that customers are not invited if they do not fall within 100km from the intercom office.
     */
    @Test
    public void test_DoNotInviteCustomers_NotWithin100Km() throws JSONException {

        final Point coordinates = new Point();
        coordinates.setLocation(53.3381985, -6.2592576);

        final Offices offices = Mockito.mock(Offices.class);
        Mockito.when(offices.getCoordinates()).thenReturn(coordinates);

        final String customer = "{\"latitude\": \"53.4692815\", \"user_id\": 7, \"name\": \"Frank Kehoe\", \"longitude\": \"-9.436036\"}";

        final CustomersToInvite customersToInvite = CustomersToInviteOnGeoLocation.customersToInvite(offices);
        final CustomerResponse customerResponse = customersToInvite.invite(customer);

        Assert.assertEquals(null,customerResponse);
    }

    /**
     * Test to ensure that customer is invited if he falls within 100km from the intercom office.
     */
    @Test
    public void test_InviteCustomers_Within100Km() throws JSONException {

        final Point coordinates = new Point();
        coordinates.setLocation(53.3381985, -6.2592576);

        final Offices offices = Mockito.mock(Offices.class);
        Mockito.when(offices.getCoordinates()).thenReturn(coordinates);

        final String customer = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";

        final CustomersToInvite customersToInvite = CustomersToInviteOnGeoLocation.customersToInvite(offices);
        final CustomerResponse actualCustomerResponse = customersToInvite.invite(customer);

        final CustomerResponseImpl expectedCustomerResponse= new CustomerResponseImpl();
        expectedCustomerResponse.setCustomerName("Christina McArdle");
        expectedCustomerResponse.setCustomerUserId("12");

        Assert.assertEquals(expectedCustomerResponse.getCustomerName(),actualCustomerResponse.getCustomerName());
        Assert.assertEquals(expectedCustomerResponse.getCustomerUserId(),actualCustomerResponse.getCustomerUserId());
    }
}
