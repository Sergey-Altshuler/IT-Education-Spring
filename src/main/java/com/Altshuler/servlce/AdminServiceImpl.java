package com.Altshuler.servlce;

import com.Altshuler.dao.DAOAdmin;
import com.Altshuler.dao.DAOAdminImpl;
import com.Altshuler.model.Admin;

import java.sql.SQLException;

import static com.Altshuler.info.ProjectNamedConstants.START_ADMIN_LOGIN;
import static com.Altshuler.info.ProjectNamedConstants.START_ADMIN_PASSWORD;

public class AdminServiceImpl implements AdminService{
    private final DAOAdmin daoAdmin = new DAOAdminImpl();

    public void initialize() {
        Admin admin = Admin.builder().login(START_ADMIN_LOGIN).password(START_ADMIN_PASSWORD).build();
        try {
            daoAdmin.save(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeProperties(String login, String password) {
        try {
            Admin admin = daoAdmin.get(1, Admin.class);
            admin.setLogin(login);
            admin.setPassword(password);
            daoAdmin.save(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validate(String login, String password) {
        Admin admin = Admin.builder().build();
        try {
            admin = daoAdmin.get(1, Admin.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin.getLogin().equals(login) && admin.getPassword().equals(password);
    }

}
