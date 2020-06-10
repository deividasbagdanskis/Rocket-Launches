package lt.viko.eif.final_project.dao;
import lt.viko.eif.final_project.pojos.LaunchPad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
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
            String query = "SELECT * FROM launchpad";
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
            String query = "SELECT * FROM launchpad WHERE Id = ? LIMIT 1";

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
            String query = "SELECT * FROM launchpad WHERE name = ? LIMIT 1";

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
     * @return true - if launch pad  were inserted to the database<br>
     *         false - if operation failed
     */
    @java.lang.Override
    public boolean addLaunchPad(LaunchPad launchPad)
    {
        int result = 0;
        try {
            String query = "INSERT IGNORE INTO launchpad (name, locationName, latidude,longitude, wikiURL, mapsURL) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, launchPad.getName());
            prepStmt.setString(2, launchPad.getLocationName());
            prepStmt.setDouble(3, launchPad.getLatitude());
            prepStmt.setDouble(4, launchPad.getLongitude());
            prepStmt.setString(5, launchPad.getWikiURL());
            prepStmt.setString(6, launchPad.getMapsURL());
            result += prepStmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result == 1;
    }
    /**
     * Reads records from launchpad table.
     * @param result ResulSet of a query
     * @return launchpad object
     * @throws SQLException
     */
    private LaunchPad readLaunchPad(ResultSet result) throws SQLException {
        LaunchPad launchPad = new LaunchPad();
        launchPad.setId(result.getInt(1));
        launchPad.setName(result.getString(2));
        launchPad.setLocationName(result.getString(3));
        launchPad.setLatitude(result.getDouble(4));
        launchPad.setLongitude(result.getDouble(5));
        launchPad.setWikiURL(result.getString(6));
        launchPad.setMapsURL(result.getString(7));

        return launchPad;
    }
}
