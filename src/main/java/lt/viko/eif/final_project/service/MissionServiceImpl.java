package lt.viko.eif.final_project.service;

import com.jcabi.aspects.Cacheable;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClient;
import lt.viko.eif.final_project.apiClient.LaunchLibraryClientImpl;
import lt.viko.eif.final_project.dao.MissionDAO;
import lt.viko.eif.final_project.dao.MissionDAOImpl;
import lt.viko.eif.final_project.pojos.Mission;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Tomas Jokubauskas
 */
@Path("missions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MissionServiceImpl implements MissionService{
    private MissionDAO missionDao = new MissionDAOImpl();
    private LaunchLibraryClient launchLibraryClient = new LaunchLibraryClientImpl();

    /**
     * Gets all missions in the database.
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    @Cacheable(lifetime = 60, unit = TimeUnit.SECONDS)
    @Override
    public Response getAllMission(@Context UriInfo uriInfo) {
        List<Mission> missions = missionDao.getAllMission();

        for (Mission mission : missions) {
            mission.addLink(getUriForSelf(uriInfo, mission.getName()), "self");
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        if (missions.size() == 0)
            return Response.noContent().build();

        return Response.ok(missions).cacheControl(cacheControl).build();
    }

    /**
     * Gets a missions with particular name from the database.
     * @param name name of a searchable mission
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @GET
    @Path("{name}")
    @Cacheable(lifetime = 60, unit = TimeUnit.SECONDS)
    @Override
    public Response getMissionsByName(@PathParam("name") String name, @Context UriInfo uriInfo)
            throws UnsupportedEncodingException {
        List<Mission> missions = missionDao.getMissionsByName(URLDecoder.decode(name, "UTF-8"));

        if(missions.size() == 0) {
            missions = launchLibraryClient.getMissionsByName(name);

            for (Mission mission : missions) {
                missionDao.addMission(mission);
            }
        }

        for (Mission mission : missions) {
            mission.addLink(getUriForSelf(uriInfo, mission.getName()), "self");
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(60);

        return Response.ok(missions).cacheControl(cacheControl).build();
    }

    /**
     * Adds a mission to the database and to the repository.
     * @param mission mission object, which will be added
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @POST
    @Override
    public Response addMission(Mission mission, @Context UriInfo uriInfo) {
        if(missionDao.addMission(mission)) {
            mission.addLink(getUriForSelf(uriInfo, mission.getName()), "self");
            return Response.ok(mission).build();
        }
        return Response.serverError().build();
    }

    /**
     * Updates a mission with a matching name in the database.
     * @param mission mission object with updated data, but with the same name
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @PUT
    @Override
    public Response updateMission(Mission mission, @Context UriInfo uriInfo) {
        if(missionDao.updateMission(mission)) {
            mission.addLink(getUriForSelf(uriInfo, mission.getName()), "self");
            return Response.ok(mission).build();
        }
        return Response.serverError().build();
    }

    /**
     * Deletes a specified mission from the database.
     * @param name name of a mission, which will be deleted
     * @param uriInfo information about URI
     * @return status code with response body
     */
    @DELETE
    @Path("{name}")
    @Override
    public Response deleteMission(@PathParam("name") String name, @Context UriInfo uriInfo) {
        if(missionDao.deleteMission(name))
            return Response.noContent().build();
        return Response.serverError().build();
    }

    private String getUriForSelf(UriInfo uriInfo, String name) {
        URI uri = null;
        try {
            uri = uriInfo.getBaseUriBuilder().path(this.getClass()).path(this.getClass(), "getMissionsByName")
                    .resolveTemplate("name", URLDecoder.decode(name, "UTF-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri.toString();
    }
}
