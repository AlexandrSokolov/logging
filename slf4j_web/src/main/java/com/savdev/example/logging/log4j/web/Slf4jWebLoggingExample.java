package com.savdev.example.logging.log4j.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Slf4jWebLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jWebLoggingExample.class);

  @PostConstruct
  public void init() {
    doLog();
  }

  public void doLog() {
    logger.info("This is an info message, (originally, slf4j war)");      // == INFO
    logger.error("This is an error message, (originally, slf4j war)");   // == ERROR
    logger.warn("This is a warning message, (originally, slf4j war)"); // == WARNING
    logger.debug("Here is a debug message, (originally, slf4j war)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
