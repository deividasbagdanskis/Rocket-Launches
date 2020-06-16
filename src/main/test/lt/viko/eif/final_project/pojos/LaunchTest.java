package lt.viko.eif.final_project.pojos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tomas Jokubauskas
 */
class LaunchTest {
    private Launch launch;

    @BeforeEach
    void setUp() {
        launch = new Launch();
    }

    @AfterEach
    void tearDown() {
        launch = null;
    }

    @Test
    void getId() {
        launch.setId(7);
        assertEquals(7, launch.getId());
    }

    @Test
    void setId() {
        launch.setId(7);
        assertEquals(7, launch.getId());
    }

    @Test
    void getName() {
        launch.setName("TestName");
        assertEquals("TestName", launch.getName());
    }

    @Test
    void setName() {
        launch.setName("TestName");
        assertEquals("TestName", launch.getName());
    }

    @Test
    void getWindowStart() {
        Instant instant = Instant.parse("2020-01-01T00:00:00.00Z");
        launch.setWindowStart(instant);
        assertEquals(instant, launch.getWindowStart());
    }

    @Test
    void setWindowStart() {
        Instant instant = Instant.parse("2020-01-01T00:00:00.00Z");
        launch.setWindowStart(instant);
        assertEquals(instant, launch.getWindowStart());
    }

    @Test
    void getWindowEnd() {
        Instant instant = Instant.parse("2020-01-01T00:00:00.00Z");
        launch.setWindowEnd(instant);
        assertEquals(instant, launch.getWindowEnd());
    }

    @Test
    void setWindowEnd() {
        Instant instant = Instant.parse("2020-01-01T00:00:00.00Z");
        launch.setWindowEnd(instant);
        assertEquals(instant, launch.getWindowEnd());
    }

    @Test
    void getRocket() {
        Rocket rocket = new Rocket();
        launch.setRocket(rocket);
        assertEquals(rocket, launch.getRocket());
    }

    @Test
    void setRocket() {
        Rocket rocket = new Rocket();
        launch.setRocket(rocket);
        assertEquals(rocket, launch.getRocket());
    }

    @Test
    void getLaunchPad() {
        LaunchPad launchPad = new LaunchPad();
        launch.setLaunchPad(launchPad);
        assertEquals(launchPad, launch.getLaunchPad());
    }

    @Test
    void setLaunchPad() {
        LaunchPad launchPad = new LaunchPad();
        launch.setLaunchPad(launchPad);
        assertEquals(launchPad, launch.getLaunchPad());
    }

    @Test
    void getLaunchServiceProvider() {
        launch.setLaunchServiceProvider("TestProvider");
        assertEquals("TestProvider", launch.getLaunchServiceProvider());
    }

    @Test
    void setLaunchServiceProvider() {
        launch.setLaunchServiceProvider("TestProvider");
        assertEquals("TestProvider", launch.getLaunchServiceProvider());
    }

    @Test
    void addLink() {
        launch.addLink("https://example.com", "rel");
        assertEquals(1, launch.getLinks().size());
    }

    @Test
    void getLinks() {
        launch.addLink("https://example.com", "rel");
        assertEquals(1, launch.getLinks().size());
    }
}