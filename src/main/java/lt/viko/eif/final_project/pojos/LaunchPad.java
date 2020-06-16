package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LaunchPad {

    private int Id;
    private String name;
    private String locationName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String wikiURL;
    private String mapsURL;
    private List<Link> links = new ArrayList<>();

    public LaunchPad() {
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
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

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
    }

    public List<Link> getLinks() {
        return links;
    }
}
