package lt.viko.eif.final_project.service;


import lt.viko.eif.final_project.dao.RocketDAO;
import lt.viko.eif.final_project.dao.RocketDAOImpl;
import lt.viko.eif.final_project.pojos.Rocket;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Edvinas Jakštas
 */
@Path("rockets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RocketServiceImpl implements RocketService {
    private RocketDAO rocketDAO = new RocketDAOImpl();

    /**
     *   Gets all rockets in the repository
     * @param uriInfo information about URI
     * @return status code with response ody
     */
    @Get
    @Override
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
     *
     * @param name    name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     */


    @Get
    @Override
    @Path("{name}")
    public Response getRocketsByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException {
        List<Rocket> rockets = rocketDAO.getRocketsByName(URLDecoder.decode(name, "UTF-8"));

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(rockets).cacheControl(cacheControl).build();
    }


    /**
     *
     * Gets a rocket with particular id from the database.
     * @param id
     * @param uriInfo
     * @return
     * @throws UnsupportedEncodingException
     */

    @Override
    @Path("{id}")
    public Response getRocketById(@PathParam(value = "id") int id, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException {
        List<Rocket> rockets = rocketDAO.getRocketById(URLDecoder.decode(id, "UTF-8"));

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(rockets).cacheControl(cacheControl).build();
    }


    /**
     *
     * @param rocket rocket object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @POST
    @Override
    public Response addRocket(Rocket rocket, @Context UriInfo uriInfo) {
        if(rocketDAO.addRocket(rocket))
            return Response.ok(rocket).build();
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
    public Response updateRocket(Rocket rocket, @Context UriInfo uriInfo) {
        if(rocketDAO.updateRocket(rocket))
            return Response.ok(rocket).build();
        return Response.serverError().build();
    }


    /**
     * Deletes a specified rocket from the database.
     *
     * @param id id of a rocket, which will be deleted
     * @return status code with response body
     */
    @Delete
    @Path("{id}")
    @Override
    public void deleteRocket(int id) {
        if (rocketDAO.getRocketById(id) != null) {
            rocketDAO.deleteRocket(id);
        }
    }


    private String getUriForSelf(UriInfo uriInfo, String name) {
        URI uri = null;
        try {
            uri = uriInfo.getBaseUriBuilder().path(this.getClass()).path(this.getClass(), "getRocketsByName")
                    .queryParam("name", URLDecoder.decode(name, "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }
}
