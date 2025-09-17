package com.savdev.example.logging.jul;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class JulLibTest {

  @BeforeAll
  public static void setup() throws IOException {
    try (InputStream stream = JulLibTest.class.getClassLoader().getResourceAsStream("jul.logging.properties")){
      LogManager.getLogManager().readConfiguration(stream);
    }
  }

  @Test
  public void testJulLog() {
    new JulLib().performDebugAction();
  }

}
