package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Rocket;

import java.util.List;

/**
 * The user of this interface has full control of CRUD operations with rocket table.
 * @author Deividas Bagdanskis
 */
public interface RocketDAO {

    /**
     * Gets all rockets in the database.
     * @return a list of rockets
     */
    List<Rocket> getAllRockets();

    /**
     * Gets a rocket with particular name from the database.
     * @param name name of a searchable rocket
     * @return rocket object - if a rocket was found<br>
     *         null - if a rocket was not found
     */
    Rocket getRocketByName(String name);

    /**
     * Gets a rocket with particular id from the database.
     * @param id id of a searchable rocket
     * @return rocket object - if a rocket was found<br>
     *         null - if a rocket was not found
     */
    Rocket getRocketById(int id);

    /**
     * Adds a rocket to the database.
     * @param rocket rocket object, which will be added
     * @return true - if rocket and stages were inserted to the database<br>
     *         false - if operation failed
     */
    boolean addRocket(Rocket rocket);
    /**
     * Updates a rocket with a matching name in the database.
     * @param rocket rocket object with updated data, but with the same name
     * @return true - if rocket and stages were updated in the database<br>
     *         false - if operation failed
     */
    boolean updateRocket(Rocket rocket);

    /**
     * Deletes a specified rocket from the database.
     * @param id id of a rocket, which will be deleted
     * @return true - if rocket and stages were deleted from the database<br>
     *         false - if operation failed
     */
    boolean deleteRocket(int id);
}
