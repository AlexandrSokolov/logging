package com.savdev.example.logging.app.log4j2;

import org.apache.logging.log4j.jul.Log4jBridgeHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log4j2AppTest {

  @BeforeAll
  public static void setup() {
    //nothing works:
    //System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    //setting -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager did not help either
    //Log4jBridgeHandler.install(true,"JUL", true);
    //Logger.getLogger("").setLevel(Level.FINER);
  }

  @Test
  public void testLog4j2App() {
    new Log4j2App().runLibs();
  }
}
