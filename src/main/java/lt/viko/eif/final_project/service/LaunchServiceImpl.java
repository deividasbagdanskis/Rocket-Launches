package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.apiClient.LaunchLibraryClient;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClientImpl;
import lt.viko.eif.final_project.dao.LaunchDAO;
import lt.viko.eif.final_project.dao.LaunchDAOImpl;
import lt.viko.eif.final_project.pojos.Launch;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * @author Deividas Bagdanskis
 */
@Path("launches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LaunchServiceImpl {
    private LaunchDAO launchDAO = new LaunchDAOImpl();
    private LaunchLibraryClient launchLibraryClient = new LaunchLibraryClientImpl();

    @GET
//    @Override
    public Response getAllLaunches(@Context UriInfo uriInfo) {
        List<Launch> launches = launchDAO.getAllLaunches();

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        if (launches.size() == 0) {
            Response.noContent().build();
        }

        return Response.ok(launches).cacheControl(cacheControl).build();
    }

    @GET
    @Path("{name}")
//    @Override
    public Response getLaunchesByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo) {
        List<Launch> launches = launchDAO.getLaunchesByName(name);

        if (launches.size() == 0) {
            launches = launchLibraryClient.getLaunchesByName(name);

            for (Launch launch : launches) {
                launchDAO.addLaunch(launch);
            }
        }
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(launches).cacheControl(cacheControl).build();
    }

//    @Override
    public Response getUpcomingLaunches(int amount, UriInfo uriInfo) {
        return null;
    }

//    @Override
    public Response getLaunchesByDates(String startDate, String endDate, UriInfo uriInfo) {
        return null;
    }

//    @Override
    public Response addLaunch(Launch launch, UriInfo uriInfo) {
        return null;
    }

//    @Override
    public Response updateLaunch(int id, Launch launch, UriInfo uriInfo) {
        return null;
    }

//    @Override
    public void deleteLaunch(int id) {

    }
}
