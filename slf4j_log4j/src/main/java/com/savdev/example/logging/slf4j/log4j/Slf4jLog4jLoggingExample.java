package com.savdev.example.logging.slf4j.log4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLog4jLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLog4jLoggingExample.class);

  public static void main(String ... args) {
    new Slf4jLog4jLoggingExample().doLog();
  }

  void doLog() {
    logger.info("This is an info message");      // == INFO
    logger.error("This is an error message");   // == ERROR
    logger.warn("This is a warning message"); // == WARNING
    logger.debug("Here is a debug message");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
