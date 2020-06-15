package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.LaunchPad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LaunchPadDAOImplTest {

    private LaunchPadDAO launchPadDAO;
    LaunchPad testLaunchPad = new LaunchPad();

    @BeforeEach
    void setUp() {
        launchPadDAO = new LaunchPadDAOImpl();
        testLaunchPad.setName("TestName");
        testLaunchPad.setLocationName("TestLocation");
        testLaunchPad.setLatitude(58.73430000);
        testLaunchPad.setLongitude(93.82030000);
        testLaunchPad.setMapsURL("testURL");
        testLaunchPad.setWikiURL("testURL");
    }

    @AfterEach
    void tearDown() {
        launchPadDAO = null;
    }

    @Test
    void getAllLaunches(){
        assertEquals(1, launchPadDAO.getAllLaunchPads().size());
    }

    @Test
    void getLaunchPadById(){
        LaunchPad addedLaunchPad = new LaunchPad();

        launchPadDAO.addLaunchPad(testLaunchPad);
        addedLaunchPad = launchPadDAO.getLaunchPadByName("TestName");
        int id = addedLaunchPad.getId();
        assertEquals(addedLaunchPad.getName(), launchPadDAO.getLaunchPadById(id).getName());
        launchPadDAO.deleteLaunchPad(addedLaunchPad.getId());
    }

    @Test
    void getLaunchPadByName(){
        LaunchPad addedLaunchPad = new LaunchPad();

        launchPadDAO.addLaunchPad(testLaunchPad);
        addedLaunchPad = launchPadDAO.getLaunchPadByName("TestName");
        int id = addedLaunchPad.getId();
        assertEquals(addedLaunchPad.getName(), launchPadDAO.getLaunchPadByName("TestName").getName());
        launchPadDAO.deleteLaunchPad(addedLaunchPad.getId());
    }

    @Test
    void addLaunchPad(){
        LaunchPad addedLaunchPad = new LaunchPad();

        launchPadDAO.addLaunchPad(testLaunchPad);
        addedLaunchPad = launchPadDAO.getLaunchPadByName("TestName");
        assertEquals(testLaunchPad.getName(), addedLaunchPad.getName());
        launchPadDAO.deleteLaunchPad(addedLaunchPad.getId());
    }

    @Test
    void deleteLaunchPad() {
        LaunchPad addedLaunchPad = new LaunchPad();

        launchPadDAO.addLaunchPad(testLaunchPad);
        addedLaunchPad = launchPadDAO.getLaunchPadByName("TestName");
        int id = addedLaunchPad.getId();
        assertEquals(true, launchPadDAO.deleteLaunchPad(id));
    }
}
