package intercom.buildrelationship.server.customer.relationshipmanagement.invite.impl;

import intercom.buildrelationship.exception.Verifier;
import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.object.criteria.offices.Offices;
import intercom.buildrelationship.server.assistant.calculation.GeoCalculation;
import intercom.buildrelationship.server.assistant.json.JSONAssistant;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.CustomersToInvite;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Determines customers to invite depending on the location of the office.
 * @author Ritesh Dalvi.
 */
public class CustomersToInviteOnGeoLocation implements CustomersToInvite {

    final Logger logger = Logger.getLogger(getClass().getName());

    private final Offices office;

    /**
     * Creates an instance of {@link CustomersToInviteOnGeoLocation}.
     * @param office The office which will determine the customer to invite (falls within 100 km of the location) (cannot be null).
     * @return non-null instance of {@link CustomersToInviteOnGeoLocation}.
     * @throws {@link intercom.buildrelationship.exception.VerifyException} if the input provided is null.
     */
    public static CustomersToInvite customersToInvite(final Offices office) {

        Verifier.verifyNotNull(office,"Office:null");

        return new CustomersToInviteOnGeoLocation(office);
    }

    /**
     * Custructor. Private to avoid direct instantiation.
     * @param office The office which will determine the customer to invite (falls within 100 km of the location) (cannot be null).
     */
    private CustomersToInviteOnGeoLocation(final Offices office) {
        this.office = office;
    }

    /**
     * Invites customers within 100km of dublin office.
     * @param customer The customer to invite (may be null,empty ot blank).
     * @return Possibly null {@link CustomerResponse} if the customer does not match the criteria (within 100 km of dublin office).
     */
    @Override
    public CustomerResponse invite(final String customer) throws JSONException {

        // Assuming, we will not fail the transaction, instead we would ignore the corrupt data and log.
        if(customer == null || customer.trim().isEmpty()) {

            logger.log(Level.WARNING, "The customer information provided was either null,empty or blank",customer);

            return null;
        }

        Point coordinates = office.getCoordinates();

        final JSONObject jsonObject = new JSONObject(customer);

        final String latitude = JSONAssistant.getLatitude(jsonObject, "latitude");
        final String userId = JSONAssistant.getUserId(jsonObject, "user_id");
        final String name = JSONAssistant.getName(jsonObject, "name");
        final String longitude = JSONAssistant.getLongitude(jsonObject, "longitude");

        int distance = GeoCalculation.calculateDistance(Double.valueOf(latitude),Double.valueOf(longitude),coordinates.getX(), coordinates.getY());

        if(distance<=100) {

            final CustomerResponseImpl customerResponse = new CustomerResponseImpl();
            customerResponse.setCustomerName(name);
            customerResponse.setCustomerUserId(userId);

            return customerResponse;
        }

        return null;
    }
}
