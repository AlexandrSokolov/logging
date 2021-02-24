package com.savdev.example.logging.log4j;


import org.apache.log4j.Logger;

public class Log4jLoggingExample {

  private static final Logger logger = Logger.getLogger(Log4jLoggingExample.class.getName());

  public static void main(String ... args) {
    new Log4jLoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message");      // == INFO
    logger.error("This is an error message");   // == ERROR
    logger.warn("This is a warning message"); // == WARNING
    logger.debug("Here is a debug message");      // == DEBUG
  }

}
