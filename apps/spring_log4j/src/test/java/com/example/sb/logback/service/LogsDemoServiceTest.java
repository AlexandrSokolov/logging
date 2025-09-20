package com.example.sb.logback.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogsDemoServiceTest {

  @Autowired
  private LogsDemoService logsDemoService;

  @BeforeAll
  public static void setup() {
    enableJulLogging();
  }

  @Test
  public void testLogsDemoService() {
    logsDemoService.runLoggingDemo();
  }

  public static void enableJulLogging() {
    //nothing works:
    //System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    //setting -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager did not help either
    //Log4jBridgeHandler.install(true,"JUL", true);
    //Logger.getLogger("").setLevel(Level.FINER);
  }

}
