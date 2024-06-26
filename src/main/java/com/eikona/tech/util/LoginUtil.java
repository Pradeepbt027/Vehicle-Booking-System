package com.eikona.tech.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpHeaders;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Component
public class LoginUtil {
	
	public String getBearerToken(String baseUrl)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		
		JSONObject requestObj = getJsonObjectForLogin();
		
		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();

		HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

		WebClient webClient = WebClient.builder().baseUrl(baseUrl)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();

		ResponseEntity<JSONObject> response = webClient.post().bodyValue(requestObj).retrieve().toEntity(JSONObject.class).block();
		String responeData =response.getBody().toString();
		return responeData;
	}

	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectForLogin() {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("username", "admin@gmai.com");
		requestObj.put("password", "Admin@123");
		
		return requestObj;
	}
}
