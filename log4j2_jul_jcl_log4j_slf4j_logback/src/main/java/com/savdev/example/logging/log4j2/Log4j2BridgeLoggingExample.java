package com.savdev.example.logging.log4j2;


import com.savdev.example.logging.jcl.logback.JclLog4jLoggingExample;
import com.savdev.example.logging.jul.JulLoggingExample;
import com.savdev.example.logging.log4j.Log4jLoggingExample;

import com.savdev.example.logging.log4j.Log4jV2LoggingExample;
import com.savdev.example.logging.logback.SlfLogbackLoggingExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2BridgeLoggingExample {

  static {
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
  }

  private static final Logger logger = LogManager.getLogger(Log4j2BridgeLoggingExample.class);

  public static void main(String ... args) {
    new Log4j2BridgeLoggingExample().doLog();
    new JulLoggingExample().doLog();
    new Log4jLoggingExample().doLog();
    new Log4jV2LoggingExample().doLog();
    new JclLog4jLoggingExample().doLog();
    new SlfLogbackLoggingExample().doLog();
  }

  void doLog() {
    logger.info("This is an info message, (originally, log4j2 with log4j2)");      // == INFO
    logger.error("This is an error message, (originally, log4j2 with log4j2)");   // == ERROR
    logger.warn("This is a warning message, (originally, log4j2 with log4j2)"); // == WARNING
    logger.debug("Here is a debug message, (originally, log4j2 with log4j2)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
