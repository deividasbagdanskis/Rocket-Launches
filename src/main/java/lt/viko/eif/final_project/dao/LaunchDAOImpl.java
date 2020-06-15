package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.LaunchPad;
import lt.viko.eif.final_project.pojos.Rocket;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements CRUD operations with launch table in a database.
 * @author Deividas Bagdanskis
 */
public class LaunchDAOImpl implements LaunchDAO {
    private Connection connection;
    private RocketDAO rocketDAO = new RocketDAOImpl();
    private LaunchPadDAO launchPadDAO = new LaunchPadDAOImpl();

    /**
     * Creates new LaunchDAOImpl with database connection.
     */
    public LaunchDAOImpl() {
        DbConnection dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }

    /**
     * Gets all rockets in the database.
     * @return a list of launches
     */
    @Override
    public List<Launch> getAllLaunches() {
        List<Launch> launches = new ArrayList<>();

        try {
            String query = "SELECT * FROM launch";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Launch launch = readLaunch(result);

                launches.add(launch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return launches;
    }

    /**
     * Gets a launch with particular name from the database.
     * @param name name of a searchable launch
     * @return rocket object - if a launch was found<br>
     *         null - if a launch was not found
     */
    @Override
    public List<Launch> getLaunchesByName(String name) {
        List<Launch> launches = new ArrayList<>();

        try {
            String query = "SELECT * FROM launch WHERE name LIKE CONCAT('%', ?, '%')";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, name);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                Launch launch = readLaunch(result);

                launches.add(launch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return launches;
    }

    /**
     * Gets a launch with particular id from the database.
     * @param id id of a searchable launch
     * @return rocket object - if a launch was found<br>
     *         null - if a launch was not found
     */
    @Override
    public Launch getLaunchById(int id) {
        Launch launch = null;

        try {
            String query = "SELECT * FROM launch WHERE id = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, id);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                launch = readLaunch(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return launch;
    }

    /**
     * Gets a specified number of upcoming launches.
     * @param amount number of upcoming launches
     * @return a list of upcoming launches
     */
    @Override
    public List<Launch> getUpcomingLaunches(int amount) {
        List<Launch> launches = new ArrayList<>();

        try {
            String query = "SELECT * FROM launch WHERE windowStart > CURRENT_TIMESTAMP ORDER BY windowStart DESC LIMIT ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, amount);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                Launch launch = readLaunch(result);

                launches.add(launch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return launches;
    }

    /**
     * Gets launches between given start date and end date.
     * @param startDate start date
     * @param endDate end date
     * @return a list of launches
     */
    @Override
    public List<Launch> getLaunchesByDates(String startDate, String endDate) {
        List<Launch> launches = new ArrayList<>();

        try {
            String query = "SELECT * FROM launch WHERE DATE(windowStart) > ? AND DATE(windowStart) < ?";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, startDate);
            prepStmt.setString(2, endDate);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                Launch launch = readLaunch(result);

                launches.add(launch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return launches;
    }

    /**
     * Adds a rocket to the database.
     * @param launch launch object, which will be added
     * @return id of added launch
     */
    @Override
    public int addLaunch(Launch launch) {
        int launchId = 0;
        int rocketId = 0;
        int launchPadId = 0;

        try {
            rocketId = launch.getRocket().getId();
            launchPadId = launch.getLaunchPad().getId();
            if (rocketDAO.getRocketsByName(launch.getRocket().getName()).size() == 0) {
                rocketId = rocketDAO.addRocket(launch.getRocket());
            }

            if (launchPadDAO.getLaunchPadByName(launch.getLaunchPad().getName()) == null) {
                launchPadId = launchPadDAO.addLaunchPad(launch.getLaunchPad());
            }

            String query = "INSERT IGNORE INTO launch (name, windowStart, windowEnd, rocket_Id, launchPad_Id, " +
                    "launchServiceProvider) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, launch.getName());
            prepStmt.setTimestamp(2, Timestamp.from(launch.getWindowStart()));
            prepStmt.setTimestamp(3, Timestamp.from(launch.getWindowEnd()));
            prepStmt.setInt(4, rocketId);
            prepStmt.setInt(5, launchPadId);
            prepStmt.setString(6, launch.getLaunchServiceProvider());
            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                launchId = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return launchId;
    }

    /**
     * Updates a launch with a matching name in the database.
     * @param launch launch object with updated data, but with the same name
     * @return true - if launch were updated in the database<br>
     *         false - if operation failed
     */
    @Override
    public boolean updateLaunch(Launch launch) {
        int result = 0;

        try {
            String query = "UPDATE launch SET windowStart = ?, windowEnd = ?, rocket_Id = ?, launchPad_id = ?, " +
                    "launchServiceProvider = ? WHERE name = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setTimestamp(1, Timestamp.from(launch.getWindowStart()));
            prepStmt.setTimestamp(2, Timestamp.from(launch.getWindowEnd()));
            prepStmt.setInt(3, launch.getRocket().getId());
            prepStmt.setInt(4, launch.getLaunchPad().getId());
            prepStmt.setString(5, launch.getLaunchServiceProvider());
            prepStmt.setString(6, launch.getName());

            result += prepStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result == 1;
    }

    /**
     * Deletes a specified launch from the database.
     * @param id id of a launch, which will be deleted
     * @return true - if a launch was deleted from the database<br>
     *         false - if operation failed
     */
    @Override
    public boolean deleteLaunch(int id) {
        int result = 0;

        try {
            String updateMissionQuery = "UPDATE mission SET launch_id = ? WHERE launch_id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(updateMissionQuery);
            prepStmt.setInt(1, 0);
            prepStmt.setInt(2, id);
            result += prepStmt.executeUpdate();

            String deleteLaunchQuery = "DELETE FROM launch WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteLaunchQuery);
            preparedStatement.setInt(1, id);
            result += preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result > 0;
    }

    /**
     * Reads records from launch table.
     * @param result ResultSet of a query
     * @return launch object
     * @throws SQLException
     */
    private Launch readLaunch(ResultSet result) throws SQLException {
        Launch launch = new Launch();
        launch.setId(result.getInt(1));
        launch.setName(result.getString(2));
        launch.setWindowStart(result.getTimestamp(3).toInstant());
        launch.setWindowEnd(result.getTimestamp(4).toInstant());
        Rocket rocket = rocketDAO.getRocketById(result.getInt(5));
        launch.setRocket(rocket);
        LaunchPad launchPad = launchPadDAO.getLaunchPadById(result.getInt(6));
        launch.setLaunchServiceProvider(result.getString(7));

        return launch;
    }
}
