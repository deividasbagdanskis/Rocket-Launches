package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Customer;
import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.Mission;
import lt.viko.eif.final_project.pojos.Payload;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements CRUD operations with mission table in a database.
 */
public class MissionDAOImpl implements  MissionDAO {

    private Connection connection;
    private LaunchDAO launchDAO = new LaunchDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private PayloadDAO payloadDAO = new PayloadDAOImpl();

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
    public List<Mission> getMissionsByName(String name) {
         List<Mission> missions = new ArrayList<>();

        try {
            String query = "SELECT * FROM mission WHERE `name` LIKE CONCAT('%', ?, '%')";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, name);

            ResultSet result = prepStmt.executeQuery();

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
    public Mission getMissionById(int id) {
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
            //int launchId = launchDAO.addLaunch(mission.getLaunch());
            //int customerId = customerDAO.addCustomer(mission.getCustomer());
            int launchId = mission.getLaunch().getId();
            int customerId = mission.getCustomer().getId();

            if(launchDAO.getLaunchesByName(mission.getLaunch().getName()).size() == 0){
                launchId = launchDAO.addLaunch(mission.getLaunch());
            } else {
                launchId = launchDAO.getLaunchesByName(mission.getLaunch().getName()).get(0).getId();
            }

            if(customerDAO.getCustomerByName(mission.getCustomer().getName()) == null) {
                customerId = customerDAO.addCustomer(mission.getCustomer());
            } else {
                customerId = customerDAO.getCustomerByName(mission.getCustomer().getName()).getId();
            }

            String query = "INSERT IGNORE INTO mission (`name`, description, launch_id, customer_id) VALUES (?, ?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, mission.getName());
            prepStmt.setString(2, mission.getDescription());
            prepStmt.setInt(3, launchId);
            prepStmt.setInt(4, customerId);

            result += prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            int missionId = 0;

            if (generatedKeys.next()) {
                missionId = generatedKeys.getInt(1);
            }

            if (mission.getPayloads().size() != 0) {
                for (Payload payload : mission.getPayloads()) {
                    payload.setMissionId(missionId);
                    payloadDAO.addPayload(payload);
                }
            }
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
            String query = "UPDATE mission SET description = ?, launch_id = ?, customer_id = ? WHERE `name` = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, mission.getDescription());
            prepStmt.setInt(2, mission.getLaunch().getId());
            prepStmt.setInt(3, mission.getCustomer().getId());
            prepStmt.setString(4, mission.getName());

            result += prepStmt.executeUpdate();

            for (Payload payload : mission.getPayloads()) {
                payloadDAO.updatePayload(payload);
            }
            customerDAO.updateCustomer(mission.getCustomer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result == 1;
    }

    @Override
    public boolean deleteMission(String name) {
        int result = 0;

        try {
            Mission mission = getMissionsByName(name).get(0);
            payloadDAO.deletePayloadsByMission(mission.getId());

            String deleteMissionQuery = "DELETE FROM mission WHERE `name` = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteMissionQuery);
            preparedStatement.setString(1, name);
            result += preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result > 0;
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
        mission.setPayloads(payloadDAO.getPayloads(mission.getId()));

        return mission;
    }
}
