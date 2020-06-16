package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Mission class represents a mission in the database.
 */
public class Mission {
    private int Id;
    private String name;
    private String description;
    private Launch launch;
    private Customer customer;
    private List<Payload> payloads = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    /**
     * Sets customer object of a mission.
     * @param customer customer object
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets mission id.
     * @return mission id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets mission id.
     * @param id mission id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets mission name.
     * @return mission name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets mission name.
     * @param name mission name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets mission description.
     * @return mission description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets mission description.
     * @param description mission description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets launch object of a mission.
     * @return launch object
     */
    public Launch getLaunch() {
        return launch;
    }

    /**
     * Sets launch object of a mission.
     * @param launch launch object
     */
    public void setLaunch(Launch launch) {
        this.launch = launch;
    }

    /**
     * Gets customer object of a mission.
     * @return customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets a list of payloads of a mission.
     * @return list of payloads
     */
    public List<Payload> getPayloads() {
        return payloads;
    }

    /**
     * Sets a list of payloads of a mission.
     * @param payloads list of payloads
     */
    public void setPayloads(List<Payload> payloads) {
        this.payloads = payloads;
    }

    /**
     * Adds a payload to the mission.
     * @param payload payload object
     */
    public void addPayload(Payload payload) {
        payloads.add(payload);
    }

    /**
     * Sets a list of Link objects that are assigned to a mission.
     * @param links list of Link objects
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Adds a link to a list of links.
     * @param url URL of the link
     * @param rel name of link relation to the mission
     */
    public void addLink(String url, String rel) {
        Link link = Link.fromUri(url).rel(rel).build();
        this.links.add(link);
    }

    /**
     * Gets a list of Link objects that are assigned to a mission.
     * @return  list of Link objects
     */
    public List<Link> getLinks() {
        return links;
    }
}
