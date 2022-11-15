package com.Altshuler.serviceTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.info.ProjectInfo;
import com.Altshuler.servlce.CoachService;
import com.Altshuler.servlce.CoachServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.Altshuler.TestInfo.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoachServiceTest {
    CoachService coachService = new CoachServiceImpl();

    @BeforeClass
    void generateForTesting() {
        coachService.add(TestDataCreator.createValidateTestCoach());
        coachService.add(TestDataCreator.createLoginTestCoach());
    }

    @Test
    void add() {
        coachService.add(TestDataCreator.createNewTestCoach());
        assertNotNull(coachService.getById(3));
    }

    @Test
    void validate() {
        coachService.add(TestDataCreator.createValidateTestCoach());
        assertTrue(coachService.validate(VALIDATE_LOGIN, VALIDATE_PASSWORD));
    }

    @Test
    void logIn() {
        coachService.add(TestDataCreator.createLoginTestCoach());
        coachService.logIn(ENTER_LOGIN, ENTER_PASSWORD);
        assertEquals(ProjectInfo.getCoach().getPassword(), ENTER_PASSWORD);
    }


    @AfterClass
    void cleanDatabase() {
        coachService.deleteAll();
    }
}
