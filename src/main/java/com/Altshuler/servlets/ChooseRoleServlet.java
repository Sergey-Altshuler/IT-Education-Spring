package com.Altshuler.servlets;

import com.Altshuler.info.ProjectInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectParamConstants.PARAM_ROLE;

@WebServlet("/chooseRole")
public class ChooseRoleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = ProjectInfo.getRoles().get(request.getParameter(PARAM_ROLE));
        request.getRequestDispatcher(path).forward(request, response);
    }

}
