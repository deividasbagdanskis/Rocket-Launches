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

    /**
     * Creates new RocketDAOImpl with database connection.
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

    private Mission readMission(ResultSet result) throws SQLException {
        Mission mission = new Mission();
        mission.setId(result.getInt(1));
        mission.setName(result.getString(2));
        mission.setDescription(result.getString(3));
       // Launch launch = LaunchDao.getLaunchById(result.getInt(4));
        //Customer customer = customer.getId(result.getInt(5));

        return mission;
    }

    private List<Payload> getPayloads(int missionId) {
        List<Payload> payloads = new ArrayList<>();

        try {
            String queryStages = "SELECT * FROM payload WHERE mission_Id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(queryStages);
            prepStmt.setInt(1, missionId);

            ResultSet resultStage = prepStmt.executeQuery();

            while (resultStage.next()) {
                Payload payload = new Payload();

                payload.setId(resultStage.getInt(1));
                payload.setDescription(resultStage.getString(3));
                payload.setWeight(resultStage.getInt(4));
                payload.setTotalAmount(resultStage.getInt(5));
                payload.setMissionId(resultStage.getInt(6));

                payloads.add(payload);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payloads;
    }

}
