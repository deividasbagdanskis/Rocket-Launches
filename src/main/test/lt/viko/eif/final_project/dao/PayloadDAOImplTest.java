package lt.viko.eif.final_project.dao;

import lt.viko.eif.final_project.pojos.Mission;
import lt.viko.eif.final_project.pojos.Payload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayloadDAOImplTest {

    private CustomerDAO customerDAO;
    private MissionDAO missionDAO;
    private LaunchDAO launchDAO;
    private PayloadDAO payloadDAO;
    Mission testMission = new Mission();
    Payload newPayloadDOA = new Payload();

    List<Payload> foundPayloadDOA = new ArrayList<>();
    List<Mission> missions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        missionDAO = new MissionDAOImpl();
        customerDAO = new CustomerDAOImpl();
        launchDAO = new LaunchDAOImpl();
        payloadDAO = new PayloadDAOImpl();

        newPayloadDOA.setDescription("Just Testing Description");
        newPayloadDOA.setTotalAmount(1);
        newPayloadDOA.setWeight(1);
        newPayloadDOA.setMissionId(1);
        newPayloadDOA.setId(2);

        testMission.setName("MissionTest");
        testMission.setDescription("TestDescription");
        testMission.setLaunch(launchDAO.getLaunchById(1));
        testMission.setCustomer(customerDAO.getCustomerById(1));
    }

    @AfterEach
    void tearDown() {
        payloadDAO = null;
    }

    @Test
    void getPayloads(){
        foundPayloadDOA = payloadDAO.getPayloads(1);
        assertEquals(1, foundPayloadDOA.size());
    }

    @Test
    void addPayload(){
        assertEquals(true, payloadDAO.addPayload(newPayloadDOA));
        payloadDAO.deletePayloadByDescription("Just Testing Description");
    }

    @Test
    void updatePayload(){
        missionDAO.addMission(testMission);
        missions = missionDAO.getMissionsByName("MissionFromPayload");
        for( Mission var : missions)
        {
            payloadDAO.addPayload(newPayloadDOA);
            newPayloadDOA.setWeight(50);
            foundPayloadDOA = payloadDAO.getPayloads(var.getId());
            for( Payload var2 : foundPayloadDOA)
            {
                assertEquals("50", var2.getWeight());
            }
            payloadDAO.deletePayloadByDescription("Just Testing Description");
        }
    }

    @Test
    void deletePayloadsByMission(){
        missionDAO.addMission(testMission);
        missions = missionDAO.getMissionsByName("MissionFromPayload");
        for( Mission var : missions)
        {
            payloadDAO.addPayload(newPayloadDOA);
            assertEquals(true, payloadDAO.deletePayloadsByMission(payloadDAO.deletePayloadsByMission(var.getId())));
            missionDAO.deleteMission("MissionFromPayload");
        }
    }

    @Test
    void deletePayloadByDescription(){
        payloadDAO.addPayload(newPayloadDOA);
        assertEquals(true, payloadDAO.deletePayloadByDescription("Just Testing Description"));
    }
}
