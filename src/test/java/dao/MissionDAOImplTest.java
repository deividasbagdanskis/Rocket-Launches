package dao;

import lt.viko.eif.final_project.dao.*;
import lt.viko.eif.final_project.pojos.Customer;
import lt.viko.eif.final_project.pojos.Launch;
import lt.viko.eif.final_project.pojos.Mission;
import lt.viko.eif.final_project.pojos.Payload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MissionDAOImplTest {

    private CustomerDAO customerDAO;
    private MissionDAO missionDAO;
    private LaunchDAO launchDAO;
    private RocketDAO rocketDAO;
    private LaunchPadDAO launchPadDAO;
    private PayloadDAO payloadDAO;

    Mission testMission = new Mission();
    Launch newLaunch = new Launch();
    Customer testCustomer = new Customer();
    Payload newPayloadDOA = new Payload();
    List<Mission> missions = new ArrayList<>();
    List<Payload> payloads = new ArrayList<>();

    @BeforeEach
    void setUp() {
        missionDAO = new MissionDAOImpl();
        customerDAO = new CustomerDAOImpl();
        launchDAO = new LaunchDAOImpl();
        payloadDAO = new PayloadDAOImpl();
        launchPadDAO = new LaunchPadDAOImpl();
        rocketDAO = new RocketDAOImpl();

        newPayloadDOA.setDescription("Just Testing Description");
        newPayloadDOA.setTotalAmount(1);
        newPayloadDOA.setWeight(1);
        newPayloadDOA.setMissionId(1);
        payloads.add(newPayloadDOA);
        testMission.setName("MissionTest");
        testMission.setDescription("TestDescription");
        testMission.setLaunch(launchDAO.getLaunchById(1));
        testMission.setCustomer(customerDAO.getCustomerById(1));
        testMission.setPayloads(payloads);
        newLaunch.setName("testLaunch");
        newLaunch.setLaunchPad(launchPadDAO.getLaunchPadById(6));
        newLaunch.setWindowStart(Instant.parse("2020-06-12T04:15:13Z"));
        newLaunch.setWindowEnd(Instant.parse("2020-06-12T04:16:13Z"));
        newLaunch.setRocket(rocketDAO.getRocketById(1));
        testMission.setLaunch(newLaunch);
        testCustomer.setName("NASA");
        testMission.setCustomer(testCustomer);
    }

    @AfterEach
    void tearDown() {
        missionDAO = null;
        missions = null;
        testMission = null;
        payloads = null;
    }

    @Test
    void getAllMission(){
        missions = missionDAO.getAllMission();
        int count = missions.size();
        assertEquals(count, missionDAO.getAllMission().size());
    }

    @Test
    void getMissionsByName(){
        missionDAO.addMission(testMission);
        missions = missionDAO.getMissionsByName("MissionTest");
        for(Mission var : missions)
        {
            assertEquals(testMission.getName(), var.getName());
        }
        missionDAO.deleteMission("MissionTest");
    }

    @Test
    void getMissionById(){
        missionDAO.addMission(testMission);
        missions = missionDAO.getMissionsByName("MissionTest");
        for(Mission var : missions)
        {
            assertEquals(testMission.getName(), missionDAO.getMissionById(var.getId()).getName());
        }
        missionDAO.deleteMission("MissionTest");
    }

    @Test
    void addMission(){
        assertEquals(true, missionDAO.addMission(testMission));
        missionDAO.deleteMission("MissionTest");
    }

    @Test
    void updateMission(){
        missionDAO.addMission(testMission);
        missions = missionDAO.getMissionsByName("MissionTest");
        for(Mission var : missions)
        {
            var.setDescription("ChangedDescription");
            var.setPayloads(payloadDAO.getPayloads(var.getId()));
            assertEquals(true, missionDAO.updateMission(var));
            missionDAO.deleteMission("MissionTest");
        }
    }

    @Test
    void deleteMission(){
        missionDAO.addMission(testMission);
        assertEquals(true, missionDAO.deleteMission(testMission.getName()));
    }
}
