package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.pojos.LaunchPad;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;

public interface LaunchPadService {

    /***
     * Gets all launch pads in the database.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    Response getAllLaunchPads(@Context UriInfo uriInfo);

    /**
     * Gets launch pads with particular name from the database.
     * @param name name of a searchable launch pad
     * @param uriInfo uriInfo information about URI
     * @return status code with response body
     */
    @Path("{name}")
    Response getLaunchPadByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException;

    /**
     * Adds a launch pad to the database.
     * @param launchPad launch pad object, which will be added
     * @param uriInfo uriInfo uriInfo information about URI
     * @return status code with response body
     */
    Response addLaunchPad(LaunchPad launchPad, @Context UriInfo uriInfo);

    /**
     * Deletes a specified launch pad from the database.
     * @param id id of a launch pad, which will be deleted
     */
    @Path("{id}")
    Response deleteLaunchPad(@PathParam("id") int id);
}
