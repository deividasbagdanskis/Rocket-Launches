package lt.viko.eif.final_project.dao;

import java.util.List;
import lt.viko.eif.final_project.pojos.Mission;

/**
 * The user of this interface has full control of CRUD operations with mission table.
 */
public interface MissionDAO {
    /**
     * Gets all missions in the repository.
     * @return a list of missions
     */
    List<Mission> getAllMission();

    /**
     * Gets a missions with particular name from the database.
     * @param name name of a searchable mission
     * @return a list of missions
     */
    List<Mission> getMissionsByName(String name);

    /**
     * Gets a mission with particular id from the database.
     * @param id id of a searchable mission
     * @return mission object - if a mission was found<br>
     *         null - if a mission was not found
     */
    Mission getMissionById(int id);

    /**
     * Adds a mission to the database and to the repository.
     * @param mission mission object, which will be added
     * @return true - if mission were inserted to the database<br>
     *         false - if operation failed
     */
    boolean addMission(Mission mission);
    /**
     * Updates a mission with a matching name in the database.
     * @param mission mission object with updated data, but with the same name
     * @return true - if mission  updated in the database<br>
     *         false - if operation failed
     */
    boolean updateMission(Mission mission);

    /**
     * Deletes a specified mission from the database.
     * @param name name of a mission, which will be deleted
     * @return true - if a mission and payloads were deleted from the database<br>
     *         false - if operation failed
     */
    boolean deleteMission(String name);
}
