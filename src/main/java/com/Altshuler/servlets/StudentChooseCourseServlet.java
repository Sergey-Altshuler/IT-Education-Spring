package com.Altshuler.servlets;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Course;
import com.Altshuler.model.Student;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;
import com.Altshuler.servlce.StudentService;
import com.Altshuler.servlce.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectAttributeConstants.ATTR_CURRENT_COURSE;
import static com.Altshuler.info.ProjectPageConstants.PAGE_STUDENT_SUCCESS_ENROLL;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebServlet("/studentChooseCourse")
public class StudentChooseCourseServlet extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Course course = courseService.getById(Integer.parseInt(request.getParameter(PARAM_NUMBER)));
        Student student = ProjectInfo.getStudent();
        course.addStudent(student);
        student.setCourse(course);
        courseService.add(course);
        studentService.add(student);
        ProjectInfo.setStudent(student);
        request.setAttribute(ATTR_CURRENT_COURSE, course);
        request.getRequestDispatcher(PAGE_STUDENT_SUCCESS_ENROLL).forward(request, response);

    }
}
