package com.example.sb.logback.service;

import com.savdev.example.logging.jcl.JclLib;
import com.savdev.example.logging.jcl.JclV1_3Lib;
import com.savdev.example.logging.jul.JulLib;
import com.savdev.example.logging.log4j2.Log4j2Lib;
import com.savdev.example.logging.log4j_v1.Log4V1jLib;
import com.savdev.example.logging.slf4j.Slf4jLib;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsDemoService {

  private static final Logger logger = LogManager.getLogger();

  @Autowired
  private Log4j2Service log4j2Service;

  @Autowired
  private Slf4jService slf4jService;

  public void runLoggingDemo() {
    logger.debug("Slf4j call:");
    slf4jService.slf4jLog();
    logger.debug("Log4j2 call:");
    log4j2Service.log4j2Log();

    logger.debug("Slf4j lib call:");
    new Slf4jLib().performDebugAction();
    logger.debug("Log4j2 lib call:");
    new Log4j2Lib().performDebugAction();
    logger.debug("JCL v1.3 lib call:");
    new JclV1_3Lib().performDebugAction();
    //outdated logging solutions:
    logger.debug("Jul lib call:");
    new JulLib().performDebugAction();
    logger.debug("JCL v1.2 lib call:");
    new JclLib().performDebugAction();
    logger.debug("Log4j (v1) lib call:");
    new Log4V1jLib().performDebugAction();
  }
}
