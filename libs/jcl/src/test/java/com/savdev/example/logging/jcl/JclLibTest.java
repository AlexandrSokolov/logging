package com.savdev.example.logging.jcl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JclLibTest {

  @BeforeAll
  public static void setup() {
    System.setProperty("log4j.configuration", "log4j-test.properties");
  }

  @Test
  public void testLog4j2Lib() {
    new JclLib().performDebugAction();
  }

}
