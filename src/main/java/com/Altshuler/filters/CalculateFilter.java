package com.Altshuler.filters;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Course;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;
import com.Altshuler.servlce.StudentService;
import com.Altshuler.servlce.StudentServiceImpl;
import com.Altshuler.util.MarkUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectNamedConstants.NO;
import static com.Altshuler.info.ProjectNamedConstants.YES;
import static com.Altshuler.info.ProjectPageConstants.PAGE_WRONG_OPERATION;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebFilter(urlPatterns = "/calculate")
public class CalculateFilter implements Filter {
    private final MarkUtil markUtil = new MarkUtil();
    private final CourseService courseService = new CourseServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        Course course = courseService.getById(Integer.parseInt(req.getParameter(PARAM_NUMBER)));
        if ((course != null) && (course.getIsFinished().equals(NO)) && (ProjectInfo.getCourse() != null) && (markUtil.getIsFinished(ProjectInfo.getCourse()) == 0 && (ProjectInfo.getCourse().getStats() == null))) {
            course.setIsFinished(YES);
            courseService.add(course);
            ProjectInfo.setCourse(course);
            filterChain.doFilter(req, resp);
        } else resp.sendRedirect(contextPath + PAGE_WRONG_OPERATION);

    }
}
