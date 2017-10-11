package intercom.buildrelationship.server.customer.relationshipmanagement.organizer;

import com.google.common.collect.ImmutableList;
import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.CustomerInformationRetriever;
import intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.impl.CustomerInformationRetrieverImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

/**
 * JUnit test for {@link CustomerRelationshipManagement}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CustomerInformationRetrieverImpl.class})
public class CustomerRelationshipManagementTest {

    private CustomerInformationRetriever customerInformationRetriever;

    /**
     * Executes before the test. Sets up mock objects needed by the test.
     */
    @Before
    public void beforeTest() {
        PowerMockito.mockStatic(CustomerInformationRetrieverImpl.class);
        customerInformationRetriever = Mockito.mock(CustomerInformationRetriever.class);
        Mockito.when(CustomerInformationRetrieverImpl.createInstance(Mockito.any())).thenReturn(customerInformationRetriever);
    }

    /**
     * Executes after the test. Dereferences objects used by the test.
     */
    @After
    public void afterTest() {

        customerInformationRetriever = null;
    }

    /**
     * Test to ensure that {@link CustomerInformationRetriever#readCustomerData(String)} is called at least once.
     */
    @Test
    public void test_CustomerRelationshipManagement() {

        final CustomerResponse customerResponse = Mockito.mock(CustomerResponse.class);
        final File directory = new File("./");
        Mockito.when(customerInformationRetriever.readCustomerData(directory.getAbsolutePath() + "/src/main/resources/customer.text")).thenReturn(ImmutableList.of(customerResponse));

        final CustomerRelationshipManagement customerRelationshipManagement = new CustomerRelationshipManagement();
        customerRelationshipManagement.main(new String[]{});

        Mockito.verify(customerInformationRetriever).readCustomerData(directory.getAbsolutePath() + "/src/main/resources/customer.text");
    }
}
