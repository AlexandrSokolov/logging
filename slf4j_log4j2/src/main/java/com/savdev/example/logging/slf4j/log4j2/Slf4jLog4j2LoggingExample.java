package com.savdev.example.logging.slf4j.log4j2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLog4j2LoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLog4j2LoggingExample.class);

  public static void main(String ... args) {
    new Slf4jLog4j2LoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message, (originally, slf4j with log4j2)");      // == INFO
    logger.error("This is an error message, (originally, slf4j with log4j2)");   // == ERROR
    logger.warn("This is a warning message, (originally, slf4j with log4j2)"); // == WARNING
    logger.debug("Here is a debug message, (originally, slf4j with log4j2)");      // == DEBUG
  }

}
