package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.pojos.Rocket;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Edvinas Jakštas
 */
@Path("rockets")
public interface RocketService {
    /**
     * Gets all rockets in the repository.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    Response getAllRockets(@Context UriInfo uriInfo);

    /**
     * Gets a rocket with particular name from the database.
     * @param name name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    @Path("{name}")
    Response getRocketsByName(@PathParam("name") String name, @Context UriInfo uriInfo);

    /**
     * Gets a rocket with particular id from the database.
     * @param id of a searchable rocket
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    @Path("{id}")
    Response getRocketById(@PathParam("id") int id, @Context UriInfo uriInfo);

    /**
     * Adds a rocket to the database and to the repository.
     * @param rocket rocket object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */

    @POST
    Response addRocket(Rocket rocket, @Context UriInfo uriInfo);
    /**
     * Updates a rocket with a matching name in the database.
     * @param rocket rocket object with updated data, but with the same name
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @PUT
    @Path("{id}")
    Response updateRocket(Rocket rocket, @Context UriInfo uriInfo);

    /**
     * Deletes a specified rocket from the database.
     * @param id id of a mission, which will be deleted
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @DELETE
    @Path("{id}")
    Response deleteRocket(@PathParam("id") int id, @Context UriInfo uriInfo);
}
