package clients;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import com.google.gson.Gson;

import server.service.object.CustomerInformation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.core.UriBuilder;

/**
 * Junit test to call {@link CustomerResource}. Apache http client implemented to server the purpose.
 * @author Ritesh Dalvi
 **/
public class CustomerResourceTest {

    @Test
    public void test_GetCustomerResource() {

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            final HttpGet httpGet = getHttpGetRequest();
            final HttpResponse response = httpClient.execute(httpGet);

            System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
            
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String readline = null;
            
            while((readline = bufferedReader.readLine())!=null) {
            	
            		System.out.println(readline.toString());
            }

        }catch (final Exception e) {
            e.printStackTrace();
        }
    }

    
    @Test
    public void test_CreateCustomer() {

    		final CloseableHttpClient httpClient = HttpClients.createDefault();
    		
    		try {
    			
    			final HttpPost httpPost = getHttpPostRequest();
    			
    			final HttpResponse response = httpClient.execute(httpPost);
    			
            System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
            
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String readline = null;
            
            while((readline = bufferedReader.readLine())!=null) {
            	
            		System.out.println(readline.toString());
            }

    			
    		}catch(final Exception e) {
    			e.printStackTrace();
    		}
    }
    
    private HttpPost getHttpPostRequest() throws UnsupportedEncodingException {
        final String uri = "http://localhost:8080/war/"+"service/customers";
        
        final HttpPost post = new HttpPost(uri);
        
        final CustomerInformation customerInformation = new CustomerInformation();
        customerInformation.setName("Ridima");
        customerInformation.setUserId("1234");
        customerInformation.setLatitude("53.3381985");
        customerInformation.setLongitutde("-6.2592576");
        
        final Gson gson = new Gson();
        final String custInfo = gson.toJson(customerInformation);
        
        final StringEntity entity = new StringEntity(custInfo);
        entity.setContentType("application/json");
        
        post.setEntity(entity);
        
        return post;
    }

    private HttpGet getHttpGetRequest() {

        final String uri = "http://localhost:8080/war/"+getServiceURI();
        System.out.println(uri);

        final HttpGet request = new HttpGet(uri);
        
        return request;
    }

    private String getServiceURI() {
        return UriBuilder.fromPath("service/customers/").path(String.valueOf(53.3381985)+"-"+String.valueOf(-6.2592576)).build().toString();
    }

}
