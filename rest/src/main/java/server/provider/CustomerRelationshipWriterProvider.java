package server.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRelationshipWriterProvider implements MessageBodyWriter<intercom.buildrelationship.object.response.Response> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return intercom.buildrelationship.object.response.Response.class.equals(type);
	}

	@Override
	public long getSize(intercom.buildrelationship.object.response.Response t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(intercom.buildrelationship.object.response.Response t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException, WebApplicationException {
		
		final ObjectMapper mapper = new ObjectMapper();
		
		mapper.writeValue(entityStream, intercom.buildrelationship.object.response.Response.class);
	}

}
