package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Customer;
import lt.viko.eif.final_project.pojos.Rocket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImplTest {

    private CustomerDAO customerDAO;
    Customer testCustomer = new Customer();

    @BeforeEach
    void setUp() {
        customerDAO = new CustomerDAOImpl();
        testCustomer.setName("TestName");
        testCustomer.setCountryCode("LT");
        testCustomer.setWikiURL("TestWikiURL");
    }

    @AfterEach
    void tearDown() {
        customerDAO = null;
    }

    @Test
    void getCustomerById() {
        String customerName = "Elon Musk";
        testCustomer.setName(customerName);
        int id = customerDAO.addCustomer(testCustomer);
        assertEquals(customerName, customerDAO.getCustomerById(id).getName());
        customerDAO.deleteCustomer(id);
    }

    @Test
    void addCustomer() {
        int id = 0;

        id = customerDAO.addCustomer(testCustomer);
        assertEquals(testCustomer.getName(), customerDAO.getCustomerById(id).getName());
        customerDAO.deleteCustomer(id);
    }

    @Test
    void updateCustomer(){
        int id = 0;

        id = customerDAO.addCustomer(testCustomer);
        testCustomer.setId(id);
        testCustomer.setName("ChangedTest");
        assertEquals(true, customerDAO.updateCustomer(testCustomer));
        customerDAO.deleteCustomer(id);
    }

    @Test
    void deleteCustomer() {
        int id = 0;

        id = customerDAO.addCustomer(testCustomer);
        assertEquals(true, customerDAO.deleteCustomer(id));
    }

}
