package lt.viko.eif.final_project.apiClient;

import lt.viko.eif.final_project.pojos.*;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements methods to get data of rockets, launches and missions from Launch Library API.
 * @author Deividas Bagdanskis
 */
public class LaunchLibraryClientImpl implements LaunchLibraryClient {
    private Client client;
    private WebTarget webTarget;

    /**
     * Creates Launch Library API client.
     */
    public LaunchLibraryClientImpl() {
        client = ClientBuilder.newClient();
        webTarget = client.target("https://launchlibrary.net/1.4");
    }

    /**
     * Gets a list of rockets with a given name from Launch Library API.
     * @param name name of searchable rockets
     * @return a list of rockets
     */
    @Override
    public List<Rocket> getRocketsByName(String name) {
        List<Rocket> rockets = new ArrayList<>();
        Invocation.Builder invBuilder = webTarget.path("rocket/" + name).request(MediaType.APPLICATION_JSON_TYPE);

        String inline = "";

        Response response = invBuilder.get();
        inline = response.readEntity(String.class);
        JSONObject jsonObject = new JSONObject(inline);

        JSONArray array = jsonObject.getJSONArray("rockets");

        for (int i = 0; i < array.length(); i++) {
            Rocket rocket = new Rocket();
            rocket.setName(array.getJSONObject(i).getString("name"));
            rocket.addLink(array.getJSONObject(i).getString("wikiURL"), "wikiURL");
            rockets.add(rocket);
        }
        return rockets;
    }

    /**
     * Gets a list of launches with a given name from Launch Library API.
     * @param name name of searchable launches
     * @return a list of launches
     */
    @Override
    public List<Launch> getLaunchesByName(String name) {
        List<Launch> launches = new ArrayList<>();
        Invocation.Builder invBuilder = webTarget.path("launch/" + name).request(MediaType.APPLICATION_JSON_TYPE);

        return readLaunchesFromJson(launches, invBuilder);
    }

    /**
     * Gets a specified number of upcoming launches from Launch Library API.
     * @param amount number of upcoming launches
     * @return a list of upcoming launches
     */
    @Override
    public List<Launch> getUpcomingLaunches(int amount) {
        List<Launch> launches = new ArrayList<>();
        Invocation.Builder invBuilder = webTarget.path("launch/next/" + amount).request(MediaType.APPLICATION_JSON_TYPE);

        return readLaunchesFromJson(launches, invBuilder);
    }
    /**
     * Gets a list of launches between given start date and end date from Launch Library API.
     * @param startDate start date
     * @param endDate end date
     * @return a list of launches
     */
    @Override
    public List<Launch> getLaunchesByDates(String startDate, String endDate) {
        List<Launch> launches = new ArrayList<>();
        Invocation.Builder invBuilder = webTarget.path("launch/" + startDate + "/" + endDate)
                .request(MediaType.APPLICATION_JSON_TYPE);

        return readLaunchesFromJson(launches, invBuilder);
    }

    /**
     * Gets a list of missions with a given name from Launch Library API.
     * @param name name of missions launches
     * @return a list of missions
     */
    @Override
    public List<Mission> getMissionsByName(String name) {
        List<Mission> missions = new ArrayList<>();
        Invocation.Builder invBuilder = webTarget.path("mission/" + name).request(MediaType.APPLICATION_JSON_TYPE);

        Response response = invBuilder.get();

        String inline = "";
        inline = response.readEntity(String.class);

        JSONObject jsonObject = new JSONObject(inline);
        JSONObject launchJSON;
        JSONObject customerJSON;

        JSONArray array = jsonObject.getJSONArray("missions");
        JSONArray agencies;
        JSONArray payloadsJSON;

        for (int i = 0; i < array.length(); i++) {
            Mission mission = new Mission();
            mission.setName(array.getJSONObject(i).getString("name"));
            mission.setDescription(array.getJSONObject(i).getString("description"));
            launchJSON = array.getJSONObject(i).getJSONObject("launch");
            mission.setLaunch(getLaunchesByName(String.valueOf(launchJSON.getInt("id"))).get(0));

            agencies = array.getJSONObject(i).getJSONArray("agencies");
            customerJSON = agencies.getJSONObject(0);

            Customer customer = new Customer();
            customer.setName(customerJSON.getString("name"));
            customer.setCountryCode(customerJSON.getString("countryCode"));
            customer.setWikiURL(customerJSON.getString("wikiURL"));
            mission.setCustomer(customer);

            payloadsJSON = array.getJSONObject(i).getJSONArray("payloads");
            mission.setPayloads(readPayloadsFromJSON(payloadsJSON));

            missions.add(mission);
        }

        return missions;
    }

    /**
     * Reads data of launches from JSON document returned by Launch Library API.
     * @param launches empty launch list
     * @param invBuilder Invocation.Builder of Launch Library request
     * @return a list of launches
     */
    private List<Launch> readLaunchesFromJson(List<Launch> launches, Invocation.Builder invBuilder) {
        Response response = invBuilder.get();

        String inline = "";
        inline = response.readEntity(String.class);

        JSONObject jsonObject = new JSONObject(inline);
        JSONObject rocketJSON;
        JSONObject location;
        JSONArray pads;
        JSONObject launchPadJSON;
        JSONObject launchServiceProviderJSON;

        JSONArray array = jsonObject.getJSONArray("launches");

        long windowStart = 0;
        long windowEnd = 0;

        for (int i = 0; i < array.length(); i++) {
            Launch launch = new Launch();
            launch.setName(array.getJSONObject(i).getString("name"));

            windowStart = array.getJSONObject(i).getLong("wsstamp");
            windowEnd = array.getJSONObject(i).getLong("westamp");

            launch.setWindowStart(Instant.ofEpochSecond(windowStart));
            launch.setWindowEnd(Instant.ofEpochSecond(windowEnd));

            rocketJSON = array.getJSONObject(i).getJSONObject("rocket");
            Rocket rocket = new Rocket();
            rocket.setName(rocketJSON.getString("name"));
            rocket.addLink(rocketJSON.getString("wikiURL"), "wikiURL");
            launch.setRocket(rocket);

            location = array.getJSONObject(i).getJSONObject("location");
            pads = location.getJSONArray("pads");
            launchPadJSON = pads.getJSONObject(0);

            LaunchPad launchPad = new LaunchPad();
            launchPad.setName(launchPadJSON.getString("name"));
            launchPad.setLongitude(launchPadJSON.getBigDecimal("longitude"));
            launchPad.setLatitude(launchPadJSON.getBigDecimal("latitude"));
            launchPad.setMapsURL(launchPadJSON.getString("mapURL"));
            launchPad.setWikiURL(launchPadJSON.getString("wikiURL"));
            launchPad.setLocationName(location.getString("name"));

            launch.setLaunchPad(launchPad);

            launchServiceProviderJSON = array.getJSONObject(i).getJSONObject("lsp");
            launch.setLaunchServiceProvider(launchServiceProviderJSON.getString("name"));

            launches.add(launch);
        }

        return launches;
    }

    /**
     * Reads data of payloads from JSON document returned by Launch Library API.
     * @param payloadsJSON JSONArray of payloads in JSON format
     * @return a list of payloads
     */
    private List<Payload> readPayloadsFromJSON(JSONArray payloadsJSON) {
        List<Payload> payloads = new ArrayList<>();

        for (int i = 0; i < payloadsJSON.length(); i++) {
            Payload payload = new Payload();
            payload.setDescription(payloadsJSON.getJSONObject(i).getString("description"));
            payload.setWeight((int) payloadsJSON.getJSONObject(i).getDouble("weight"));
            payload.setTotalAmount(payloadsJSON.getJSONObject(i).getInt("total"));
            payloads.add(payload);
        }

        return  payloads;
    }
}
