package server.service;

import intercom.buildrelationship.object.response.CustomerResponse;
import server.service.object.CustomerList;
import server.service.object.Dublin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
@Path("/customers")
public class CustomerServiceResource {

    @GET
    @Path("/latitude/{latitude}/longitude/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersWithin100Km(@PathParam("latitude") final String latitude, @PathParam("longitude") final String longitude) {

        final CustomerRelationshipManagement customerRelationshipManagement = CustomerRelationshipManagement.create();

        final Dublin dublin = new Dublin();
        final Point point = new Point();
        point.setLocation(Double.valueOf(latitude),Double.valueOf(longitude));
        dublin.setCoordinates(point);
        final List<CustomerResponse> customers = customerRelationshipManagement.getCustomers(dublin);

        return createResponse(customers);
    }

    private Response createResponse(final List<CustomerResponse> customerResponses) {
        final CustomerList customerList = new CustomerList();
        customerList.setCustomerResponses(customerResponses);

        return Response.ok().entity(customerList).build();
    }

    private Point getCoordinates(final String coordinates) {

        final String[] coordinateArray = coordinates.split(",");

        final Point point = new Point();
        point.setLocation(Double.valueOf(coordinateArray[0]), Double.valueOf(coordinateArray[1]));

        return point;
    }
}
