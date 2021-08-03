package com.Altshuler.servlce;

public interface AdminService {
    void initialize();
    void changeProperties(String login, String password);
    boolean validate(String login, String password);
}
