package com.Altshuler.filters;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static com.Altshuler.info.ProjectNamedConstants.NO;
import static com.Altshuler.info.ProjectPageConstants.PAGE_WRONG_OPERATION;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUM_OF_CURRENT_LESSON;

@WebFilter(urlPatterns = "/coachFillMarks")
public class CoachFillMarksFilter implements Filter {
    private final String regex = "[0-9]|10|N";
    private final CourseService courseService = new CourseServiceImpl();
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        Enumeration<String> params = req.getParameterNames();
        boolean areRightParams = true;
        if (ProjectInfo.getCourse()==null)
            resp.sendRedirect(contextPath + PAGE_WRONG_OPERATION);
        else if (courseService.getById(ProjectInfo.getCourse().getId()).getIsFinished().equals(NO)) {
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                if (!param.equals(PARAM_NUM_OF_CURRENT_LESSON)) {
                    if ((req.getParameter(param) != null) && (!req.getParameter(param).matches(regex))) {
                        areRightParams = false;
                    }
                } else {
                    int numLesson = Integer.parseInt(req.getParameter(PARAM_NUM_OF_CURRENT_LESSON));
                    if ((ProjectInfo.getCourse() != null) && ((numLesson < 0) || (numLesson > ProjectInfo.getCourse().getNumOfLessons()))) {
                        areRightParams = false;
                    }
                }
            }
            if (areRightParams)
                filterChain.doFilter(req, resp);
            else resp.sendRedirect(contextPath + PAGE_WRONG_OPERATION);
        }
        else resp.sendRedirect(contextPath + PAGE_WRONG_OPERATION);
    }
}
