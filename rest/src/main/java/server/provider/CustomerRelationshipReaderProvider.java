package server.provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import server.service.object.CustomerInformation;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerRelationshipReaderProvider implements MessageBodyReader<CustomerInformation> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return CustomerInformation.class.equals(type);
	}

	@Override
	public CustomerInformation readFrom(Class<CustomerInformation> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		final ObjectMapper objectMapper = new ObjectMapper();
		
		final CustomerInformation customerInformation = objectMapper.readValue(entityStream, CustomerInformation.class);
		
		return customerInformation;
	}

}
