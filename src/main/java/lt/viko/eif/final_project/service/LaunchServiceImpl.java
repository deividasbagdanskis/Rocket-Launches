package lt.viko.eif.final_project.service;

import lt.viko.eif.final_project.apiClient.LaunchLibraryClient;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClientImpl;
import lt.viko.eif.final_project.dao.LaunchDAO;
import lt.viko.eif.final_project.dao.LaunchDAOImpl;
import lt.viko.eif.final_project.pojos.Launch;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Deividas Bagdanskis
 */
@Path("launches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LaunchServiceImpl implements LaunchService {
    private LaunchDAO launchDAO = new LaunchDAOImpl();
    private LaunchLibraryClient launchLibraryClient = new LaunchLibraryClientImpl();

    @Override
    public Response getAllLaunches(@Context UriInfo uriInfo) {
        List<Launch> launches = launchDAO.getAllLaunches();

        for (Launch launch : launches) {
            launch.addLink(getUriForSelf(uriInfo, launch.getName()), "self");
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        if (launches.size() == 0) {
            Response.noContent().build();
        }

        return Response.ok(launches).cacheControl(cacheControl).build();
    }

    @Override
    @Path("{name}")
    public Response getLaunchesByName(@PathParam(value = "name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException {
        List<Launch> launches = launchDAO.getLaunchesByName(URLDecoder.decode(name, "UTF-8"));

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

    @Override
    @Path("/next/{amount}")
    public Response getUpcomingLaunches(@PathParam("amount") int amount, @Context UriInfo uriInfo) {
        List<Launch> launches = launchDAO.getUpcomingLaunches(amount);

        if (launches.size() == 0) {
            launches = launchLibraryClient.getUpcomingLaunches(amount);

            for (Launch launch : launches) {
                launchDAO.addLaunch(launch);
            }
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(launches).cacheControl(cacheControl).build();
    }

    @Override
    @Path("{startDate}/{endDate}")
    public Response getLaunchesByDates(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
                                       @Context UriInfo uriInfo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        boolean datesAreValid = true;

        try {
            dateFormat.parse(startDate.trim());
            dateFormat.parse(endDate.trim());
        } catch (ParseException e) {
            datesAreValid = false;
        }

        if (datesAreValid) {
            List<Launch> launches = launchDAO.getLaunchesByDates(startDate, endDate);

            if (launches.size() == 0) {
                launches = launchLibraryClient.getLaunchesByDates(startDate, endDate);

                for (Launch launch : launches) {
                    launchDAO.addLaunch(launch);
                }
            }

            CacheControl cacheControl = new CacheControl();
            cacheControl.setMaxAge(60);

            return Response.ok(launches).cacheControl(cacheControl).build();
        }
        return Response.status(400).build();
    }

    @Override
    public Response addLaunch(Launch launch, @Context UriInfo uriInfo) {
        if (launchDAO.addLaunch(launch) != 0) {
            return Response.ok(launch).build();
        }
        return Response.serverError().build();
    }

    @Override
    @Path("{id}")
    public Response updateLaunch(@PathParam("id") int id, Launch launch, @Context UriInfo uriInfo) {
        if (launchDAO.getLaunchById(id) != null) {
            if (launchDAO.updateLaunch(launch)) {
                return Response.ok(launch).build();
            }
            return Response.serverError().build();
        }

        return Response.status(404).build();
    }

    @Override
    public void deleteLaunch(int id) {
        if (launchDAO.getLaunchById(id) != null) {
            launchDAO.deleteLaunch(id);
        }
    }

    private String getUriForSelf(UriInfo uriInfo, String name) {
        URI uri = null;
        try {
            uri = uriInfo.getBaseUriBuilder().path(this.getClass()).path(this.getClass(), "getLaunchesByName")
                    .queryParam("name", URLDecoder.decode(name, "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }
}
