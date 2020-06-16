package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.pojos.Launch;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;

/**
 * The user of this interface has full control of CRUD operations with launch object as a RESTful web service.
 * @author Deividas Bagdanskis
 */
//@Stateless
public interface LaunchService {

    /***
     * Gets all launches in the database.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response getAllLaunches(@Context UriInfo uriInfo);

    /**
     * Gets launches with particular name from the database.
     * @param name name of a searchable launches
     * @param uriInfo uriInfo information about URI
     * @return status code with response body
     * @throws UnsupportedEncodingException when provided URL encoding is not supported
     */
    @Path("{name}")
    Response getLaunchesByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException;

    /**
     * Gets a specified number of upcoming launches.
     * @param amount number of upcoming launches
     * @param uriInfo uriInfo information about URI
     * @return status code with response body
     */
    @Path("next/{amount}")
    Response getUpcomingLaunches(@PathParam(value = "amount") int amount, @Context UriInfo uriInfo);

    /**
     * Gets launches between given start date and end date.
     * @param startDate start date
     * @param endDate end date
     * @param uriInfo uriInfo information about URI
     * @return status code with response body
     */
    @Path(value = "{startDate}/{endDate}")
    Response getLaunchesByDates(@PathParam("startDate") String startDate, @PathParam("endDate")
            String endDate, @Context UriInfo uriInfo);

    /**
     * Adds a launch to the database.
     * @param launch launch launch object, which will be added
     * @param uriInfo uriInfo uriInfo information about URI
     * @return status code with response body
     */
    Response addLaunch(Launch launch, @Context UriInfo uriInfo);

    /**
     * Updates a launch in the database.
     * @param id id of a launch
     * @param launch launch object with updated data, but with the same id
     * @param uriInfo uriInfo uriInfo information about URI
     * @return status code with response body
     */
    Response updateLaunch(int id, Launch launch, @Context UriInfo uriInfo);

    /**
     * Deletes a specified launch from the database.
     * @param id id of a launch, which will be deleted
     * @return status code
     */
    Response deleteLaunch(int id);
}
