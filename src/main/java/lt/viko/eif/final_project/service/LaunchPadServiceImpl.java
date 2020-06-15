package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.apiClient.LaunchLibraryClient;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClientImpl;
import lt.viko.eif.final_project.dao.LaunchPadDAO;
import lt.viko.eif.final_project.dao.LaunchPadDAOImpl;
import lt.viko.eif.final_project.pojos.LaunchPad;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;


/**
 * @author Lukas Vanglikas
 */
@Path("launchPads")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LaunchPadServiceImpl implements LaunchPadService {

    private LaunchPadDAO launchPadDAO = new LaunchPadDAOImpl();

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

    @Override
    @Path("{name}")
    public Response getLaunchPadByName(String name,@Context UriInfo uriInfo) throws UnsupportedEncodingException {
        LaunchPad launchpad = launchPadDAO.getLaunchPadByName(URLDecoder.decode(name, "UTF-8"));


        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(launchpad).cacheControl(cacheControl).build();
          }

    @Override
    @PUT
    public Response addLaunchPad(LaunchPad launchPad,@Context UriInfo uriInfo) {
        if (launchPadDAO.addLaunchPad(launchPad) != 0) {
            return Response.ok(launchPad).build();
        }
        return Response.serverError().build();
    }

    @Override
    @Path("{id}")
    public void deleteLaunchPad(int id) {
        if (launchPadDAO.getLaunchPadById(id) != null) {
            launchPadDAO.deleteLaunchPad(id);
        }
    }

    private String getUriForSelf(UriInfo uriInfo, String name) {
        URI uri = null;
        try {
            uri = uriInfo.getBaseUriBuilder().path(this.getClass()).path(this.getClass(), "getLaunchPadsByName")
                    .queryParam("name", URLDecoder.decode(name, "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }
}
