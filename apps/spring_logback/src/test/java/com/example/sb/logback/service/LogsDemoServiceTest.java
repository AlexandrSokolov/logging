package com.example.sb.logback.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
    //you cannot set log level only for specific package:
    //Logger.getLogger("com.savdev").setLevel(Level.FINER);
    Logger.getLogger("").setLevel(Level.FINER);
  }

}
