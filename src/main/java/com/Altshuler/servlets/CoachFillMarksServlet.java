package com.Altshuler.servlets;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.util.MarkUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.Altshuler.info.ProjectPageConstants.PAGE_COACH_MARKS_REDIRECT;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUM_OF_CURRENT_LESSON;

@WebServlet("/coachFillMarks")
public class CoachFillMarksServlet extends HttpServlet {
    private final MarkUtil markUtil = new MarkUtil();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int numLesson = Integer.parseInt(request.getParameter(PARAM_NUM_OF_CURRENT_LESSON));
        Enumeration<String> params = request.getParameterNames();
        Map<String, String> studentsAndMarks = new HashMap<>();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            if (!param.equals(PARAM_NUM_OF_CURRENT_LESSON))
                studentsAndMarks.put(param, request.getParameter(param));
        }
        markUtil.setLessonMarks(ProjectInfo.getCourse(), studentsAndMarks, numLesson);
        request.getRequestDispatcher(PAGE_COACH_MARKS_REDIRECT).forward(request, response);
    }
}
