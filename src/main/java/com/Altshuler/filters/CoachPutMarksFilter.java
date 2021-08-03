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

import static com.Altshuler.info.ProjectNamedConstants.YES;
import static com.Altshuler.info.ProjectPageConstants.PAGE_WRONG_OPERATION;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebFilter(urlPatterns = "/coachPutMarks")
public class CoachPutMarksFilter implements Filter {
   private final CourseService courseService = new CourseServiceImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        int courseNum;
        if ((req.getParameter(PARAM_NUMBER)) == null) courseNum = ProjectInfo.getNumOfCurrentCourse();
        else courseNum = Integer.parseInt(req.getParameter(PARAM_NUMBER));
        Course course = courseService.getById(courseNum);
        if ((YES).equals(course.getIsStarted()) && ((ProjectInfo.getCoach()).equals(course.getCoach()))) {
            filterChain.doFilter(req, resp);
        } else resp.sendRedirect(contextPath + PAGE_WRONG_OPERATION);
    }

}

