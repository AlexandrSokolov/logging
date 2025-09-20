package com.example.sb.logback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Slf4jService {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jService.class);

  void slf4jLog() {
    logger.debug("Performing Slf4jService.slf4jLog...");
  }
}
