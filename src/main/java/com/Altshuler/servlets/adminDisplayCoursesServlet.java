package com.Altshuler.servlets;

import com.Altshuler.model.Course;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;
import com.Altshuler.util.MarkUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectAttributeConstants.ATTR_COURSES;
import static com.Altshuler.info.ProjectNamedConstants.YES;
import static com.Altshuler.info.ProjectPageConstants.PAGE_ADMIN_DISPLAY_COURSES;
import static com.Altshuler.info.ProjectParamConstants.PARAM_DELETE_ID;
import static com.Altshuler.info.ProjectParamConstants.PARAM_LAUNCH_ID;

@WebServlet("/adminDisplayCourses")
public class adminDisplayCoursesServlet extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();
    private final MarkUtil markUtil = new MarkUtil();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter(PARAM_LAUNCH_ID) != null) {
            Course course = courseService.getById(Integer.parseInt(request.getParameter(PARAM_LAUNCH_ID)));
            course.setIsStarted(YES);
            courseService.add(course);
            markUtil.initializeMarks(course);
        }
        if (request.getParameter(PARAM_DELETE_ID) != null) {
            courseService.deleteById(Integer.parseInt(request.getParameter(PARAM_DELETE_ID)));
        }
        request.setAttribute(ATTR_COURSES, courseService.getAll());
        request.getRequestDispatcher(PAGE_ADMIN_DISPLAY_COURSES).forward(request, response);
    }
}
