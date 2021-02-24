package com.savdev.example.logging.jcl.logback;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JclLog4jLoggingExample {

  private static final Log logger = LogFactory.getLog(JclLog4jLoggingExample.class);

  public static void main(String ... args) {
    new JclLog4jLoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message (originally, jcl with log4j)");      // == INFO
    logger.error("This is an error message (originally, jcl with log4j)");   // == ERROR
    logger.warn("This is a warning message (originally, jcl with log4j)"); // == WARNING
    logger.debug("Here is a debug message (originally, jcl with log4j)");      // == DEBUG
  }

}
