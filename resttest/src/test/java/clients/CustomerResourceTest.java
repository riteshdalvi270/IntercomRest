package clients;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * @author Ritesh Dalvi
 **/
public class CustomerResourceTest {

    @Test
    public void test_CustomerResource() {

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            final HttpGet httpGet = getRequest();
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

    private HttpGet getRequest() {

        final String uri = "http://localhost:8080/war/"+getServiceURI();
        System.out.println(uri);

        final HttpGet request = new HttpGet(uri);
        //request.setHeader("Content-Type","application/xml");

        return request;
    }

    private String getServiceURI() {
        return UriBuilder.fromPath("service/customers/latitude/").path(String.valueOf(53.3381985)).path("/longitude/").path(String.valueOf(-6.2592576)).build().toString();
    }

}
