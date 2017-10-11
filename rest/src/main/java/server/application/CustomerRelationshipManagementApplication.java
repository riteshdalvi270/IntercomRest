package server.application;

import server.service.CustomerServiceResource;

import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

/**
 * @author Ritesh Dalvi
 **/
public class CustomerRelationshipManagementApplication extends Application {

    private static final Set<Object> service = Collections.emptySet();
    private static final Set<Class<?>> emptyClassSet = Collections.emptySet();

    public CustomerRelationshipManagementApplication() {
        service.add(new CustomerServiceResource());
    }

    public Set<Class<?>> getClasses() {
        return emptyClassSet;
    }

    public Set<Object> getSingletons() {
        return service;
    }
}
