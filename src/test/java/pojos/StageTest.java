package pojos;

import lt.viko.eif.final_project.pojos.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tomas Jokubauskas
 */
class StageTest {
    private Stage stage;

    @BeforeEach
    void setUp() {
        stage = new Stage();
    }

    @AfterEach
    void tearDown() {
        stage = null;
    }

    @Test
    void getId() {
        stage.setId(7);
        assertEquals(7, stage.getId());
    }

    @Test
    void setId() {
        stage.setId(7);
        assertEquals(7, stage.getId());
    }

    @Test
    void getRocketId() {
        stage.setRocketId(7);
        assertEquals(7, stage.getRocketId());
    }

    @Test
    void setRocketId() {
        stage.setRocketId(7);
        assertEquals(7, stage.getRocketId());
    }

    @Test
    void getType() {
        stage.setType("TestType");
        assertEquals("TestType", stage.getType());
    }

    @Test
    void setType() {
        stage.setType("TestType");
        assertEquals("TestType", stage.getType());
    }

    @Test
    void getNumberOfEngines() {
        stage.setNumberOfEngines(7);
        assertEquals(7, stage.getNumberOfEngines());
    }

    @Test
    void setNumberOfEngines() {
        stage.setNumberOfEngines(7);
        assertEquals(7, stage.getNumberOfEngines());
    }

    @Test
    void getEngine() {
        stage.setEngine("TestEngine");
        assertEquals("TestEngine", stage.getEngine());
    }

    @Test
    void setEngine() {
        stage.setEngine("TestEngine");
        assertEquals("TestEngine", stage.getEngine());
    }

    @Test
    void getThrust() {
        stage.setThrust(7);
        assertEquals(7, stage.getThrust());
    }

    @Test
    void setThrust() {
        stage.setThrust(7);
        assertEquals(7, stage.getThrust());
    }

    @Test
    void getFuel() {
        stage.setFuel("TestFuel");
        assertEquals("TestFuel", stage.getFuel());
    }

    @Test
    void setFuel() {
        stage.setFuel("TestFuel");
        assertEquals("TestFuel", stage.getFuel());
    }
}