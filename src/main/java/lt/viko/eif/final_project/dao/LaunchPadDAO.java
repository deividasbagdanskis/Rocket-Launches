package lt.viko.eif.final_project.dao;


import java.util.List;
import lt.viko.eif.final_project.pojos.LaunchPad;

/**
 * @author Lukas Vanglikas
 */
public interface LaunchPadDAO {
    /**
     * Gets all launch pads in the repository.
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


}
