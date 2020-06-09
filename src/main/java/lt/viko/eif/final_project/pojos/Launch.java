package lt.viko.eif.final_project.pojos;

public class Launch {
    private int Id;
    private String name;
    private String windowStart;
    private String windowEnd;
    private int rocketId;
    private int launchPadId;
    private String launchServiceProvider;

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

    public String getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(String windowStart) {
        this.windowStart = windowStart;
    }

    public String getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(String windowEnd) {
        this.windowEnd = windowEnd;
    }

    public int getRocketId() {
        return rocketId;
    }

    public void setRocketId(int rocketId) {
        this.rocketId = rocketId;
    }

    public int getLaunchPadId() {
        return launchPadId;
    }

    public void setLaunchPadId(int launchPadId) {
        this.launchPadId = launchPadId;
    }

    public String getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(String launchServiceProvider) {
        this.launchServiceProvider = launchServiceProvider;
    }
}
