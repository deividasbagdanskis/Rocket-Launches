package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * LaunchPad class represents a launch pad in the database.
 */
public class LaunchPad {

    private int Id;
    private String name;
    private String locationName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String wikiURL;
    private String mapsURL;
    private List<Link> links = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public LaunchPad() {
    }

    /**
     * Gets launch pad id.
     * @return launch pad id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets launch pad id.
     * @param id launch pad id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets launch pad name.
     * @return launch pad name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets launch pad name.
     * @param name launch pad name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets location name of a launch pad.
     * @return location name
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets location name of a launch pad.
     * @param locationName location name
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * Gets latitude coordinates of a launch pad.
     * @return latitude coordinates
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude coordinates of a launch pad.
     * @param latitude latitude coordinates
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude coordinates of a launch pad.
     * @return longitude coordinates
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude coordinates of a launch pad.
     * @param longitude longitude coordinates
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets wikipedia link about a launch pad.
     * @return wikipedia URL
     */
    public String getWikiURL() {
        return wikiURL;
    }

    /**
     * Sets wikipedia link about a launch pad.
     * @param wikiURL wikipedia URL
     */
    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    /**
     * Gets map link of a launch pad.
     * @return map link
     */
    public String getMapsURL() {
        return mapsURL;
    }

    /**
     * Sets map link of a launch pad.
     * @param mapsURL map link
     */
    public void setMapsURL(String mapsURL) {
        this.mapsURL = mapsURL;
    }

    /**
     * Sets a list of Link objects that are assigned to a launch pad.
     * @param links list of Link objects
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a link to a list of links.
     * @param url URL of the link
     * @param rel name of link relation to the launch pad
     */
    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
    }

    /**
     * Gets a list of Link objects that are assigned to a launch pad.
     * @return list of Link objects
     */
    public List<Link> getLinks() {
        return links;
    }
}
