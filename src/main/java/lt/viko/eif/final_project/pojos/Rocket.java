package lt.viko.eif.final_project.pojos;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

public class Rocket {
    private int Id;
    private String name;
    private String manufacturer;
    private String type;
    private String countryOfOrigin;
    private double height;
    private double diameter;
    private int mass;
    private int numberOfStages;
    private int payloadToLEO;
    private int payloadToGTO;
    private String wikiURL;
    private List<Stage> stages = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    public Rocket() {
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getNumberOfStages() {
        return numberOfStages;
    }

    public void setNumberOfStages(int numberOfStages) {
        this.numberOfStages = numberOfStages;
    }

    public int getPayloadToLEO() {
        return payloadToLEO;
    }

    public void setPayloadToLEO(int payloadToLEO) {
        this.payloadToLEO = payloadToLEO;
    }

    public int getPayloadToGTO() {
        return payloadToGTO;
    }

    public void setPayloadToGTO(int payloadToGTO) {
        this.payloadToGTO = payloadToGTO;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
            Link link = Link.fromUri(url).rel(rel).build();
            this.links.add(link);
    }
}
