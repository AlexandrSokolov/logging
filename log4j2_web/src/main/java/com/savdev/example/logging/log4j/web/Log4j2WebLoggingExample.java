package com.savdev.example.logging.log4j.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Log4j2WebLoggingExample {

  private static final Logger logger = LogManager.getLogger(Log4j2WebLoggingExample.class.getName());

  @PostConstruct
  public void init() {
    doLog();
  }

  public void doLog() {
    logger.info("This is an info message, (originally, log4j2 war)");      // == INFO
    logger.error("This is an error message, (originally, log4j2 war)");   // == ERROR
    logger.warn("This is a warning message, (originally, log4j2 war)"); // == WARNING
    logger.debug("Here is a debug message, (originally, log4j2 war)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
