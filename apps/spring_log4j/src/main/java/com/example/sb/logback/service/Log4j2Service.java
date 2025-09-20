package com.example.sb.logback.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Log4j2Service {

  private static final Logger logger = LogManager.getLogger();

  void log4j2Log() {
    logger.debug("Performing Log4j2Service.log4j2Log...");
  }
}
