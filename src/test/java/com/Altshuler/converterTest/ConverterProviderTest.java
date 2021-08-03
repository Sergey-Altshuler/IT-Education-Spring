package com.Altshuler.converterTest;

import com.Altshuler.converters.ConverterProvider;
import com.Altshuler.model.Coach;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ConverterProviderTest {
    @Test
    void getConverter() {
        try {
            assertEquals(ConverterProvider.getConverterMap().get(Coach.class), ConverterProvider.getConverter(Coach.class));
        } catch (Exception e) {
            fail();
        }
    }
}
