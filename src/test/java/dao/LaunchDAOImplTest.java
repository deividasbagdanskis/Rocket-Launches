package dao;

import lt.viko.eif.final_project.dao.*;
import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.LaunchPad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LaunchDAOImplTest {

    private RocketDAO rocketDAO;
    private LaunchDAO launchDAO;
    private LaunchPadDAO launchPadDAO;
    Launch newLaunch = new Launch();
    List<Launch> launches = new ArrayList<>();

    @BeforeEach
    void setUp() {
        launchDAO = new LaunchDAOImpl();
        rocketDAO = new RocketDAOImpl();
        launchPadDAO = new LaunchPadDAOImpl();

        newLaunch.setName("TestName");
        newLaunch.setLaunchServiceProvider("TestProvider");
        newLaunch.setWindowEnd(Instant.parse("2021-10-08T05:21:00Z"));
        newLaunch.setWindowStart(Instant.parse("2021-10-08T06:21:00Z"));
        newLaunch.setRocket(rocketDAO.getRocketById(1));
        newLaunch.setLaunchPad(launchPadDAO.getLaunchPadById(6));
    }

    @AfterEach
    void tearDown() {
        launchDAO = null;
        launches = null;
    }

    @Test
    void getAllLaunches() {
        int size = launchDAO.getAllLaunches().size();
        assertEquals(size, launchDAO.getAllLaunches().size());
    }

    @Test
    void getLaunchesByName(){
        int id = launchDAO.addLaunch(newLaunch);
        assertEquals(1, launchDAO.getLaunchesByName("TestName").size());
        launchDAO.deleteLaunch(id);
    }

    @Test
    void getLaunchById(){
        int id = launchDAO.addLaunch(newLaunch);
        assertEquals("TestName", launchDAO.getLaunchById(id).getName());
        launchDAO.deleteLaunch(id);
    }

    @Test
    void getUpcomingLaunches(){
        int id = launchDAO.addLaunch(newLaunch);
        launches = launchDAO.getUpcomingLaunches(1);
        assertEquals(1, launches.size());
        launchDAO.deleteLaunch(id);
    }

    @Test
    void getLaunchesByDates(){
        int id = launchDAO.addLaunch(newLaunch);
        launches = launchDAO.getLaunchesByDates("2020-10-08", "2022-10-08");
        assertEquals(1, launches.size());
        launchDAO.deleteLaunch(id);
    }

    @Test
    void addLaunch(){
        int id = launchDAO.addLaunch(newLaunch);
        assertEquals(newLaunch.getName(), launchDAO.getLaunchById(id).getName());
        launchDAO.deleteLaunch(id);
    }

    @Test
    void updateLaunch(){
        int id = launchDAO.addLaunch(newLaunch);
        newLaunch.setId(id);
        newLaunch.setLaunchServiceProvider("ChangedTestProvider");
        assertEquals(true, launchDAO.updateLaunch(newLaunch));
        launchDAO.deleteLaunch(id);
    }

    @Test
    void deleteLaunch(){
        int id = launchDAO.addLaunch(newLaunch);
        assertEquals(true, launchDAO.deleteLaunch(id));
    }
}
