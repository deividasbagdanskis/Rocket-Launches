package lt.viko.eif.final_project.pojos;

public class Rocket {
    private int Id;
    private String name;
    private String manufacturer;
    private String type;
    private String originOfCountry;
    private double height;
    private double diameter;
    private int mass;
    private int numberOfStages;
    private int payloadToLEO;
    private int payloadToGTO;
    private char status;
    private String wikiURL;

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

    public String getOriginOfCountry() {
        return originOfCountry;
    }

    public void setOriginOfCountry(String originOfCountry) {
        this.originOfCountry = originOfCountry;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }
}
