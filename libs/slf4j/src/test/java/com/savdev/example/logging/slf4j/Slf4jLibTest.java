package com.savdev.example.logging.slf4j;

import org.junit.jupiter.api.Test;

public class Slf4jLibTest {

  @Test
  public void testLog4j2Lib() {
    new Slf4jLib().performDebugAction();
  }

}
