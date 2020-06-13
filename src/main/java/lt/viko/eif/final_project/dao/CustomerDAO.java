package lt.viko.eif.final_project.dao;
import lt.viko.eif.final_project.pojos.Customer;

/**
 * The user of this interface has full control of CRUD operations with customer table.
 */
public interface CustomerDAO {

    /**
     * Gets a customer  with particular id from the database.
     * @param id id of a searchable customer
     * @return launch pad object - if a customer was found<br>
     *         null - if a customer was not found
     */
    Customer getCustomerById(int id);


    /**
     * Adds a customer to the database.
     * @param customer customer object, which will be added
     * @return id of added customer
     */
    int addCustomer(Customer customer);

    /**
     * Updates a customer with a matching id in the database.
     * @param customer customer object with updated data, but with the same id
     * @return true - if customer updated in the database<br>
     *         false - if operation failed
     */
    boolean updateCustomer(Customer customer);

    /**
     * Deletes a specified customer from the database.
     * @param id id of a customer, which will be deleted
     * @return true - if a customer was deleted from the database<br>
     *         false - if operation failed
     */
    boolean deleteCustomer(int id);
}
