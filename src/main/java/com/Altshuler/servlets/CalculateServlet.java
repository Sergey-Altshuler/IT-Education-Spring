package com.Altshuler.servlets;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.util.MarkUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectPageConstants.PAGE_COACH_MARKS_REDIRECT;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
    private final MarkUtil markUtil = new MarkUtil();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        markUtil.calculateIndividualStatistics(ProjectInfo.getCourse());
        markUtil.calculateTotalStatistics(ProjectInfo.getCourse());
        request.getRequestDispatcher(PAGE_COACH_MARKS_REDIRECT).forward(request, response);
    }
}
