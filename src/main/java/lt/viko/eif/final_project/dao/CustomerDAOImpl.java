package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;

/**
 * This class implements CRUD operations with customer table in a database.
 */
public class CustomerDAOImpl implements  CustomerDAO {
    private Connection connection;

    /**
     * Creates new with  CustomerDAOImpl database connection.
     */
    public CustomerDAOImpl() {
        DbConnection dbConnection = new DbConnection();
        connection = dbConnection.getConnection();
    }

    @Override
    public Customer getCustomerByName(String name) {
        Customer customer = null;

        try {
            String query = "SELECT * FROM customer WHERE name = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, name);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                customer = readCustomer(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;

        try {
            String query = "SELECT * FROM customer WHERE id = ? LIMIT 1";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, id);

            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                customer = readCustomer(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public int addCustomer(Customer customer) {
        int customerId = 0;
        try {
            String query = "INSERT IGNORE INTO customer (name, countryCode, wikiURL) VALUES (?, ?, ?)";

            PreparedStatement prepStmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, customer.getName());
            prepStmt.setString(2, customer.getCountryCode());
            prepStmt.setString(3, customer.getWikiURL());
            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                customerId = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return customerId;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        int result = 0;
        try {
            String query = "UPDATE customer SET name = ?, countryCode = ?, wikiURL = ? WHERE id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, customer.getName());
            prepStmt.setString(2, customer.getCountryCode());
            prepStmt.setString(3, customer.getWikiURL());
            prepStmt.setInt(4, customer.getId());

            result += prepStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result == 1;
    }

    @Override
    public boolean deleteCustomer(int id) {
        int result = 0;

        try {
            String updateMissionQuery = "UPDATE mission SET customer_id = ? WHERE customer_id = ?";

            PreparedStatement prepStmt = connection.prepareStatement(updateMissionQuery);
            prepStmt.setInt(1, 0);
            prepStmt.setInt(2, id);
            result += prepStmt.executeUpdate();

            String deleteCustomerQuery = "DELETE FROM customer WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteCustomerQuery);
            preparedStatement.setInt(1, id);
            result += preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return result > 0;
    }

    private Customer readCustomer(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getInt(1));
        customer.setName(result.getString(2));
        customer.setCountryCode(result.getString(3));
        customer.setWikiURL(result.getString(4));

        return customer;
    }
}
