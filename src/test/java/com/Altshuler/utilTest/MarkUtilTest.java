package com.Altshuler.utilTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.model.Course;
import com.Altshuler.util.MarkUtil;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkUtilTest {
    MarkUtil markUtil = new MarkUtil();
    Course course = TestDataCreator.createEmptyTestCourse();

    @Test
    void initializeMarks() {
        assertThrows(NullPointerException.class, () -> markUtil.initializeMarks(course));

    }

    @Test
    void getIsFinished() {
        assertThrows(NullPointerException.class, () -> markUtil.getIsFinished(course));
    }

    @Test
    void calculateIndividualStatistics() {
        assertThrows(NullPointerException.class, () -> markUtil.calculateIndividualStatistics(course));
    }

    @Test
    void calculateTotalStatistics() {
        assertThrows(NullPointerException.class, () -> markUtil.calculateTotalStatistics(course));
    }
}
