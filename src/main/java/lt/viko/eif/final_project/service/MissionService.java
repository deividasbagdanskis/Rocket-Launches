package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.pojos.Mission;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;

/**
 * The user of this interface has full control of CRUD operations with mission object as a RESTful web service.
 * @author Tomas Jokubauskas
 */
public interface MissionService {
    /**
     * Gets all missions in the database.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response getAllMission(@Context UriInfo uriInfo);

    /**
     * Gets a missions with particular name from the database.
     * @param name name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     * @throws UnsupportedEncodingException when provided URL encoding is not supported
     */
    @Path("{name}")
    Response getMissionsByName(@PathParam("name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException;

    /**
     * Adds a mission to the database.
     * @param mission mission object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response addMission(Mission mission, @Context UriInfo uriInfo);

    /**
     * Updates a mission with a matching name in the database.
     * @param id id of a mission
     * @param mission mission object with updated data, but with the same name
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response updateMission(int id, Mission mission, @Context UriInfo uriInfo);

    /**
     * Deletes a specified mission from the database.
     * @param name name of a mission, which will be deleted
     * @param uriInfo information about URI
     * @return status code with response body
     * @throws UnsupportedEncodingException when provided URL encoding is not supported
     */
    @Path("{name}")
    Response deleteMission(@PathParam("name") String name, @Context UriInfo uriInfo) throws UnsupportedEncodingException;
}
