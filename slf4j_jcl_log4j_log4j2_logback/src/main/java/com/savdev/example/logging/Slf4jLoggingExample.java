package com.savdev.example.logging;


import com.savdev.example.logging.jcl.logback.JclLog4jLoggingExample;
import com.savdev.example.logging.jul.JulLoggingExample;
import com.savdev.example.logging.log4j.Log4jLoggingExample;
import com.savdev.example.logging.log4j.Log4jV2LoggingExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Slf4jLoggingExample {

  static {
    // Verify that it is not installed yet; other dependency might have already
    // installed it which would cause duplicate handler
    // Unfortunately SLF4J provides no built-in method for this procedure, so
    // race condition between different classes could happen
    if (!SLF4JBridgeHandler.isInstalled()) {
      // Remove default handler logging to System.err
      SLF4JBridgeHandler.removeHandlersForRootLogger();
      // Install the SLF4J handler
      SLF4JBridgeHandler.install();
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLoggingExample.class);

  public static void main(String ... args) {
    new Slf4jLoggingExample().doLog();
    new JulLoggingExample().doLog();
    new Log4jLoggingExample().doLog();
    new Log4jV2LoggingExample().doLog();
    new JclLog4jLoggingExample().doLog();
  }

  void doLog() {
    logger.info("This is an info message, (originally, slf4j with logback)");      // == INFO
    logger.error("This is an error message, (originally, slf4j with logback)");   // == ERROR
    logger.warn("This is a warning message, (originally, slf4j with logback)"); // == WARNING
    logger.debug("Here is a debug message, (originally, slf4j with logback)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

}
