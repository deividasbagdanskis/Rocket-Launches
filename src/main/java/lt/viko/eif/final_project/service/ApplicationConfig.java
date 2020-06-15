package lt.viko.eif.final_project.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines the components of a JAX-RS application and supplies additional meta-data.
 * @author Deividas Bagdanskis
 */
@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> h = new HashSet<>();

        h.add(LaunchServiceImpl.class);
        h.add(MissionServiceImpl.class);


        return h;
    }
}
