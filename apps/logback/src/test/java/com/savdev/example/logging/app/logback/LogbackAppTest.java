package com.savdev.example.logging.app.logback;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogbackAppTest {

  @BeforeAll
  public static void setup() {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
    //you cannot set log level only for specific package:
    //Logger.getLogger("com.savdev").setLevel(Level.FINER);
    Logger.getLogger("").setLevel(Level.FINER);
  }

  @Test
  public void testLogbackApp() {
    new LogbackApp().runLibs();
  }
}
