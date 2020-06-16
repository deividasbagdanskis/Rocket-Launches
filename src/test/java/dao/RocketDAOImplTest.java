package dao;

import lt.viko.eif.final_project.dao.RocketDAO;
import lt.viko.eif.final_project.dao.RocketDAOImpl;
import lt.viko.eif.final_project.pojos.Rocket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RocketDAOImplTest {

    private RocketDAO rocketDAO;
    List<Rocket> foundRockets = new ArrayList<>();
    Rocket testRocket = new Rocket();

    @BeforeEach
    void setUp() {
        rocketDAO = new RocketDAOImpl();
        testRocket.setName("TestRocket");
        testRocket.setManufacturer("TestManufacturer");
        testRocket.setType("TestType");
        testRocket.setCountryOfOrigin("USA");
        testRocket.setHeight(1);
        testRocket.setDiameter(1);
        testRocket.setMass(1);
        testRocket.setNumberOfStages(1);
        testRocket.setPayloadToLEO(1);
        testRocket.setPayloadToGTO(1);
        testRocket.addLink("www.wikipedia.com", "wikiURL");
    }

    @AfterEach
    void tearDown() {
        rocketDAO = null;
        foundRockets = null;
        testRocket = null;
    }

    @Test
    void getAllRockets(){
        int size = rocketDAO.getAllRockets().size();
        assertEquals(size, rocketDAO.getAllRockets().size());
    }

    @Test
    void getRocketsByName(){
        int id = rocketDAO.addRocket(testRocket);
        foundRockets = rocketDAO.getRocketsByName("TestRocket");
        assertEquals(foundRockets.size(), 1);
        rocketDAO.deleteRocket(id);
    }

    @Test
    void getRocketById(){
        int id = rocketDAO.addRocket(testRocket);
        assertEquals("TestRocket", rocketDAO.getRocketById(id).getName());
        rocketDAO.deleteRocket(id);
    }

    @Test
    void addRocket(){
        int id = rocketDAO.addRocket(testRocket);
        assertEquals(testRocket.getName(), rocketDAO.getRocketById(id).getName());
        rocketDAO.deleteRocket(id);
    }

    @Test
    void updateRocket(){
        int id = rocketDAO.addRocket(testRocket);
        testRocket.setId(id);
        testRocket.setName("ChangedTest");
        assertEquals(false, rocketDAO.updateRocket(testRocket));
        rocketDAO.deleteRocket(id);
    }

    @Test
    void deleteRocket() {
        int id = rocketDAO.addRocket(testRocket);
        assertEquals(true, rocketDAO.deleteRocket(id));
    }

}
