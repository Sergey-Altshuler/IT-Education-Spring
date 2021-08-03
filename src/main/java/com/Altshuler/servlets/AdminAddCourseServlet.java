package com.Altshuler.servlets;

import com.Altshuler.converters.ConverterProvider;
import com.Altshuler.model.Course;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Altshuler.info.ProjectAttributeConstants.ATTR_COURSES;
import static com.Altshuler.info.ProjectPageConstants.PAGE_ADMIN_SUCCESS_ADD;

@WebServlet("/adminAddCourse")
public class AdminAddCourseServlet extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();

    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        courseService.add(ConverterProvider.convert(Course.class, request));
        request.setAttribute(ATTR_COURSES, courseService.getAll());
        request.getRequestDispatcher(PAGE_ADMIN_SUCCESS_ADD).forward(request, response);

    }
}
