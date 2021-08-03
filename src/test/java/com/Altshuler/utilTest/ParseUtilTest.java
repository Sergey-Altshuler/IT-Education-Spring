package com.Altshuler.utilTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.model.Student;
import com.Altshuler.util.ParseUtil;
import org.testng.annotations.Test;

import java.sql.Date;
import java.time.LocalDate;

import static com.Altshuler.TestInfo.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParseUtilTest {
    ParseUtil parseUtil = new ParseUtil();

    @Test
    void parseRightDate() {
        String stringDate = SUCCESS_DATE;
        Date date = Date.valueOf(LocalDate.parse(stringDate));
        assertEquals(date, parseUtil.parseDate(stringDate));
    }

    @Test
    void parseWrongDate() {
        assertThrows(Exception.class, () -> parseUtil.parseDate(FAIL_DATE));
    }

    @Test
    void parseStudent() {
        Student student = TestDataCreator.createTestStudentForParsing();
        assertEquals("student student", parseUtil.parseStudent(student));
    }

    @Test
    void encryptPassword() {
        assertEquals("********", parseUtil.encryptPassword(PASSWORD));
    }
}
