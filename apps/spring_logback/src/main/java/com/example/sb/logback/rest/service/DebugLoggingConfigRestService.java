package com.example.sb.logback.rest.service;

import com.example.sb.logback.api.DebugLoggingConfigApi;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

@Component
public class DebugLoggingConfigRestService implements DebugLoggingConfigApi {
  @Override
  public void enableDebugLevel(String packageName) {
    LoggingSystem.get(DebugLoggingConfigRestService.class.getClassLoader())
      .setLogLevel(packageName, LogLevel.DEBUG);
  }

  @Override
  public void resetDebugLevel(String packageName) {
    LoggingSystem.get(DebugLoggingConfigRestService.class.getClassLoader())
      .setLogLevel(packageName, LogLevel.INFO);
  }
}
