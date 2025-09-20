package com.savdev.example.logging.http.client;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.savdev.example.logging.http.api.ItemRestApi;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest
public class ItemRestClientTest {

  public static final String TEST_ITEM = "\"Test item\"";

  @Test
  void testGetItem(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
    var wireMock = wmRuntimeInfo.getWireMock();
    wireMock.register(get(urlPathEqualTo(ItemRestApi.BASE_URL))
      .willReturn(
        ok()
          .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
          .withHeader(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
          .withBody(TEST_ITEM)));

    try (var client = new ItemRestClient(wmRuntimeInfo.getHttpBaseUrl())) {
      var item = client.item();
      Assertions.assertEquals(TEST_ITEM, item);
    }
  }
}
