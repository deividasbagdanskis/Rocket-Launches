package lt.viko.eif.final_project.dao;
import  lt.viko.eif.final_project.pojos.Payload;

import java.util.List;

public interface PayloadDAO {


    /**
     * Gets payloads of a particular mission.
     * @param missionId id of a mission
     * @return list of payloads
     */
    List<Payload> getPayloads(int missionId);

    /**
     * Adds a payload to the database and to the repository.
     * @param payload payload object, which will be added
     * @return true - if customer  were inserted to the database<br>
     *         false - if operation failed
     */
    boolean addPayload(Payload payload);

    /**
     * Updates a payload with a matching id in the database.
     * @param payload payload object with updated data, but with the same id
     * @return true - if payload updated in the database<br>
     *         false - if operation failed
     */
    boolean updatePayload(Payload payload);

    /**
     * Deletes a specified payload from the database.
     * @param id id of a payload, which will be deleted
     * @return true - if a payload was deleted from the database<br>
     *         false - if operation failed
     */
    boolean deletePayload(int id);
}
