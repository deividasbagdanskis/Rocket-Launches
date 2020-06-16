package pojos;

import lt.viko.eif.final_project.pojos.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tomas Jokubauskas
 */
class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @AfterEach
    void tearDown() {
        customer = null;
    }

    @Test
    void getId() {
        customer.setId(7);
        assertEquals(7, customer.getId());
    }

    @Test
    void setId() {
        customer.setId(7);
        assertEquals(7, customer.getId());
    }

    @Test
    void getName() {
        customer.setName("TestName");
        assertEquals("TestName", customer.getName());
    }

    @Test
    void setName() {
        customer.setName("TestName");
        assertEquals("TestName", customer.getName());
    }

    @Test
    void getCountryCode() {
        customer.setCountryCode("LT");
        assertEquals("LT", customer.getCountryCode());
    }

    @Test
    void setCountryCode() {
        customer.setCountryCode("LT");
        assertEquals("LT", customer.getCountryCode());
    }

    @Test
    void getWikiURL() {
        customer.setWikiURL("https://example.com");
        assertEquals("https://example.com", customer.getWikiURL());
    }

    @Test
    void setWikiURL() {
        customer.setWikiURL("https://example.com");
        assertEquals("https://example.com", customer.getWikiURL());
    }
}