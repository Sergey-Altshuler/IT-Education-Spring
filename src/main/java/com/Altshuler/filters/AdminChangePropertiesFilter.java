package com.Altshuler.filters;

import com.Altshuler.servlce.AdminService;
import com.Altshuler.servlce.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectPageConstants.PAGE_WRONG_DATA;
import static com.Altshuler.info.ProjectParamConstants.*;

@WebFilter(urlPatterns = "/adminChangeProperties")
public class AdminChangePropertiesFilter implements Filter {
    private final AdminService adminService = new AdminServiceImpl();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        if (((req.getParameter(PARAM_CHANGED)).equals(req.getParameter(PARAM_REPEATED)))&&
                (adminService.validate(req.getParameter(PARAM_LOGIN), req.getParameter(PARAM_PASSWORD))))
            filterChain.doFilter(req, resp);
        else resp.sendRedirect(contextPath + PAGE_WRONG_DATA);
    }
}
