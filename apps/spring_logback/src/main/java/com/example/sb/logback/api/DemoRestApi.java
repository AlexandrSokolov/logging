package com.example.sb.logback.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(DemoRestApi.DEMO_PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface DemoRestApi {
  String DEMO_PATH = "/demo";

  @GET
  String get();
}
