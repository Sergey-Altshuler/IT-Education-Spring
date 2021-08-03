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

import static com.Altshuler.info.ProjectAttributeConstants.ATTR_CURRENT_COURSE;
import static com.Altshuler.info.ProjectPageConstants.PAGE_COACH_SUCCESS_ENROLL;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebServlet("/coachChooseCourse")
public class CoachChooseCourseServlet extends HttpServlet {
   private final CoachService coachService = new CoachServiceImpl();
   private final CourseService courseService = new CourseServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Course course = courseService.getById(Integer.parseInt(request.getParameter(PARAM_NUMBER)));
        Coach coach = ProjectInfo.getCoach();
        coach.addCourse(course);
        course.setCoach(coach);
        coachService.add(coach);
        courseService.add(course);
        ProjectInfo.setCoach(coach);
        request.setAttribute(ATTR_CURRENT_COURSE, course);
        request.getRequestDispatcher(PAGE_COACH_SUCCESS_ENROLL).forward(request, response);

    }
}
