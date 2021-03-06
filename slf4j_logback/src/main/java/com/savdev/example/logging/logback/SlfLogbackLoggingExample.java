package com.savdev.example.logging.logback;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlfLogbackLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(SlfLogbackLoggingExample.class);

  public static void main(String ... args) {
    new SlfLogbackLoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message");      // == INFO
    logger.error("This is an error message");   // == ERROR
    logger.warn("This is a warning message"); // == WARNING
    logger.debug("Here is a debug message");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
