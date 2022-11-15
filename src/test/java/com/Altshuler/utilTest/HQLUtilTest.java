package com.Altshuler.utilTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.model.Coach;
import com.Altshuler.model.Student;
import com.Altshuler.servlce.CoachService;
import com.Altshuler.servlce.CoachServiceImpl;
import com.Altshuler.servlce.StudentService;
import com.Altshuler.servlce.StudentServiceImpl;
import com.Altshuler.util.HQLUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.Altshuler.TestInfo.TestConstants.LOGIN;
import static com.Altshuler.TestInfo.TestConstants.PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HQLUtilTest {
    HQLUtil hqlUtil = new HQLUtil();
    CoachService coachService = new CoachServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    Student student = TestDataCreator.createTestStudent();
    Coach coach = TestDataCreator.createTestCoach();

    @BeforeClass
    void generateForChecking() {
        coachService.add(coach);
        studentService.add(student);
    }

    @Test
    void checkCoach() {
        assertEquals(coach.getPassword(), hqlUtil.checkCoach(LOGIN, PASSWORD).getPassword());
    }

    @Test
    void checkStudent() {
        assertEquals(student.getPassword(), hqlUtil.checkStudent(LOGIN, PASSWORD).getPassword());
    }

    @AfterClass
    void clearDatabase() {
        coachService.deleteAll();
        studentService.deleteAll();
    }
}
