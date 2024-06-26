package com.eikona.tech.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

import javax.net.ssl.SSLException;

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
public class RequestExecutionUtil {
	
	public String executeHttpsGetRequest(String baseUrl, String addRestUrl, String authHeader)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();

		HttpClient httpclient = HttpClient.create().responseTimeout(Duration.ofSeconds(3))
				.secure(t -> t.sslContext(sslContext));

		WebClient webClient = WebClient.builder().baseUrl(baseUrl + addRestUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, authHeader)
				.clientConnector(new ReactorClientHttpConnector(httpclient)).build();

		ResponseEntity<JSONObject> response = webClient.get().retrieve().toEntity(JSONObject.class).block();
		String responeData =response.getBody().toString();
		return responeData;
	}
	
	public String executeHttpsPostRequest(JSONObject requestObj, String baseUrl, String authHeader)
			throws KeyManagementException, NoSuchAlgorithmException, IOException {
		
		SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();

		HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

		WebClient webClient = WebClient.builder().baseUrl(baseUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, authHeader)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();

		ResponseEntity<JSONObject> response = webClient.post().bodyValue(requestObj).retrieve().toEntity(JSONObject.class).block();
		String responeData =response.getBody().toString();
		return responeData;
	}
	
	public String executeHttpsPutRequest(JSONObject requestObj, String httpsUrl, String putUrl, String authHeader)
			throws SSLException {
		
		String responeData = null;
		try {
			SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
					.build();

			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

			WebClient webClient = WebClient.builder().baseUrl(httpsUrl + putUrl)
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
					.defaultHeader(HttpHeaders.AUTHORIZATION, authHeader)
					.clientConnector(new ReactorClientHttpConnector(httpClient))
					.build();

			ResponseEntity<JSONObject> response = webClient.put().bodyValue(requestObj).retrieve()
					.toEntity(JSONObject.class).block();
			
			responeData = response.getBody().toString();
			
			webClient.delete();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responeData;
	}
	
	public String executeHttpsDeleteRequest(String httpsUrl, String deleteUrl, String authHeader)
			throws SSLException {
		
		String responeData = null;
		try {
			SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
					.build();

			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

			WebClient webClient = WebClient.builder().baseUrl(httpsUrl + deleteUrl)
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
					.defaultHeader(HttpHeaders.AUTHORIZATION, authHeader)
					.clientConnector(new ReactorClientHttpConnector(httpClient))
					.build();

			ResponseEntity<JSONObject> response = webClient.delete().retrieve()
					.toEntity(JSONObject.class).block();
			
			response.getBody().toString();
			
			webClient.delete();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responeData;
	}
}
