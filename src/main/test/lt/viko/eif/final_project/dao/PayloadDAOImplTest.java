package lt.viko.eif.final_project.dao;

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

class PayloadDAOImplTest {

    private CustomerDAO customerDAO;
    private MissionDAO missionDAO;
    private LaunchDAO launchDAO;
    private PayloadDAO payloadDAO;
    private RocketDAO rocketDAO;
    private LaunchPadDAO launchPadDAO;
    Mission testMission = new Mission();
    Payload newPayload = new Payload();

    List<Payload> foundPayloadDOA = new ArrayList<>();
//    List<Mission> missions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        missionDAO = new MissionDAOImpl();
        customerDAO = new CustomerDAOImpl();
        launchDAO = new LaunchDAOImpl();
        payloadDAO = new PayloadDAOImpl();
        rocketDAO = new RocketDAOImpl();
        launchPadDAO = new LaunchPadDAOImpl();

        newPayload.setDescription("Just Testing Description");
        newPayload.setTotalAmount(1);
        newPayload.setWeight(1);
        newPayload.setMissionId(12);
    }

    @AfterEach
    void tearDown() {
        payloadDAO = null;
    }

    @Test
    void getPayloads(){
        foundPayloadDOA = payloadDAO.getPayloads(12);
        assertEquals(1, foundPayloadDOA.size());
    }

    @Test
    void addPayload(){
        assertEquals(true, payloadDAO.addPayload(newPayload));
        payloadDAO.deletePayloadByDescription("Just Testing Description");
    }

    @Test
    void updatePayload(){
        payloadDAO.addPayload(newPayload);
        int newWeight = 15;
        newPayload.setWeight(newWeight);
        int id = payloadDAO.getPayloads(12).get(1).getId();
        newPayload.setId(id);
        payloadDAO.updatePayload(newPayload);
        assertEquals(newWeight, payloadDAO.getPayloads(12).get(1).getWeight());
        payloadDAO.deletePayloadByDescription(newPayload.getDescription());
    }

    @Test
    void deletePayloadsByMission(){
        Launch newLaunch = new Launch();
        newLaunch.setRocket(rocketDAO.getRocketById(1));
        newLaunch.setLaunchPad(launchPadDAO.getLaunchPadById(6));
        newLaunch.setWindowStart(Instant.parse("2021-10-08T05:21:00Z"));
        newLaunch.setWindowEnd(Instant.parse("2021-10-08T06:21:00Z"));
        testMission.setLaunch(newLaunch);
        testMission.setName("MissionFromPayload");
        testMission.setCustomer(customerDAO.getCustomerById(17));
        testMission.addPayload(newPayload);
        missionDAO.addMission(testMission);
        Mission addedMission = missionDAO.getMissionsByName("MissionFromPayload").get(0);

        assertEquals(true, payloadDAO.deletePayloadsByMission(addedMission.getId()));
        missionDAO.deleteMission("MissionFromPayload");
    }

    @Test
    void deletePayloadByDescription(){
        payloadDAO.addPayload(newPayload);
        assertEquals(true, payloadDAO.deletePayloadByDescription("Just Testing Description"));
    }
}
