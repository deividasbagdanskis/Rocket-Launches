package lt.viko.eif.final_project.pojos;

public class LaunchPad {

    private int Id;
    private String name;
    private String locationName;
    private double latitude;
    private double longtitude;
    private String wikiURL;
    private String mapsURL;

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

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
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
}
