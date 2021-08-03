package com.Altshuler.converters;

import com.Altshuler.model.Coach;

import javax.servlet.http.HttpServletRequest;

import static com.Altshuler.info.ProjectParamConstants.*;

public class CoachConverter implements Converter<Coach> {
    public Coach convert(HttpServletRequest request) {
        return Coach.builder()
                .login(request.getParameter(PARAM_LOGIN))
                .age(Integer.parseInt(request.getParameter(PARAM_AGE)))
                .password(request.getParameter(PARAM_PASSWORD))
                .name(request.getParameter(PARAM_NAME))
                .surname(request.getParameter(PARAM_SURNAME))
                .build();

    }
}
