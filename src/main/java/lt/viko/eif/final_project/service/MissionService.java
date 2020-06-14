package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.pojos.Mission;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Tomas Jokubauskas
 */
@Path("missions")
public interface MissionService {
    /**
     * Gets all missions in the repository.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    Response getAllMission(@Context UriInfo uriInfo);

    /**
     * Gets a missions with particular name from the database.
     * @param name name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    @Path("{name}")
    Response getMissionsByName(@PathParam("name") String name, @Context UriInfo uriInfo);

    /**
     * Adds a mission to the database and to the repository.
     * @param mission mission object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @POST
    Response addMission(Mission mission, @Context UriInfo uriInfo);
    /**
     * Updates a mission with a matching name in the database.
     * @param mission mission object with updated data, but with the same name
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @PUT
    Response updateMission(Mission mission, @Context UriInfo uriInfo);

    /**
     * Deletes a specified mission from the database.
     * @param name name of a mission, which will be deleted
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @DELETE
    @Path("{name}")
    Response deleteMission(@PathParam("name") String name, @Context UriInfo uriInfo);
}
