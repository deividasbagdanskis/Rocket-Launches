package lt.viko.eif.final_project.pojos;

import sun.awt.image.ImageWatched;

import javax.ws.rs.core.Link;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Launch {
    private int Id;
    private String name;
    private Instant windowStart;
    private Instant windowEnd;
    private Rocket rocket;
    private LaunchPad launchPad;
    private String launchServiceProvider;
    private List<Link> links = new ArrayList<>();

    public Launch() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(Instant windowStart) {
        this.windowStart = windowStart;
    }

    public Instant getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(Instant windowEnd) {
        this.windowEnd = windowEnd;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public LaunchPad getLaunchPad() {
        return launchPad;
    }

    public void setLaunchPad(LaunchPad launchPad) {
        this.launchPad = launchPad;
    }

    public String getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(String launchServiceProvider) {
        this.launchServiceProvider = launchServiceProvider;
    }

    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String findLink(String rel) {
        for (Link link : this.links) {
            if (link.getRel().equals(rel)) {
                return link.getUri().toString();
            }
        }

        return null;
    }
}
