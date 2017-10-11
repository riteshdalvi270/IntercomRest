package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.impl;

import com.google.common.collect.ImmutableList;
import intercom.buildrelationship.exception.VerifyException;
import intercom.buildrelationship.object.criteria.offices.Offices;
import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.CustomerInformationRetriever;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.CustomersToInvite;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.impl.CustomersToInviteOnGeoLocation;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * JUnit test for {@link CustomerInformationRetrieverImpl}.
 * @author Ritesh Dalvi.
 */

public class CustomerInformationRetrieverImplTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    /**
     * Test to ensure that {@link VerifyException} is thrown when the input to {@link CustomerInformationRetrieverImpl#createInstance(Offices)} is null.
     */
    @Test
    public void test_VerifyExceptionException_OfficeNull() {

        expectedException.expect(VerifyException.class);
        expectedException.expectMessage("office:null");

        CustomerInformationRetrieverImpl.createInstance(null);
    }

    /**
     * Test to ensure that {@link VerifyException} is thrown when file to read is null.
     */
    @Test
    public void test_FileToRead_Null() {

        expectedException.expect(VerifyException.class);
        expectedException.expectMessage("File provided is invalid: null,empty or blank");

        final Offices offices = Mockito.mock(Offices.class);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(offices);
        customerInformationRetriever.readCustomerData(null);
    }

    /**
     * Test to ensure that {@link VerifyException} is thrown when file to read is empty.
     */
    @Test
    public void test_FileToRead_Empty() {

        expectedException.expect(VerifyException.class);
        expectedException.expectMessage("File provided is invalid: null,empty or blank");

        final Offices offices = Mockito.mock(Offices.class);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(offices);
        customerInformationRetriever.readCustomerData("");
    }

    /**
     * Test to ensure that {@link VerifyException} is thrown when file to read is blank.
     */
    @Test
    public void test_FileToRead_Blank() {

        expectedException.expect(VerifyException.class);
        expectedException.expectMessage("File provided is invalid: null,empty or blank");

        final Offices offices = Mockito.mock(Offices.class);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(offices);
        customerInformationRetriever.readCustomerData("   ");
    }

    /**
     * Test to ensure {@link java.io.FileNotFoundException} is thrown when trying to read customer data for the file which does not exist.
     */
    @Test
    public void test_ReadCustomerData_FileNotFoundException() {

        final Offices offices = Mockito.mock(Offices.class);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(offices);

        final List<CustomerResponse> customerResponses = customerInformationRetriever.readCustomerData("customerdata.text");

        Assert.assertEquals(Collections.emptyList(),customerResponses);
    }

    /**
     * Test to ensure that when {@link CustomerInformationRetriever#readCustomerData(String)} is called, the customer information is read successfully.
     */
    @Test
    public void test_ReadCustomerData() throws JSONException {
        final Offices offices = Mockito.mock(Offices.class);
        final Point coordinates = new Point();
        coordinates.setLocation(53.3381985, -6.2592576);
        Mockito.when(offices.getCoordinates()).thenReturn(coordinates);

        final CustomersToInvite customersToInvite = Mockito.mock(CustomersToInvite.class);
        PowerMockito.mockStatic(CustomersToInviteOnGeoLocation.class);
        Mockito.when(CustomersToInviteOnGeoLocation.customersToInvite(offices)).thenReturn(customersToInvite);

        final CustomerResponse expectedCustomerResponse1 = Mockito.mock(CustomerResponse.class);
        Mockito.when(expectedCustomerResponse1.getCustomerName()).thenReturn("Christina McArdle");
        Mockito.when(expectedCustomerResponse1.getCustomerUserId()).thenReturn("12");
        Mockito.when(customersToInvite.invite("{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}")).thenReturn(expectedCustomerResponse1);

        final CustomerResponse expectedCustomerResponse2 = Mockito.mock(CustomerResponse.class);
        Mockito.when(expectedCustomerResponse2.getCustomerName()).thenReturn("Alice Cahill");
        Mockito.when(expectedCustomerResponse2.getCustomerUserId()).thenReturn("1");
        Mockito.when(customersToInvite.invite("{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}")).thenReturn(expectedCustomerResponse2);

        final CustomerInformationRetriever customerInformationRetriever = CustomerInformationRetrieverImpl.createInstance(offices);
        File directory = new File("./");


        final List<CustomerResponse> actualCustomerResponses = customerInformationRetriever.readCustomerData(directory.getAbsolutePath() + "/src/main/test/intercom/buildrelationship/server/customer/relationshipmanagement/customerdata/read/impl/customer.text");

        final List<CustomerResponse> expectedCustomerResponses = ImmutableList.of(expectedCustomerResponse1,expectedCustomerResponse2);

        Assert.assertEquals(expectedCustomerResponses.size(),actualCustomerResponses.size());

        Assert.assertEquals(expectedCustomerResponse2.getCustomerName(),actualCustomerResponses.get(0).getCustomerName());
        Assert.assertEquals(expectedCustomerResponse2.getCustomerUserId(),actualCustomerResponses.get(0).getCustomerUserId());

        Assert.assertEquals(expectedCustomerResponse1.getCustomerName(),actualCustomerResponses.get(1).getCustomerName());
        Assert.assertEquals(expectedCustomerResponse1.getCustomerUserId(),actualCustomerResponses.get(1).getCustomerUserId());
    }
}
