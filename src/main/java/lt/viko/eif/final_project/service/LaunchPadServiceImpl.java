package lt.viko.eif.final_project.service;

import com.jcabi.aspects.Cacheable;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClient;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClientImpl;
import lt.viko.eif.final_project.dao.LaunchPadDAO;
import lt.viko.eif.final_project.dao.LaunchPadDAOImpl;
import lt.viko.eif.final_project.pojos.LaunchPad;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * This class implements a RESTful web service, which allows the client to perform CRUD operations with a database
 * through a launchPad DAO.&nbsp;This web service produces and consumes JSON documents.
 * @author Lukas Vanglikas
 */
@Path("launchPads")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LaunchPadServiceImpl implements LaunchPadService {
    private LaunchPadDAO launchPadDAO = new LaunchPadDAOImpl();

    @GET
    @Cacheable(lifetime = 60, unit = TimeUnit.SECONDS)
    @Override
    public Response getAllLaunchPads(@Context UriInfo uriInfo) {
        List<LaunchPad> LaunchPads = launchPadDAO.getAllLaunchPads();

        for (LaunchPad launchPad : LaunchPads) {
            launchPad.addLink(getUriForSelf(uriInfo, launchPad.getName()), "self");
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        if (LaunchPads.size() == 0) {
            Response.noContent().build();
        }

        return Response.ok(LaunchPads).cacheControl(cacheControl).build();
    }

    @GET
    @Override
    @Cacheable(lifetime = 60, unit = TimeUnit.SECONDS)
    @Path("{name}")
    public Response getLaunchPadByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException {
        LaunchPad launchPad = launchPadDAO.getLaunchPadByName(URLDecoder.decode(name, "UTF-8"));

        launchPad.addLink(getUriForSelf(uriInfo, launchPad.getName()), "self");

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(launchPad).cacheControl(cacheControl).build();
    }

    @Override
    @POST
    public Response addLaunchPad(LaunchPad launchPad, @Context UriInfo uriInfo) {
        if (launchPadDAO.addLaunchPad(launchPad) != 0) {
            launchPad.addLink(getUriForSelf(uriInfo, launchPad.getName()), "self");
            return Response.ok(launchPad).build();
        }
        return Response.serverError().build();
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response deleteLaunchPad(@PathParam("id") int id) {
        if (launchPadDAO.getLaunchPadById(id) != null) {
            if (launchPadDAO.deleteLaunchPad(id)) {
                return Response.noContent().build();
            }
        }

        return Response.serverError().build();
    }

    /***
     * Creates a URI for getting data about a launch pad.
     * @param uriInfo information about URI
     * @param name name of a launch pad
     * @return URI of a launch pad
     */
    private String getUriForSelf(UriInfo uriInfo, String name) {
        URI uri = null;
        try {
            uri = uriInfo.getBaseUriBuilder().path(this.getClass()).path(this.getClass(), "getLaunchPadByName")
                    .resolveTemplate("name", URLDecoder.decode(name, "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }
}
