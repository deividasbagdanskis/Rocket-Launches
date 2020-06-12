package lt.viko.eif.final_project.apiClient;

import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.Mission;
import lt.viko.eif.final_project.pojos.Rocket;

import java.util.List;

/**
 * The user of this interface implements methods to get data of rockets, launches and missions from Launch Library API.
 * @author Deividas Bagdanskis
 */
public interface LaunchLibraryClient {

    /**
     * Gets a list of rockets with a given name from Launch Library API.
     * @param name name of searchable rockets
     * @return a list of rockets
     */
    List<Rocket> getRocketsByName(String name);

    /**
     * Gets a list of launches with a given name from Launch Library API.
     * @param name name of searchable launches
     * @return a list of launches
     */
    List<Launch> getLaunchesByName(String name);

    /**
     * Gets a list of launches between given start date and end date from Launch Library API.
     * @param startDate start date
     * @param endDate end date
     * @return a list of launches
     */
    List<Launch> getLaunchesByDates(String startDate, String endDate);

    /**
     * Gets a list of missions with a given name from Launch Library API.
     * @param name name of missions launches
     * @return a list of missions
     */
    List<Mission> getMissionsByName(String name);
}
