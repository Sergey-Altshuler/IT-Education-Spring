package com.Altshuler.servlets;

import com.Altshuler.converters.ConverterProvider;
import com.Altshuler.model.Student;
import com.Altshuler.servlce.StudentService;
import com.Altshuler.servlce.StudentServiceImpl;
import com.Altshuler.util.ParseUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectPageConstants.PAGE_STUDENT_SUCCESS_REGISTER;
import static com.Altshuler.info.ProjectParamConstants.PARAM_PASSWORD;

@WebServlet("/studentRegister")
public class StudentRegisterServlet extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();
    private final ParseUtil parseUtil = new ParseUtil();
    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        studentService.add(ConverterProvider.convert(Student.class, request));
        request.setAttribute(PARAM_PASSWORD, parseUtil.encryptPassword(request.getParameter(PARAM_PASSWORD)));
        request.getRequestDispatcher(PAGE_STUDENT_SUCCESS_REGISTER).forward(request, response);
    }
}
