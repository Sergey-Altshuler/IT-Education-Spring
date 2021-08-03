package com.Altshuler.converters;

import com.Altshuler.model.Course;
import com.Altshuler.util.CourseGroupUtil;
import com.Altshuler.util.ParseUtil;

import javax.servlet.http.HttpServletRequest;

import static com.Altshuler.info.ProjectNamedConstants.NO;
import static com.Altshuler.info.ProjectNamedConstants.YES;
import static com.Altshuler.info.ProjectParamConstants.*;

public class CourseConverter implements Converter<Course> {
    private final CourseGroupUtil courseGroupUtil = new CourseGroupUtil();
    private final ParseUtil parseUtil = new ParseUtil();

    public Course convert(HttpServletRequest request) {
        return Course.builder()
                .title(request.getParameter(PARAM_TITLE))
                .educationType(request.getParameter(PARAM_EDUCATION_TYPE))
                .price(Integer.parseInt(request.getParameter(PARAM_PRICE)))
                .numOfLessons(Integer.parseInt(request.getParameter(PARAM_NUM_OF_LESSONS)))
                .address(request.getParameter(PARAM_ADDRESS))
                .numOfStudents(Integer.parseInt(request.getParameter(PARAM_NUM_OF_STUDENTS)))
                .subgroupNum(courseGroupUtil.getCourseGroup(request.getParameter(PARAM_TITLE)))
                .startDate(parseUtil.parseDate(request.getParameter(PARAM_START_DATE)))
                .finishDate(parseUtil.parseDate(request.getParameter(PARAM_FINISH_DATE)))
                .isStarted(NO)
                .isFinished(NO)
                .coachRequired(YES)
                .remaining(Integer.parseInt(request.getParameter(PARAM_NUM_OF_STUDENTS))).build();

    }
}
