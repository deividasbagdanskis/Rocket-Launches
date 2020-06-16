package lt.viko.eif.final_project.pojos;

/**
 * Stage class represents a stage in a rocket.
 */
public class Stage {
    private int Id;
    private int rocketId;
    private String type;
    private int numberOfEngines;
    private String engine;
    private int thrust;
    private String fuel;

    /**
     * Empty constructor.
     */
    public Stage() {
    }

    /**
     * Gets id of a stage.
     * @return id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets id of a stage.
     * @param id id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets id of a rocket.
     * @return rocket id
     */
    public int getRocketId() {
        return rocketId;
    }

    /**
     * Sets id of a rocket.
     * @param rocketId rocket id
     */
    public void setRocketId(int rocketId) {
        this.rocketId = rocketId;
    }

    /**
     * Sets stage type.
     * @return stage type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets stage type
     * @param type stage type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets number of engines in a stage.
     * @return number of engines
     */
    public int getNumberOfEngines() {
        return numberOfEngines;
    }

    /**
     * Sets number of engines in a stage
     * @param numberOfEngines number of engines
     */
    public void setNumberOfEngines(int numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    /**
     * Gets stage engine.
     * @return engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Sets stage engine.
     * @param engine engine
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * Gets stage thrust.
     * @return stage thrust
     */
    public int getThrust() {
        return thrust;
    }

    /**
     * Sets stage thrust.
     * @param thrust stage thrust
     */
    public void setThrust(int thrust) {
        this.thrust = thrust;
    }

    /**
     * Gets stage fuel.
     * @return stage fuel
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * Sets stage fuel.
     * @param fuel stage fuel
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
