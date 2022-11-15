package com.Altshuler.servlce;

import com.Altshuler.model.Course;

import java.util.List;

public interface CourseService {
    Course add(Course course);
    Course getById(int id);
    List<Course> getAll();
    void deleteById(int id);
    void deleteAll();
}
