package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Customer;
import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.Mission;
import lt.viko.eif.final_project.pojos.Payload;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionDAOImpl implements  MissionDAO {

    private Connection connection;
    private LaunchDAO launchDAO = new LaunchDAOImpl();
    private CustomerDAO customerDAO= new CustomerDAOImpl();

    /**
     * Creates new MissionDAOImpl with database connection.
     */
    public MissionDAOImpl() {
        DbConnection dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }
    @Override
    public List<Mission> getAllMission() {
        List<Mission> missions = new ArrayList<>();

        try {
            String query = "SELECT * FROM mission";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {

                Mission mission = readMission(result);

                missions.add(mission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return missions;
    }

    @Override
    public Mission getMissionByName(String name) {
        Mission mission = null;

        try {
            String query = "SELECT * FROM mission WHERE name = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, name);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                mission = readMission(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mission;
    }

    @Override
    public Mission getMission(int id) {
        Mission mission = null;

        try {
            String query = "SELECT * FROM mission WHERE Id = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, id);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                mission = readMission(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mission;
    }

    @Override
    public boolean addMission(Mission mission) {
        int result = 0;
        try {
            String query = "INSERT IGNORE INTO mission (name, description, launch_id, customer_id VALUES (?, ?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, mission.getName());
            prepStmt.setString(2, mission.getDescription());
            prepStmt.setInt(3, mission.getLaunch().getId());
            prepStmt.setInt(4, mission.getCustomer().getId());

            result += prepStmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result == 1;
    }

    @Override
    public boolean updateMission(Mission mission) {
        int result = 0;
        try {
            String query = "UPDATE mission SET description = ?, launch_id = ?, customer_id = ? WHERE name = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, mission.getDescription());
            prepStmt.setInt(2, mission.getLaunch().getId());
            prepStmt.setInt(3, mission.getCustomer().getId());
            prepStmt.setString(4, mission.getName());

            result += prepStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result == 1;
    }

    /**
     * Reads records from mission table.
     * @param result ResultSet of a query
     * @return mission object
     * @throws SQLException
     */
    private Mission readMission(ResultSet result) throws SQLException {
        Mission mission = new Mission();
        mission.setId(result.getInt(1));
        mission.setName(result.getString(2));
        mission.setDescription(result.getString(3));
        Launch launch = launchDAO.getLaunchById(result.getInt(4));
        mission.setLaunch(launch);
        Customer customer = customerDAO.getCustomerById(result.getInt(5));
        mission.setCustomer(customer);

        return mission;
    }





}
