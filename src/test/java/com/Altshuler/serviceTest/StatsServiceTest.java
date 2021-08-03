package com.Altshuler.serviceTest;

import com.Altshuler.TestInfo.TestDataCreator;
import com.Altshuler.servlce.StatsService;
import com.Altshuler.servlce.StatsServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsServiceTest {
    StatsService statsService = new StatsServiceImpl();

    @BeforeClass
    void generateForTesting() {
        statsService.add(TestDataCreator.createTestGetAllStats());
        statsService.add(TestDataCreator.createTestAddStats());
    }

    @Test
    void getAll() {
        assertEquals(2, statsService.getAll().size());
    }

    @AfterClass
    void clearDatabase() {
        statsService.deleteAll();
    }
}
