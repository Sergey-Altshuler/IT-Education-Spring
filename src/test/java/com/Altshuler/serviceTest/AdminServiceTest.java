package com.Altshuler.serviceTest;

import com.Altshuler.servlce.AdminService;
import com.Altshuler.servlce.AdminServiceImpl;
import org.testng.annotations.Test;

import static com.Altshuler.TestInfo.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminServiceTest  {
    AdminService adminService = new AdminServiceImpl();

    @Test
    void validate() {
        adminService.initialize();
        assertTrue(adminService.validate(ADMIN_LOGIN, ADMIN_PASSWORD));
        adminService.changeProperties(ADMIN_LOGIN_CHANGED, ADMIN_PASSWORD_CHANGED);
        assertTrue(adminService.validate(ADMIN_LOGIN_CHANGED, ADMIN_PASSWORD_CHANGED));
    }

}
