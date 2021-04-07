package com.savdev.example.logging.log4j.web;


import com.savdev.example.logging.jcl.logback.JclLog4jLoggingExample;
import com.savdev.example.logging.jul.JulLoggingExample;
import com.savdev.example.logging.log4j.Log4jLoggingExample;
import com.savdev.example.logging.log4j.Log4jV2LoggingExample;
import com.savdev.example.logging.logback.SlfLogbackLoggingExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.UUID;

@Singleton
@Startup
public class Slf4jWebLoggingExample {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jWebLoggingExample.class);

  @PostConstruct
  public void init() {
    doLog();
    new JulLoggingExample().doLog();
    new Log4jLoggingExample().doLog();
    new JclLog4jLoggingExample().doLog();
    new Log4jV2LoggingExample().doLog();
    new SlfLogbackLoggingExample().doLog();
  }

  public void doLog() {
    try {
      MDC.put("user", UUID.randomUUID().toString());
      logger.info("This is an info message, (originally, slf4j war)");      // == INFO
      logger.error("This is an error message, (originally, slf4j war)");   // == ERROR
      logger.warn("This is a warning message, (originally, slf4j war)"); // == WARNING
      logger.debug("Here is a debug message, (originally, slf4j war)");      // == DEBUG
      logger.info(logger.getClass().getName());
    } finally {
      MDC.clear();
    }
  }

}
