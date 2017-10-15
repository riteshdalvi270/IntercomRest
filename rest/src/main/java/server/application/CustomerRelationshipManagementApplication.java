package server.application;

import server.provider.CustomerRelationshipReaderProvider;
import server.provider.CustomerRelationshipWriterProvider;
import server.service.CustomerServiceResource;

import javax.ws.rs.core.Application;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

/**
 * @author Ritesh Dalvi
 **/
public class CustomerRelationshipManagementApplication extends Application {

    private static final Set<Object> service = Collections.emptySet();
    private static final Set<Class<?>> classSet = Sets.newHashSet();

    public CustomerRelationshipManagementApplication() {
    	classSet.add(CustomerServiceResource.class);
    	classSet.add(CustomerRelationshipReaderProvider.class);
    	classSet.add(CustomerRelationshipWriterProvider.class);
    }

    public Set<Class<?>> getClasses() {
        return classSet;
    }

    public Set<Object> getSingletons() {
        return service;
    }
}
