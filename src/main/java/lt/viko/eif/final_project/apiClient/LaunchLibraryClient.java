package lt.viko.eif.final_project.apiClient;

import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.Mission;
import lt.viko.eif.final_project.pojos.Rocket;

import java.util.List;

/**
 * @author Deividas Bagdanskis
 */
public interface LaunchLibraryClient {
    List<Rocket> getRocketsByName(String name);
    List<Launch> getLaunchesByName(String name);
    List<Launch> getLaunchesByDates(String startDate, String endDate);
    List<Mission> getMissionsByName(String name);
}
