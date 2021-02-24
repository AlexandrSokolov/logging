package com.savdev.example.logging.jul;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JulLoggingExample {

  private static final Logger logger = logger();

  public static void main(String ... args) {
    new JulLoggingExample().doLog();
  }

  public void doLog() {
    logger.info("This is an info message (originally, JUL)");      // == INFO
    logger.severe("This is an error message (originally, JUL)");   // == ERROR
    logger.warning("This is a warning message (originally, JUL)"); // == WARNING
    logger.fine("Here is a debug message (originally, JUL)");      // == DEBUG
    logger.info(logger.getClass().getName());
  }

  public static Logger logger() {

    try (InputStream stream = JulLoggingExample.class.getClassLoader().getResourceAsStream("logging.properties")) {
      LogManager.getLogManager().readConfiguration(stream);
      return Logger.getLogger(JulLoggingExample.class.getName());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
