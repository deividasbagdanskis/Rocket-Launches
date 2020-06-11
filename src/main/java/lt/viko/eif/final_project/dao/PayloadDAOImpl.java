package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Payload;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayloadDAOImpl implements PayloadDAO {

    private Connection connection;

    /**
     * Creates new with  PayloadDAOImpl database connection.
     */
    public PayloadDAOImpl() {
        DbConnection dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }
    @Override
    public List<Payload> getPayloads(int missionId) {
        List<Payload> payloads = new ArrayList<>();

        try {
            String queryPayloads = "SELECT * FROM payload WHERE mission_Id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(queryPayloads);
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

    @Override
    public boolean addPayload(Payload payload) {
        int result = 0;
        try {
            String query = "INSERT IGNORE INTO payload (description, weight, totalAmount,mission_id) VALUES (?, ?, ?,?)";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, payload.getDescription());
            prepStmt.setInt(2, payload.getWeight());
            prepStmt.setInt(3, payload.getTotalAmount());
            prepStmt.setInt(4, payload.getMissionId());
            result += prepStmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result == 1;
    }

    @Override
    public boolean updatePayload(Payload payload) {
        int result = 0;
        try {
            String query = "UPDATE payload SET description = ?, weight = ?, totalAmount = ?,mission_id = ? WHERE id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, payload.getDescription());
            prepStmt.setInt(2, payload.getWeight());
            prepStmt.setInt(3, payload.getTotalAmount());
            prepStmt.setInt(4, payload.getMissionId());
            prepStmt.setInt(5, payload.getId());
            result += prepStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result == 1;
    }

    @Override
    public boolean deletePayload(int id) {
        int result = 0;

        try {


            String deletePayloadQuery = "DELETE FROM payload WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deletePayloadQuery);
            preparedStatement.setInt(1, id);
            result += preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result > 0;
    }
}
