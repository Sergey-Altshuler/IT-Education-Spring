package com.Altshuler.servlets;

import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Course;
import com.Altshuler.model.Student;
import com.Altshuler.servlce.CourseService;
import com.Altshuler.servlce.CourseServiceImpl;
import com.Altshuler.util.ParseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.Altshuler.info.ProjectAttributeConstants.*;
import static com.Altshuler.info.ProjectPageConstants.PAGE_COACH_PUT_MARKS;
import static com.Altshuler.info.ProjectParamConstants.PARAM_NUMBER;

@WebServlet("/coachPutMarks")
public class CoachPutMarksServlet extends HttpServlet {
    private final CourseService courseService = new CourseServiceImpl();
    private final ParseUtil parseUtil = new ParseUtil();
    private final String regex = "[0-9]+";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int courseNum;
        if ((request.getParameter(PARAM_NUMBER)) == null) courseNum = ProjectInfo.getNumOfCurrentCourse();
        else {
            courseNum = Integer.parseInt(request.getParameter(PARAM_NUMBER));
            ProjectInfo.setNumOfCurrentCourse(courseNum);
        }
        Course course = courseService.getById(courseNum);
        ProjectInfo.setCourse(course);
        Student anyStudent = course.getStudents().stream().findAny().get();
        Map<Student, Map<String, String>> courseMap = ProjectInfo.getMarks().get(course);
        request.setAttribute(ATTR_COURSE_MAP, courseMap);
        Map<String, String> studentMap = new LinkedHashMap<>(courseMap.get(anyStudent));
        List<String> elements = new ArrayList<>(studentMap.keySet());
        for (String element : elements) {
            if (!element.matches(regex)) studentMap.remove(element);
        }
        request.setAttribute(ATTR_TITLES, studentMap);
        Map<Integer, String> studentAdditionalMap = new LinkedHashMap<>();
        for (Student student : course.getStudents()) {
            studentAdditionalMap.put(student.getId(), parseUtil.parseStudent(student));
        }
        request.setAttribute(ATTR_STUDENT_MAP, studentAdditionalMap);
        request.setAttribute(ATTR_NUMBER, courseNum);

        request.getRequestDispatcher(PAGE_COACH_PUT_MARKS).forward(request, response);

    }
}
