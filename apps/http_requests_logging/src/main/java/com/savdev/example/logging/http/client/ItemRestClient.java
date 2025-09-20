package com.savdev.example.logging.http.client;

import com.savdev.example.logging.http.api.ItemRestApi;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.io.Closeable;
import java.io.IOException;

public class ItemRestClient implements Closeable {

  final Client client = ClientBuilder.newClient();
  final ResteasyWebTarget target;


  public ItemRestClient(String url) {
    this.target = (ResteasyWebTarget) client.target(url);;
  }

  public String item() {
    return target.proxy(ItemRestApi.class).item();
  }

  @Override
  public void close() throws IOException {
    this.client.close();
  }
}
