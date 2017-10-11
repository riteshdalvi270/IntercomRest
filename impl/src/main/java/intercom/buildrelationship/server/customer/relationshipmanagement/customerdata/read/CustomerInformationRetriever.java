package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read;

import intercom.buildrelationship.exception.Verifier;
import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.CustomersToInvite;
import org.codehaus.jettison.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class to determine the customer data to read and read the data and return List of {@link CustomerResponse}. Different implementation of this class will provide different ways to read the customer file.
 * @author Ritesh Dalvi.
 */
public abstract class CustomerInformationRetriever {

    private final Logger logger = Logger.getLogger(getClass().getName());

    protected CustomersToInvite customersToInvite;

    /**
     * Read the customer data.
     * @param fileToRead The file to read (cannot be null, empty or blank).
     * @return non-null, possibly empty List of {@link CustomerResponse}.
     * @throw {@link intercom.buildrelationship.exception.VerifyException} if file to be read is null.
     */
    public List<CustomerResponse> readCustomerData(final String fileToRead) {

        Verifier.verifyBlank(fileToRead, "File provided is invalid: null,empty or blank");

        final List<CustomerResponse> customerResponses = new ArrayList<>();

        try {
            final FileReader fileReader = new FileReader(fileToRead);

            final BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readData = null;

            while((readData = bufferedReader.readLine())!=null) {

                final CustomerResponse customerResponse = customersToInvite.invite(readData);

                if(customerResponse != null) {
                    customerResponses.add(customerResponse);
                }
            }

            Collections.sort(customerResponses, new Comparator<CustomerResponse>() {
                @Override
                public int compare(CustomerResponse customerResponse1, CustomerResponse customerResponse2) {
                    return Integer.valueOf(customerResponse1.getCustomerUserId()).compareTo(Integer.valueOf(customerResponse2.getCustomerUserId()));
                }
            });

            bufferedReader.close();
            fileReader.close();

        }catch (final FileNotFoundException fileNotFound) {

            logger.log(Level.SEVERE,"Customer data file not found",fileNotFound);

        }catch (final IOException ioException) {

            logger.log(Level.SEVERE,"Failed while reading customer data",ioException);

        } catch (JSONException jsonException) {

            logger.log(Level.SEVERE,"Failed while reading customer data",jsonException);

        }

        return customerResponses;
    }
}
