package lt.viko.eif.final_project.pojos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    private Rocket rocket;

    @BeforeEach
    void setUp() {
        rocket = new Rocket();
    }

    @AfterEach
    void tearDown() {
        rocket = null;
    }

    @Test
    void getId() {
        rocket.setId(7);
        assertEquals(7, rocket.getId());
    }

    @Test
    void setId() {
        rocket.setId(7);
        assertEquals(7, rocket.getId());
    }

    @Test
    void getName() {
        rocket.setName("TestName");
        assertEquals("TestName", rocket.getName());
    }

    @Test
    void setName() {
        rocket.setName("TestName");
        assertEquals("TestName", rocket.getName());
    }

    @Test
    void getManufacturer() {
        rocket.setManufacturer("TestManufacturer");
        assertEquals("TestManufacturer", rocket.getManufacturer());
    }

    @Test
    void setManufacturer() {
        rocket.setManufacturer("TestManufacturer");
        assertEquals("TestManufacturer", rocket.getManufacturer());
    }

    @Test
    void getType() {
        rocket.setType("TestType");
        assertEquals("TestType", rocket.getType());
    }

    @Test
    void setType() {
        rocket.setType("TestType");
        assertEquals("TestType", rocket.getType());
    }

    @Test
    void getOriginOfCountry() {
        rocket.setCountryOfOrigin("LT");
        assertEquals("LT", rocket.getCountryOfOrigin());
    }

    @Test
    void setOriginOfCountry() {
        rocket.setCountryOfOrigin("LT");
        assertEquals("LT", rocket.getCountryOfOrigin());
    }

    @Test
    void getHeight() {
        rocket.setHeight(10d);
        assertEquals(10d, rocket.getHeight());
    }

    @Test
    void setHeight() {
        rocket.setHeight(10d);
        assertEquals(10d, rocket.getHeight());
    }

    @Test
    void getDiameter() {
        rocket.setDiameter(10d);
        assertEquals(10d, rocket.getDiameter());
    }

    @Test
    void setDiameter() {
        rocket.setDiameter(10d);
        assertEquals(10d, rocket.getDiameter());
    }

    @Test
    void getMass() {
        rocket.setMass(10);
        assertEquals(10, rocket.getMass());
    }

    @Test
    void setMass() {
        rocket.setMass(10);
        assertEquals(10, rocket.getMass());
    }

    @Test
    void getNumberOfStages() {
        rocket.setNumberOfStages(7);
        assertEquals(7, rocket.getNumberOfStages());
    }

    @Test
    void setNumberOfStages() {
        rocket.setNumberOfStages(7);
        assertEquals(7, rocket.getNumberOfStages());
    }

    @Test
    void getPayloadToLEO() {
        rocket.setPayloadToLEO(1);
        assertEquals(1, rocket.getPayloadToLEO());
    }

    @Test
    void setPayloadToLEO() {
        rocket.setPayloadToLEO(1);
        assertEquals(1, rocket.getPayloadToLEO());
    }

    @Test
    void getPayloadToGTO() {
        rocket.setPayloadToGTO(1);
        assertEquals(1, rocket.getPayloadToGTO());
    }

    @Test
    void setPayloadToGTO() {
        rocket.setPayloadToGTO(1);
        assertEquals(1, rocket.getPayloadToGTO());
    }
}