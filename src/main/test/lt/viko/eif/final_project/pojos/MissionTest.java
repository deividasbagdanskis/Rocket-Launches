package lt.viko.eif.final_project.pojos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Link;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tomas Jokubauskas
 */
class MissionTest {
    private Mission mission;

    @BeforeEach
    void setUp() {
        mission = new Mission();
    }

    @AfterEach
    void tearDown() {
        mission = null;
    }

    @Test
    void getId() {
        mission.setId(7);
        assertEquals(7, mission.getId());
    }

    @Test
    void setId() {
        mission.setId(7);
        assertEquals(7, mission.getId());
    }

    @Test
    void getName() {
        mission.setName("TestName");
        assertEquals("TestName", mission.getName());
    }

    @Test
    void setName() {
        mission.setName("TestName");
        assertEquals("TestName", mission.getName());
    }

    @Test
    void getDescription() {
        mission.setDescription("TestDescription");
        assertEquals("TestDescription", mission.getDescription());
    }

    @Test
    void setDescription() {
        mission.setDescription("TestDescription");
        assertEquals("TestDescription", mission.getDescription());
    }

    @Test
    void getLaunch() {
        Launch launch = new Launch();
        launch.setName("TestLaunch");
        mission.setLaunch(launch);
        assertEquals(launch, mission.getLaunch());
    }

    @Test
    void setLaunch() {
        Launch launch = new Launch();
        launch.setName("TestLaunch");
        mission.setLaunch(launch);
        assertEquals(launch, mission.getLaunch());
    }

    @Test
    void setCustomer() {
        Customer customer = new Customer();
        customer.setName("TestCustomer");
        mission.setCustomer(customer);
        assertEquals(customer, mission.getCustomer());
    }

    @Test
    void getCustomer() {
        Customer customer = new Customer();
        customer.setName("TestCustomer");
        mission.setCustomer(customer);
        assertEquals(customer, mission.getCustomer());
    }

    @Test
    void getPayloads() {
        mission.addPayload(new Payload());
        assertEquals(1, mission.getPayloads().size());
    }

    @Test
    void addPayload() {
        mission.addPayload(new Payload());
        assertEquals(1, mission.getPayloads().size());
    }

    @Test
    void addLink() {
        mission.addLink("https://example.com", "rel");
        assertEquals(1, mission.getLinks().size());
    }

    @Test
    void getLinks() {
        mission.addLink("https://example.com", "rel");
        assertEquals(1, mission.getLinks().size());
    }
}