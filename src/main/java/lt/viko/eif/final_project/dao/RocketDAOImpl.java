package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Rocket;
import lt.viko.eif.final_project.pojos.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements CRUD operations with rocket table in a database.
 * @author Deividas Bagdanskis
 */
public class RocketDAOImpl implements RocketDAO {
    private Connection connection;

    /**
     * Creates new RocketDAOImpl with database connection.
     */
    public RocketDAOImpl() {
        DbConnection dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }

    /**
     * Fetches all the rockets in the database.
     * @return list of rockets
     */
    @Override
    public List<Rocket> getAllRockets() {
        List<Rocket> rockets = new ArrayList<>();

        try {
            String query = "SELECT * FROM rocket";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {

                Rocket rocket = readRocket(result);

                rockets.add(rocket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rockets;
    }

    /**
     * Gets rockets with particular name from the database.
     * @param name name of a searchable rocket
     * @return a list of rockets
     */
    @Override
    public List<Rocket> getRocketsByName(String name) {
        List<Rocket> rockets = new ArrayList<>();

        try {
            String query = "SELECT * FROM rocket WHERE name LIKE CONCAT('%', ?, '%')";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, name);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                Rocket rocket = readRocket(result);
                rockets.add(rocket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rockets;
    }

    /**
     * Gets a rocket with particular id from the database.
     * @param id id of a searchable rocket
     * @return rocket object - if a rocket was found<br>
     *         null - if a rocket was not found
     */
    @Override
    public Rocket getRocketById(int id) {
        Rocket rocket = null;

        try {
            String query = "SELECT * FROM rocket WHERE Id = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setInt(1, id);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                rocket = readRocket(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rocket;
    }

    /**
     * Adds a rocket to the database.
     * @param rocket rocket object, which will be added
     * @return id of added rocket
     */
    @Override
    public int addRocket(Rocket rocket) {
        int rocketId = 0;
        try {
            String query = "INSERT INTO rocket (name, type, manufacturer, countryOfOrigin, height, diameter, mass,"
                    + " numberOfStages, payloadToLEO, payloadToGTO, wikiURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, rocket.getName());
            prepStmt.setString(2, rocket.getType());
            prepStmt.setString(3, rocket.getManufacturer());
            prepStmt.setString(4, rocket.getCountryOfOrigin());
            prepStmt.setDouble(5, rocket.getHeight());
            prepStmt.setDouble(6, rocket.getDiameter());
            prepStmt.setInt(7, rocket.getMass());
            prepStmt.setInt(8, rocket.getNumberOfStages());
            prepStmt.setInt(9, rocket.getPayloadToLEO());
            prepStmt.setInt(10, rocket.getPayloadToGTO());
            prepStmt.setString(11, rocket.getWikiURL());
            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                rocketId = generatedKeys.getInt(1);
            }

            if (rocket.getStages().size() != 0) {
                for (Stage stage : rocket.getStages()) {
                    stage.setRocketId(rocketId);
                    addStage(stage);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return rocketId;
    }

    /**
     * Updates a rocket with a matching name in the database.
     * @param rocket rocket object with updated data, but with the same name
     * @return true - if rocket and stages were updated in the database<br>
     *         false - if operation failed
     */
    @Override
    public boolean updateRocket(Rocket rocket) {
        int result = 0;
        try {
            String query = "UPDATE rocket SET type = ?, manufacturer = ?, countryOfOrigin = ?, height = ?, diameter = ?,"
                    + " mass = ?, numberOfStages = ?, payloadToLEO = ?, payloadToGTO = ?, wikiURL = ? WHERE 'name' = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, rocket.getType());
            prepStmt.setString(2, rocket.getManufacturer());
            prepStmt.setString(3, rocket.getCountryOfOrigin());
            prepStmt.setDouble(4, rocket.getHeight());
            prepStmt.setDouble(5, rocket.getDiameter());
            prepStmt.setInt(6, rocket.getMass());
            prepStmt.setInt(7, rocket.getNumberOfStages());
            prepStmt.setInt(8, rocket.getPayloadToLEO());
            prepStmt.setInt(9, rocket.getPayloadToGTO());
            prepStmt.setString(10, rocket.findLink("wikiURL"));
            prepStmt.setString(11, rocket.getName());
            result += prepStmt.executeUpdate();

            if (rocket.getStages().size() != 0) {
                for (Stage stage : rocket.getStages()) {
                    updateStage(stage);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result == 1;
    }

    /**
     * Deletes a specified rocket from the database.
     * @param id id of a rocket, which will be deleted
     * @return true - if rocket and stages were deleted from the database<br>
     *         false - if operation failed
     */
    @Override
    public boolean deleteRocket(int id) {
        int result = 0;

        try {
            String deleteStagesQuery = "DELETE FROM stage WHERE rocket_Id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(deleteStagesQuery);
            prepStmt.setInt(1, id);
            result += prepStmt.executeUpdate();

            String deleteRocketQuery = "DELETE FROM rocket WHERE Id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteRocketQuery);
            preparedStatement.setInt(1, id);
            result += preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result > 0;
    }

    /**
     * Reads records from rocket table.
     * @param result ResultSet of a query
     * @return rocket object
     * @throws SQLException
     */
    private Rocket readRocket(ResultSet result) throws SQLException {
        Rocket rocket = new Rocket();
        rocket.setId(result.getInt(1));
        rocket.setName(result.getString(2));
        rocket.setManufacturer(result.getString(3));
        rocket.setType(result.getString(4));
        rocket.setCountryOfOrigin(result.getString(5));
        rocket.setHeight(result.getDouble(6));
        rocket.setDiameter(result.getDouble(7));
        rocket.setMass(result.getInt(8));
        rocket.setNumberOfStages(result.getInt(9));
        rocket.setPayloadToLEO(result.getInt(10));
        rocket.setPayloadToGTO(result.getInt(11));
        rocket.setStages(getStages(rocket.getId()));
        rocket.setWikiURL(result.getString(12));

        return rocket;
    }

    /**
     * Gets stages of a particular rocket.
     * @param rocketId id of a rocket
     * @return list of stages
     */
    private List<Stage> getStages(int rocketId) {
        List<Stage> stages = new ArrayList<>();

        try {
            String queryStages = "SELECT * FROM stage WHERE rocket_Id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(queryStages);
            prepStmt.setInt(1, rocketId);

            ResultSet resultStage = prepStmt.executeQuery();

            while (resultStage.next()) {
                Stage stage = new Stage();

                stage.setId(resultStage.getInt(1));
                stage.setType(resultStage.getString(3));
                stage.setNumberOfEngines(resultStage.getInt(4));
                stage.setEngine(resultStage.getString(5));
                stage.setThrust(resultStage.getInt(6));
                stage.setFuel(resultStage.getString(7));

                stages.add(stage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stages;
    }

    private void addStage(Stage stage) {
        try {
            String query = "INSERT INTO stage (rocket_Id, type, numberOfEngines, engine, thrust, fuel) " +
                    "VALUES (?, ?, ?, ?, ?,?)";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, stage.getRocketId());
            prepStmt.setString(2, stage.getType());
            prepStmt.setInt(3, stage.getNumberOfEngines());
            prepStmt.setString(4, stage.getEngine());
            prepStmt.setInt(5, stage.getThrust());
            prepStmt.setString(6, stage.getFuel());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStage(Stage stage) {
        try {
            String query = "UPDATE stage SET type = ?, numberOfEngines = ?, engine = ?, thrust = ?, fuel = ? " +
                    "WHERE rocket_Id = ? AND id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, stage.getType());
            prepStmt.setInt(2, stage.getNumberOfEngines());
            prepStmt.setString(3, stage.getEngine());
            prepStmt.setInt(4, stage.getThrust());
            prepStmt.setString(5, stage.getFuel());
            prepStmt.setInt(6, stage.getRocketId());
            prepStmt.setInt(7, stage.getId());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
