package server.service;

import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.object.response.FailedResponse;
import server.service.object.CustomerInformation;
import server.service.object.Dublin;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;

import java.awt.*;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ritesh Dalvi
 **/
@Path("/customers")
public class CustomerServiceResource {

    @GET
    @Path("/{latitude}-{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersWithin100Km(@PathParam("latitude") final String latitude, @PathParam("longitude") final String longitude) {

        final CustomerRelationshipManagement customerRelationshipManagement = CustomerRelationshipManagement.create();

        final Dublin dublin = new Dublin();
        final Point point = new Point();
        point.setLocation(Double.valueOf(latitude),Double.valueOf(longitude));
        dublin.setCoordinates(point);
        final List<CustomerResponse> customers = customerRelationshipManagement.getCustomers(dublin);

         final List<server.service.object.CustomerResponse> responseList = createResponse(customers);
        
         final Gson gson = new Gson();
         
         final String response = gson.toJson(responseList);
         
		GenericEntity<String> entity = new GenericEntity<String>(response) {};
        return Response.ok(entity).build();
    }
    
    @POST
    @Path("/customer/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(final CustomerInformation customerInformation) {
    		
        final CustomerRelationshipManagement customerRelationshipManagement = CustomerRelationshipManagement.create();
        
        //final CustomerInformation customerInformation = readCustomerData(inputStreamReader);
        final intercom.buildrelationship.object.criteria.offices.CustomerInformation customerInfo = intercom.buildrelationship.object.criteria.offices.CustomerInformation.Builder.create().
        		withUserId(customerInformation.getUserId()).withName(customerInformation.getName()).withLatitude(customerInformation.getLatitude()).withlongitude(customerInformation.getLongitutde()).build();
        
        final intercom.buildrelationship.object.response.Response response = customerRelationshipManagement.createCustomers(customerInfo);
        
        final Gson gson = new Gson();
        
        final CustomerResponse customerResponse = response.getCustomerResponse();
        if(customerResponse.getCustomerName()!=null) {
        		GenericEntity<String> genericEntity = new GenericEntity<String>(gson.toJson(customerResponse)) {};
        		
        		return Response.created(URI.create("/service/customers/"+customerResponse.getCustomerName()+"/"+customerResponse.getCustomerUserId())).entity(genericEntity).build();
        }
        
        final FailedResponse failedResponse = response.getFailedResponse();
		GenericEntity<String> genericEntity = new GenericEntity<String>(gson.toJson(failedResponse)) {};
		
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(genericEntity).build();
    }
    
    @PUT
    @Path("{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("userId")final String userId, final CustomerInformation customerInformation) {
    	
    		return null;
    }
    
    private CustomerInformation readCustomerData(final InputStreamReader inputStreamReader) {
    	
    		return null;
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
