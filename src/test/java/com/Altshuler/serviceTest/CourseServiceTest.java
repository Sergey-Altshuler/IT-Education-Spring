package com.Altshuler.serviceTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.model.Course;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.Altshuler.TestInfo.TestConstants.COURSE_TITLE_TEST2;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseServiceTest {
    CourseService courseService = new CourseServiceImpl();

    @BeforeClass
    void generateForTesting() {
        courseService.add(TestDataCreator.createTestGetByIdCourse());
        courseService.add(TestDataCreator.createTestGetAllCourse());
    }

    @Test
    void change() {
        courseService.add(TestDataCreator.createTestChangeCourse());
        assertEquals(3, courseService.getAll().size());
        courseService.deleteById(3);
        assertEquals(2, courseService.getAll().size());

    }

    @Test
    void getById() {
        Course course = courseService.getById(1);
        assertEquals(course.getTitle(), COURSE_TITLE_TEST2);
    }

    @Test
    void getAll() {
        assertEquals(2, courseService.getAll().size());
    }

    @AfterClass
    void clearDatabase() {
        courseService.deleteAll();
    }
}
