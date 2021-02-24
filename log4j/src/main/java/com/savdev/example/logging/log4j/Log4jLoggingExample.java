package com.savdev.example.logging.log4j;


import org.apache.log4j.Logger;

public class Log4jLoggingExample {

  private static final Logger logger = Logger.getLogger(Log4jLoggingExample.class.getName());

  public static void main(String ... args) {
    new Log4jLoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message, (originally, log4j)");      // == INFO
    logger.error("This is an error message, (originally, log4j)");   // == ERROR
    logger.warn("This is a warning message, (originally, log4j)"); // == WARNING
    logger.debug("Here is a debug message, (originally, log4j)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
