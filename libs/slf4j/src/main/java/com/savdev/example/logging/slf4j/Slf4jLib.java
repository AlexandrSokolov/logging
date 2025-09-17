package com.savdev.example.logging.slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLib {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLib.class);

  public void performDebugAction() {
    logger.debug("Performing action...");
  }
}
