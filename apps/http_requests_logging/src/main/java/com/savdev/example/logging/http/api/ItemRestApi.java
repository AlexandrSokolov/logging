package com.savdev.example.logging.http.api;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path(ItemRestApi.BASE_URL)
@Produces(MediaType.APPLICATION_JSON)
public interface ItemRestApi {
  String BASE_URL = "/items";

  @GET
  String item();
}
