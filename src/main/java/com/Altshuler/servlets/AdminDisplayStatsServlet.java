package com.Altshuler.servlets;

import com.Altshuler.servlce.StatsService;
import com.Altshuler.servlce.StatsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectAttributeConstants.ATTR_STATS;
import static com.Altshuler.info.ProjectPageConstants.PAGE_ADMIN_DISPLAY_STATS;

@WebServlet("/adminDisplayStats")
public class AdminDisplayStatsServlet extends HttpServlet {
    private final StatsService statsService = new StatsServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(ATTR_STATS, statsService.getAll());
        request.getRequestDispatcher(PAGE_ADMIN_DISPLAY_STATS).forward(request, response);
    }
}
