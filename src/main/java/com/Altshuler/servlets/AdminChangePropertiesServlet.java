package com.Altshuler.servlets;

import com.Altshuler.servlce.AdminService;
import com.Altshuler.servlce.AdminServiceImpl;
import com.Altshuler.util.ParseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectPageConstants.PAGE_ADMIN_SUCCESS_CHANGE;
import static com.Altshuler.info.ProjectParamConstants.*;

@WebServlet("/adminChangeProperties")
public class AdminChangePropertiesServlet extends HttpServlet {
    private final AdminService adminService = new AdminServiceImpl();
    private final ParseUtil parseUtil = new ParseUtil();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        adminService.changeProperties(request.getParameter(PARAM_LOGIN), request.getParameter(PARAM_CHANGED));
        request.setAttribute(PARAM_PASSWORD, parseUtil.encryptPassword(request.getParameter(PARAM_CHANGED)));
        request.getRequestDispatcher(PAGE_ADMIN_SUCCESS_CHANGE).forward(request, response);
    }
}
