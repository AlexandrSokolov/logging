package com.savdev.example.logging.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Lib {

  private static final Logger logger = LogManager.getLogger();

  public void performDebugAction() {
    logger.debug(() -> "Performing action...");
  }
}
