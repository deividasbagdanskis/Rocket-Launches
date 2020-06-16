package pojos;

import lt.viko.eif.final_project.pojos.Payload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tomas Jokubauskas
 */
class PayloadTest {
    private Payload payload;

    @BeforeEach
    void setUp() {
        payload = new Payload();
    }

    @AfterEach
    void tearDown() {
        payload = null;
    }

    @Test
    void getId() {
        payload.setId(7);
        assertEquals(7, payload.getId());
    }

    @Test
    void setId() {
        payload.setId(7);
        assertEquals(7, payload.getId());
    }

    @Test
    void getDescription() {
        payload.setDescription("TestDescription");
        assertEquals("TestDescription", payload.getDescription());
    }

    @Test
    void setDescription() {
        payload.setDescription("TestDescription");
        assertEquals("TestDescription", payload.getDescription());
    }

    @Test
    void getWeight() {
        payload.setWeight(7);
        assertEquals(7, payload.getWeight());
    }

    @Test
    void setWeight() {
        payload.setWeight(7);
        assertEquals(7, payload.getWeight());
    }

    @Test
    void getTotalAmount() {
        payload.setTotalAmount(7);
        assertEquals(7, payload.getTotalAmount());
    }

    @Test
    void setTotalAmount() {
        payload.setTotalAmount(7);
        assertEquals(7, payload.getTotalAmount());
    }

    @Test
    void getMissionId() {
        payload.setMissionId(7);
        assertEquals(7, payload.getMissionId());
    }

    @Test
    void setMissionId() {
        payload.setMissionId(7);
        assertEquals(7, payload.getMissionId());
    }
}