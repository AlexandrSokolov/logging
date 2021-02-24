package com.savdev.example.logging.jcl.log4j;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JclLog4jLoggingExample {

  private static final Log logger = LogFactory.getLog(JclLog4jLoggingExample.class);

  public static void main(String ... args) {
    new JclLog4jLoggingExample().doLog();
  }

  void doLog() {
    logger.info("This is an info message");      // == INFO
    logger.error("This is an error message");   // == ERROR
    logger.warn("This is a warning message"); // == WARNING
    logger.debug("Here is a debug message");      // == DEBUG
  }

}
