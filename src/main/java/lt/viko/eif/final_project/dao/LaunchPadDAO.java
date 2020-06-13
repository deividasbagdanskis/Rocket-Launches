package lt.viko.eif.final_project.dao;

import java.util.List;
import lt.viko.eif.final_project.pojos.LaunchPad;

/**
 * The user of this interface has full control of CRUD operations with launch pad table.
 * @author Lukas Vanglikas
 */
public interface LaunchPadDAO {
    /**
     * Gets all launch pads in the database.
     * @return a list of launch pads
     */
    List<LaunchPad> getAllLaunchPads();

    /**
     * Gets a launch pad with particular id from the database.
     * @param id id of a searchable launch pad
     * @return launch pad object - if a launch pad was found<br>
     *         null - if a launch pad was not found
     */
    LaunchPad getLaunchPadById(int id);

    /**
     * Gets a  launch pad with particular name from the database.
     * @param name name of a searchable launch pad
     * @return launch pad object - if a launch pad was found<br>
     *         null - if a launch pad was not found
     */
    LaunchPad getLaunchPadByName(String name);

    /**
     * Adds a launch pad to the database and to the repository.
     * @param launchPad launch pad object, which will be added
     * @return id of added launch pad
     */
    int addLaunchPad(LaunchPad launchPad);

    /**
     * Deletes a specified launch pad from the database.
     * @param id id of a launch pad, which will be deleted
     * @return true - if a launch pad was deleted from the database<br>
     *         false - if operation failed
     */
    boolean deleteLaunchPad(int id);
}
