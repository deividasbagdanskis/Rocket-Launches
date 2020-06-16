package lt.viko.eif.final_project.service;


import com.jcabi.aspects.Cacheable;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClient;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClientImpl;
import lt.viko.eif.final_project.dao.RocketDAO;
import lt.viko.eif.final_project.dao.RocketDAOImpl;
import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.Rocket;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Edvinas Jakštas
 */
@Path("rockets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RocketServiceImpl implements RocketService {
    private RocketDAO rocketDAO = new RocketDAOImpl();
    private LaunchLibraryClient launchLibraryClient = new LaunchLibraryClientImpl();

    /**
     * Gets all rockets in the repository
     * @param uriInfo information about URI
     * @return status code with response ody
     */
    @GET
    @Override
    @Cacheable(lifetime = 60, unit = TimeUnit.SECONDS)
    public Response getAllRockets(@Context UriInfo uriInfo) {
        List<Rocket> rockets = rocketDAO.getAllRockets();

        for (Rocket rocket : rockets) {
            rocket.addLink(getUriForSelf(uriInfo, rocket.getName()), "self");
        }

        if (rockets.size() == 0) {
            Response.noContent().build();
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(rockets).cacheControl(cacheControl).build();
    }

    /**
     * Gets a rocket with particular name from the database.
     * @param name name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    @Cacheable(lifetime = 60, unit = TimeUnit.SECONDS)
    @Override
    @Path("{name}")
    public Response getRocketsByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException {
        List<Rocket> rockets = rocketDAO.getRocketsByName(URLDecoder.decode(name, "UTF-8"));

        if (rockets.size() == 0) {
            rockets = launchLibraryClient.getRocketsByName(name);

            for (Rocket rocket : rockets) {
                rocketDAO.addRocket(rocket);
            }
        }

        for (Rocket rocket : rockets) {
            rocket.addLink(getUriForSelf(uriInfo, rocket.getName()), "self");
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(rockets).cacheControl(cacheControl).build();
    }

    /**
     * Adds a rocket to the database.
     * @param rocket rocket object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @POST
    @Override
    public Response addRocket(Rocket rocket, @Context UriInfo uriInfo) {
        if(rocketDAO.addRocket(rocket) != 0) {
            rocket.addLink(getUriForSelf(uriInfo, rocket.getName()), "self");
            return Response.ok(rocket).build();
        }
        return Response.serverError().build();
    }

    /**
     *
     * @param rocket rocket object with updated data, but with the same name
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @PUT
    @Override
    @Path("{id}")
    public Response updateRocket(@PathParam("id") int id, Rocket rocket, @Context UriInfo uriInfo) {
        if(rocketDAO.updateRocket(rocket)) {
            rocket.addLink(getUriForSelf(uriInfo, rocket.getName()), "self");
            return Response.ok(rocket).build();
        }
        return Response.serverError().build();
    }

    /**
     * Deletes a specified rocket from the database.
     *
     * @param id id of a rocket, which will be deleted
     * @return status code with response body
     */
    @DELETE
    @Path("{id}")
    @Override
    public Response deleteRocket(@PathParam("id")int id, @Context UriInfo uriInfo) {
        if (rocketDAO.getRocketById(id) != null) {
            if (rocketDAO.deleteRocket(id)) {
                return Response.noContent().build();
            }
        }
        return Response.serverError().build();
    }


    private String getUriForSelf(UriInfo uriInfo, String name) {
        URI uri = null;
        try {
            uri = uriInfo.getBaseUriBuilder().path(this.getClass()).path(this.getClass(), "getRocketsByName")
                    .resolveTemplate("name", URLDecoder.decode(name, "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }
}
