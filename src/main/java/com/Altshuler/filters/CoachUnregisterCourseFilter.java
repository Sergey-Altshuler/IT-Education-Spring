package com.Altshuler.filters;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Course;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectNamedConstants.NO;
import static com.Altshuler.info.ProjectNamedConstants.YES;
import static com.Altshuler.info.ProjectPageConstants.PAGE_WRONG_OPERATION;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebFilter(urlPatterns = "/coachUnregisterCourse")
public class CoachUnregisterCourseFilter implements Filter {
    private final CourseService courseService = new CourseServiceImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        Course course = courseService.getById(Integer.parseInt(req.getParameter(PARAM_NUMBER)));
        if (((course.getIsStarted().equals(NO)))&&(course.getCoach()!=null)&&(ProjectInfo.getCoach()!=null)&&(course.getCoach().equals(ProjectInfo.getCoach()))) {
            course.setCoachRequired(YES);
            courseService.add(course);
            filterChain.doFilter(req, resp);
        } else resp.sendRedirect(contextPath + PAGE_WRONG_OPERATION);
    }
}
