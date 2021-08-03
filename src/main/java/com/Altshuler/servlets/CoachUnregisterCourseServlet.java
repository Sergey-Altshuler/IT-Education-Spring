package com.Altshuler.servlets;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Coach;
import com.Altshuler.model.Course;
import com.Altshuler.servlce.CoachService;
import com.Altshuler.servlce.CoachServiceImpl;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebServlet("/coachUnregisterCourse")
public class CoachUnregisterCourseServlet extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();
    private final CoachService coachService = new CoachServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Course course = courseService.getById(Integer.parseInt(request.getParameter(PARAM_NUMBER)));
        Coach coach = ProjectInfo.getCoach();
        course.setCoach(null);
        coach.getCourseSet().remove(course);
        coachService.add(coach);
        courseService.add(course);
        ProjectInfo.setCoach(coach);
        response.sendRedirect(request.getContextPath() + "/coach");

    }
}
