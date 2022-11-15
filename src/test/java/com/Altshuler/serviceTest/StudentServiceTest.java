package com.Altshuler.serviceTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Student;
import com.Altshuler.servlce.StudentService;
import com.Altshuler.servlce.StudentServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.Altshuler.TestInfo.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    StudentService studentService = new StudentServiceImpl();

    @BeforeClass
    void generateForTesting() {
        studentService.add(TestDataCreator.createValidateTestStudent());
        studentService.add(TestDataCreator.createLoginTestStudent());
    }

    @Test
    void add() {
        studentService.add(TestDataCreator.createNewTestStudent());
        assertNotNull(studentService.getById(3));
    }

    @Test
    void validate() {
        assertTrue(studentService.validate(VALIDATE_LOGIN, VALIDATE_PASSWORD));
    }

    @Test
    void logIn() {
        studentService.logIn(ENTER_LOGIN, ENTER_PASSWORD);
        assertEquals(ProjectInfo.getStudent().getPassword(), ENTER_PASSWORD);
    }

    @Test
    void getById() {
        Student student1 = studentService.getById(1);
        assertEquals(VALIDATE_LOGIN, student1.getLogin());
    }

    @AfterClass
    void cleanDatabase() {
        studentService.deleteAll();
    }
}
