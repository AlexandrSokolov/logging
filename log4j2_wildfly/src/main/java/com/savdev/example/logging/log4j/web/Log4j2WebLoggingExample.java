package com.savdev.example.logging.log4j.web;

import com.savdev.example.logging.jcl.logback.JclLog4jLoggingExample;
import com.savdev.example.logging.jul.JulLoggingExample;
import com.savdev.example.logging.log4j.Log4jLoggingExample;
import com.savdev.example.logging.log4j.Log4jV2LoggingExample;
import com.savdev.example.logging.logback.SlfLogbackLoggingExample;
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.UUID;

@Singleton
@Startup
public class Log4j2WebLoggingExample {

  private static final Logger logger = LogManager.getLogger(Log4j2WebLoggingExample.class.getName());

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
    try (final CloseableThreadContext.Instance ctc =
           CloseableThreadContext.put("user_session_id", UUID.randomUUID().toString())) {
      String note = "(originally, log4j2 war)";
      logger.info(() -> "This is an info message, via supplier, " + note);      // == INFO
      logger.error("This is an error message, {}", note);   // == ERROR
      logger.warn("This is a warning message, {}", note); // == WARNING
      logger.debug("Here is a debug message, {}", note);      // == DEBUG
      logger.info(logger.getClass().getName());
    }
  }

}
