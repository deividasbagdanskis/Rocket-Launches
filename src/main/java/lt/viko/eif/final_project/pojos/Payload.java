package lt.viko.eif.final_project.pojos;

/**
 * Payload class represents a payload in the database.
 */
public class Payload {
    private int Id;
    private String description;
    private int weight;
    private int totalAmount;
    private int missionId;

    /**
     * Gets payload id.
     * @return payload id
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets payload id.
     * @param id payload id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets description about a payload.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description about a payload.
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets weight of a payload.
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets weight of a payload.
     * @param weight weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets total amount of payload units.
     * @return amount of payload units
     */
    public int getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets total amount of payload units.
     * @param totalAmount amount of payload units
     */
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Gets mission id of a mission, that the payload is assigned to.
     * @return mission id
     */
    public int getMissionId() {
        return missionId;
    }

    /**
     * Sets mission id of a mission, that the payload is assigned to.
     * @param missionId mission id
     */
    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }
}
