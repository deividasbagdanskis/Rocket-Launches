package lt.viko.eif.final_project.pojos;

import java.util.List;

public class Mission {
    private int Id;
    private String name;
    private String description;
    private Launch launch;
    private Customer customer;
    List<Payload> payloads;

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
}
