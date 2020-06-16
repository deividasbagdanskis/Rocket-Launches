package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

public class Mission {
    private int Id;
    private String name;
    private String description;
    private Launch launch;
    private Customer customer;
    private List<Payload> payloads = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    public void setCustomer(Customer customer) {
        this.customer = customer;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Launch getLaunch() {
        return launch;
    }

    public void setLaunch(Launch launch) {
        this.launch = launch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(List<Payload> payloads) {
        this.payloads = payloads;
    }

    public void addPayload(Payload payload) {
        payloads.add(payload);
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
