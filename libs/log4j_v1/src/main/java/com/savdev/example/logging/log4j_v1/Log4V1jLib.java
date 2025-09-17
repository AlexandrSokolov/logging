package com.savdev.example.logging.log4j_v1;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4V1jLib {

  private static final Logger logger = LogManager.getLogger(Log4V1jLib.class);

  public void performDebugAction() {
    logger.debug("Performing action...");
  }
}
