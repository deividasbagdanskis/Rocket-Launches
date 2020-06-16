package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.LaunchPad;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements CRUD operations with launch pad table in a database.
 * @author Lukas Vanglikas
 */
public class LaunchPadDAOImpl implements  LaunchPadDAO {

    private Connection connection;

    /**
     * Creates new with  LaunchPadDAOImpl database connection.
     */
    public LaunchPadDAOImpl() {
        DbConnection dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }
    /**
     * Fetches all the launch pads in the database.
     * @return list of launch pads
     */
    @java.lang.Override
    public List<LaunchPad> getAllLaunchPads() {
        List<LaunchPad> launchPads = new ArrayList<>();

        try {
            String query = "SELECT * FROM launchPad";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                LaunchPad launchPad = readLaunchPad(result);

                launchPads.add(launchPad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return launchPads;
    }
    /**
     * Gets a launch pad with particular id from the database.
     * @param id id of a searchable launch pad
     * @return launch pad object - if a launch pad was found<br>
     *         null - if a launch pad was not found
     */
    @java.lang.Override
    public LaunchPad getLaunchPadById(int id) {
        LaunchPad launchPad = null;

        try {
            String query = "SELECT * FROM launchpad WHERE id = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, id);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                launchPad = readLaunchPad(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return launchPad;
    }

    /**
     * Gets a launch pad with particular name from the database.
     * @param name name of a searchable launch pad
     * @return launch pad object - if a launch pad was found<br>
     *         null - if a launch pad was not found
     */
    @java.lang.Override
    public LaunchPad getLaunchPadByName(String name) {
        LaunchPad launchPad = null;

        try {
            String query = "SELECT * FROM launchPad WHERE `name` = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, name);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                launchPad = readLaunchPad(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return launchPad;
    }

    /**
     * Adds a launch pad to the database and to the repository.
     * @param launchPad launch pad object, which will be added
     * @return id of added launch pad
     */
    @Override
    public int addLaunchPad(LaunchPad launchPad) {
        int launchPadId = 0;
        try {
            String query = "INSERT INTO launchpad (name, locationName, latitude, longitude, wikiURL, mapsURL) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, launchPad.getName());
            prepStmt.setString(2, launchPad.getLocationName());
            prepStmt.setBigDecimal(3, launchPad.getLatitude());
            prepStmt.setBigDecimal(4, launchPad.getLongitude());
            prepStmt.setString(5, launchPad.getWikiURL());
            prepStmt.setString(6, launchPad.getMapsURL());
            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                launchPadId = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return launchPadId;
    }

    @Override
    public boolean deleteLaunchPad(int id) {
        int result = 0;

        try {
            String updateLaunchQuery = "UPDATE launch SET launchPad_id = ? WHERE launchPad_id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(updateLaunchQuery);
            prepStmt.setInt(1, 0);
            prepStmt.setInt(2, id);
            result += prepStmt.executeUpdate();

            String deleteLaunchPadQuery = "DELETE FROM launchPad WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteLaunchPadQuery);
            preparedStatement.setInt(1, id);
            result += preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result > 0;
    }

    /**
     * Reads records from launchpad table.
     * @param result ResulSet of a query
     * @return launchpad object
     * @throws SQLException when reading rows
     */
    private LaunchPad readLaunchPad(ResultSet result) throws SQLException {
        LaunchPad launchPad = new LaunchPad();
        launchPad.setId(result.getInt(1));
        launchPad.setName(result.getString(2));
        launchPad.setLocationName(result.getString(3));
        launchPad.setLatitude(result.getBigDecimal(4));
        launchPad.setLongitude(result.getBigDecimal(5));
        launchPad.setWikiURL(result.getString(6));
        launchPad.setMapsURL(result.getString(7));

        return launchPad;
    }
}
