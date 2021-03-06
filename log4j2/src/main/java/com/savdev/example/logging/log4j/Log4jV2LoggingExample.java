package com.savdev.example.logging.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jV2LoggingExample {

  private static final Logger logger = LogManager.getLogger(Log4jV2LoggingExample.class.getName());

  public static void main(String ... args) {
    new Log4jV2LoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message, (originally, log4j2)");      // == INFO
    logger.error("This is an error message, (originally, log4j2)");   // == ERROR
    logger.warn("This is a warning message, (originally, log4j2)"); // == WARNING
    logger.debug("Here is a debug message, (originally, log4j2)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
