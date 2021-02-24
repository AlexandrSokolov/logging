package com.savdev.example.logging;


import com.savdev.example.logging.jul.JulLoggingExample;
import com.savdev.example.logging.log4j.Log4jLoggingExample;
import com.savdev.example.logging.log4j.Log4jV2LoggingExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLoggingExample.class);

  public static void main(String ... args) {
    new Slf4jLoggingExample().doLog();
    new JulLoggingExample().doLog();
    new Log4jLoggingExample().doLog();
    new Log4jV2LoggingExample().doLog();
  }

  void doLog() {
    logger.info("This is an info message");      // == INFO
    logger.error("This is an error message");   // == ERROR
    logger.warn("This is a warning message"); // == WARNING
    logger.debug("Here is a debug message");      // == DEBUG
  }

}
