package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.util.List;

public class LaunchPad {

    private int Id;
    private String name;
    private String locationName;
    private double latitude;
    private double longitude;
    private String wikiURL;
    private String mapsURL;
    private List<Link> links;

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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public String getMapsURL() {
        return mapsURL;
    }

    public void setMapsURL(String mapsURL) {
        this.mapsURL = mapsURL;
    }

    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
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
