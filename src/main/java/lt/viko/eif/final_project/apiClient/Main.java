package lt.viko.eif.final_project.apiClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.orbutil.ObjectWriter;
import lt.viko.eif.final_project.pojos.Rocket;

import java.util.List;

/**
 * @author Deividas Bagdanskis
 */
public class Main {
    public static void main(String[] args) {
        LaunchLibraryClient launchLibraryClient = new LaunchLibraryClientImpl();

        List<Rocket> rockets = launchLibraryClient.getRocketsByName("falcon");

        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        for (Rocket rocket : rockets) {
            try {
                json = mapper.writeValueAsString(rocket);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
