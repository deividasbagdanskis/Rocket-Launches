package lt.viko.eif.final_project.app;

import lt.viko.eif.final_project.dao.CustomerDAOImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> h = new HashSet<>();

        h.add(CustomerDAOImpl.class);

        return h;
    }
}
