package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Launch class represents a launch in the database.
 */
public class Launch {
    private int Id;
    private String name;
    private Instant windowStart;
    private Instant windowEnd;
    private Rocket rocket;
    private LaunchPad launchPad;
    private String launchServiceProvider;
    private List<Link> links = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public Launch() {
    }

    /**
     * Gets launch id.
     * @return payload id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets launch id.
     * @param id launch id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets launch name.
     * @return launch name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets launch name.
     * @param name launch name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date and time of launch window start.
     * @return date and time of launch window start
     */
    public Instant getWindowStart() {
        return windowStart;
    }

    /**
     * Sets date and time of launch window start
     * @param windowStart date and time of launch window start
     */
    public void setWindowStart(Instant windowStart) {
        this.windowStart = windowStart;
    }

    /**
     * Gets date and time of launch window end.
     * @return date and time of launch window end
     */
    public Instant getWindowEnd() {
        return windowEnd;
    }

    /**
     * Sets date and time of launch window end.
     * @param windowEnd date and time of launch window end
     */
    public void setWindowEnd(Instant windowEnd) {
        this.windowEnd = windowEnd;
    }

    /**
     * Gets rocket object of a launch.
     * @return rocket object
     */
    public Rocket getRocket() {
        return rocket;
    }

    /**
     * Sets rocket object of a launch.
     * @param rocket rocket object
     */
    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    /**
     * Gets launch pad object of a launch.
     * @return launch pad object
     */
    public LaunchPad getLaunchPad() {
        return launchPad;
    }

    /**
     * Sets launch pad object of a launch.
     * @param launchPad launch pad object
     */
    public void setLaunchPad(LaunchPad launchPad) {
        this.launchPad = launchPad;
    }

    /**
     * Gets launch service provider.
     * @return launch service provider
     */
    public String getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    /**
     * Sets launch service provider.
     * @param launchServiceProvider launch service provider
     */
    public void setLaunchServiceProvider(String launchServiceProvider) {
        this.launchServiceProvider = launchServiceProvider;
    }

    /**
     * Adds a link to a list of links.
     * @param url URL of the link
     * @param rel name of link relation to the launch
     */
    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
    }

    /**
     * Sets a list of Link objects that are assigned to a launch.
     * @param links list of Link objects
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Gets a list of Link objects that are assigned to a launch.
     * @return list of links
     */
    public List<Link> getLinks() {
        return links;
    }
}
