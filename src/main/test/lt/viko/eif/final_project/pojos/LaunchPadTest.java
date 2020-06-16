package lt.viko.eif.final_project.pojos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tomas Jokubauskas
 */
class LaunchPadTest {
    private LaunchPad launchPad;

    @BeforeEach
    void setUp() {
        launchPad = new LaunchPad();
    }

    @AfterEach
    void tearDown() {
        launchPad = null;
    }

    @Test
    void getId() {
        launchPad.setId(7);
        assertEquals(7, launchPad.getId());
    }

    @Test
    void setId() {
        launchPad.setId(7);
        assertEquals(7, launchPad.getId());
    }

    @Test
    void getName() {
        launchPad.setName("TestName");
        assertEquals("TestName", launchPad.getName());
    }

    @Test
    void setName() {
        launchPad.setName("TestName");
        assertEquals("TestName", launchPad.getName());
    }

    @Test
    void getLocationName() {
        launchPad.setLocationName("TestLocation");
        assertEquals("TestLocation", launchPad.getLocationName());
    }

    @Test
    void setLocationName() {
        launchPad.setLocationName("TestLocation");
        assertEquals("TestLocation", launchPad.getLocationName());
    }

    @Test
    void getLatitude() {
        launchPad.setLatitude(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, launchPad.getLatitude());
    }

    @Test
    void setLatitude() {
        launchPad.setLatitude(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, launchPad.getLatitude());
    }

    @Test
    void getLongitude() {
        launchPad.setLongitude(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, launchPad.getLongitude());
    }

    @Test
    void setLongitude() {
        launchPad.setLongitude(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, launchPad.getLongitude());
    }

    @Test
    void getWikiURL() {
        launchPad.setWikiURL("https://example.com");
        assertEquals("https://example.com", launchPad.getWikiURL());
    }

    @Test
    void setWikiURL() {
        launchPad.setWikiURL("https://example.com");
        assertEquals("https://example.com", launchPad.getWikiURL());
    }

    @Test
    void getMapsURL() {
        launchPad.setMapsURL("https://example.com");
        assertEquals("https://example.com", launchPad.getMapsURL());
    }

    @Test
    void setMapsURL() {
        launchPad.setMapsURL("https://example.com");
        assertEquals("https://example.com", launchPad.getMapsURL());
    }

    @Test
    void addLink() {
        launchPad.addLink("https://example.com", "rel");
        assertEquals(1, launchPad.getLinks().size());
    }

    @Test
    void getLinks() {
        launchPad.addLink("https://example.com", "rel");
        assertEquals(1, launchPad.getLinks().size());
    }
}