package com.jsonlogprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes=JsonlogProcessorApplication.class, args ={"src\\test\\resources\\logfile.txt"})
class CommandLineTaskExecutorTest {

    @Test
    public void contextLoads() {
    }
}
