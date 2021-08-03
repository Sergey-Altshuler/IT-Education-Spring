package com.Altshuler.servlets;

import com.Altshuler.servlce.AdminService;
import com.Altshuler.servlce.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectPageConstants.PAGE_CHOOSE_LANGUAGE;

@WebServlet("/start")
public class StartServlet extends HttpServlet {
    private final AdminService adminService = new AdminServiceImpl();

    public void init() {
        adminService.initialize();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(PAGE_CHOOSE_LANGUAGE).forward(request, response);
    }
}