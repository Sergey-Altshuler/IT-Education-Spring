package com.Altshuler.utilTest;

import com.Altshuler.util.CourseGroupUtil;
import org.testng.annotations.Test;

import static com.Altshuler.TestInfo.TestConstants.TEST_GROUP_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseGroupUtilTest {
    CourseGroupUtil courseGroupUtil = new CourseGroupUtil();

    @Test
    void getCourseGroup() {
        assertEquals(1, courseGroupUtil.getCourseGroup(TEST_GROUP_NAME));
        assertEquals(2, courseGroupUtil.getCourseGroup(TEST_GROUP_NAME));
    }
}
