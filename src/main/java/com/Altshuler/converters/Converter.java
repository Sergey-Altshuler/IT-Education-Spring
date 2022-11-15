package com.Altshuler.converters;

import javax.servlet.http.HttpServletRequest;

public interface Converter<T> {
    T convert(HttpServletRequest request);
}
