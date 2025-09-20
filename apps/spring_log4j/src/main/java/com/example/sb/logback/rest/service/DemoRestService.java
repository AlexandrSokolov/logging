package com.example.sb.logback.rest.service;


import com.example.sb.logback.api.DemoRestApi;
import com.example.sb.logback.service.LogsDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoRestService implements DemoRestApi {

  @Autowired
  private LogsDemoService libsConsumerService;

  @Override
  public String get() {
    libsConsumerService.runLoggingDemo();
    return "\"Successful\"";
  }
}
