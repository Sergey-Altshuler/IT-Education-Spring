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

import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebServlet("/studentUnregisterCourse")
public class StudentUnregisterCourseServlet extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Course course = courseService.getById(Integer.parseInt(request.getParameter(PARAM_NUMBER)));
        Student student = ProjectInfo.getStudent();
        course.getStudents().remove(student);
        student.setCourse(null);
        courseService.add(course);
        studentService.add(student);
        ProjectInfo.setStudent(student);
        response.sendRedirect(request.getContextPath() + "/student");

    }
}
