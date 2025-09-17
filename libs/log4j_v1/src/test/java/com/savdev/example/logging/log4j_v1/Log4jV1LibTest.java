package com.savdev.example.logging.log4j_v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Log4jV1LibTest {

  @BeforeAll
  public static void setup() {
    System.setProperty("log4j.configuration", "log4j-test.properties");
  }

  @Test
  public void testLog4j2Lib() {
    new Log4V1jLib().performDebugAction();
  }

}
