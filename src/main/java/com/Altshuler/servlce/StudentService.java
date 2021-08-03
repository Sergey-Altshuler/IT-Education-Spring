package com.Altshuler.servlce;

import com.Altshuler.model.Student;

public interface StudentService {
    Student add(Student student);
    Student getById(int id);
    boolean validate(String login, String password);
    void logIn(String login, String password);
    void deleteAll();
}
