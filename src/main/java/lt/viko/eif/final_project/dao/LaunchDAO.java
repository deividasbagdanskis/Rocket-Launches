package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Launch;

import java.util.List;

/**
 * The user of this interface has full control of CRUD operations with launch table.
 * @author Deividas Bagdanskis
 */
public interface LaunchDAO {

    /**
     * Gets all rockets in the database.
     * @return a list of launches
     */
    List<Launch> getAllLaunches();

    /**
     * Gets a launch with particular name from the database.
     * @param name name of a searchable launch
     * @return rocket object - if a launch was found<br>
     *         null - if a launch was not found
     */
    List<Launch> getLaunchesByName(String name);

    /**
     * Gets a launch with particular id from the database.
     * @param id id of a searchable launch
     * @return rocket object - if a launch was found<br>
     *         null - if a launch was not found
     */
    Launch getLaunchById(int id);

    /**
     * Gets a specified number of upcoming launches.
     * @param amount number of upcoming launches
     * @return a list of upcoming launches
     */
    List<Launch> getUpcomingLaunches(int amount);

    /**
     * Gets launches between given start date and end date.
     * @param startDate start date
     * @param endDate end date
     * @return a list of launches
     */
    List<Launch> getLaunchesByDates(String startDate, String endDate);

    /**
     * Adds a rocket to the database.
     * @param launch launch object, which will be added
     * @return id of added launch
     */
    int addLaunch(Launch launch);

    /**
     * Updates a launch with a matching name in the database.
     * @param launch launch object with updated data, but with the same name
     * @return true - if launch were updated in the database<br>
     *         false - if operation failed
     */
    boolean updateLaunch(Launch launch);

    /**
     * Deletes a specified launch from the database.
     * @param id id of a launch, which will be deleted
     * @return true - if a launch was deleted from the database<br>
     *         false - if operation failed
     */
    boolean deleteLaunch(int id);
}
