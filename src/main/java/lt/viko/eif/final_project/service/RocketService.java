package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.pojos.Rocket;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;

/**
 * The user of this interface has full control of CRUD operations with rocket object as a RESTful web service.
 * @author Edvinas Jakstas
 */
public interface RocketService {
    /**
     * Gets all rockets in the database.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response getAllRockets(@Context UriInfo uriInfo);

    /**
     * Gets a rocket with particular name from the database.
     * @param name name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     * @throws UnsupportedEncodingException when provided URL encoding is not supported
     */
    @Path("{name}")
    Response getRocketsByName(@PathParam("name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException;

    /**
     * Adds a rocket to the database.
     * @param rocket rocket object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response addRocket(Rocket rocket, @Context UriInfo uriInfo);
    /**
     * Updates a rocket with a matching name in the database.
     * @param id id of a rocket
     * @param rocket rocket object with updated data, but with the same id
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @Path("{id}")
    Response updateRocket(@PathParam("id") int id, Rocket rocket, @Context UriInfo uriInfo);

    /**
     * Deletes a specified rocket from the database.
     * @param id id of a mission, which will be deleted
     * @param uriInfo information about URI
     * @return status code
     */
    @Path("{id}")
    Response deleteRocket(@PathParam("id") int id, @Context UriInfo uriInfo);
}
