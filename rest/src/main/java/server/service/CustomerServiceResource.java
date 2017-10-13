package server.service;

import intercom.buildrelationship.object.response.CustomerResponse;
import server.service.object.Dublin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
@Path("/customers")
public class CustomerServiceResource {

    @GET
    @Path("/latitude/{latitude}/longitude/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomersWithin100Km(@PathParam("latitude") final String latitude, @PathParam("longitude") final String longitude) {

        final CustomerRelationshipManagement customerRelationshipManagement = CustomerRelationshipManagement.create();

        final Dublin dublin = new Dublin();
        final Point point = new Point();
        point.setLocation(Double.valueOf(latitude),Double.valueOf(longitude));
        dublin.setCoordinates(point);
        final List<CustomerResponse> customers = customerRelationshipManagement.getCustomers(dublin);

         final List<server.service.object.CustomerResponse> responseList = createResponse(customers);
        
         final Gson gson = new Gson();
         
         final String response = gson.toJson(responseList);
         return response;
//		GenericEntity<List<server.service.object.CustomerResponse>> entity = new GenericEntity<List<server.service.object.CustomerResponse>>(response) {};
//        return Response.ok(entity).build();
    }

    private List<server.service.object.CustomerResponse> createResponse(final List<CustomerResponse> customerResponses) {
        
    		final List<server.service.object.CustomerResponse> customerList = new ArrayList<>();
    		for(CustomerResponse customerResponse :  customerResponses) {
    			final server.service.object.CustomerResponse custResp = new server.service.object.CustomerResponse();
    			custResp.setCustomerName(customerResponse.getCustomerName());
    			custResp.setCustomerUserId(custResp.getCustomerUserId());
    			
    			customerList.add(custResp);
    		}

    		return customerList;
    }
}
