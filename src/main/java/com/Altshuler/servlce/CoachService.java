package com.Altshuler.servlce;

import com.Altshuler.model.Coach;

public interface CoachService {
    Coach add(Coach coach);

    boolean validate(String login, String password);

    void logIn(String login, String password);
    void deleteAll();
    Coach getById(int id);
}
