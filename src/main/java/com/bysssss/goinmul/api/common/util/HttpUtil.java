package com.bysssss.goinmul.api.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
	private static RestTemplate RestTemplate = new RestTemplate();
	static {
		RestTemplate = new RestTemplate();
	}
	
	public static ResponseEntity<String> get(String url) {
		URI uri = null;
		ResponseEntity<String> result = null;
		try {
			uri = new URI(url);
			result = RestTemplate.getForEntity(uri, String.class);
		} catch (URISyntaxException e) {
			return null;
		} catch (RestClientException e) {
			return null;
		}
		
		return result;
    }

	public static <T> ResponseEntity<String> post(String url,HttpEntity<T> request) {

		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			return null;
		}

		ResponseEntity<String> response = RestTemplate.postForEntity(url, request, String.class);
		return response;
	}
	
	public static ResponseEntity<String> exchange(String url) {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			return null;
		}
		
		HttpHeaders headers = new HttpHeaders();
	    headers.set("X-COM-PERSIST", "true"); 
	    headers.set("X-COM-LOCATION", "USA");
	    String body = "";
	 
	    HttpEntity<String> request = new HttpEntity<String>(body, headers);
	    ResponseEntity<String> response = RestTemplate.exchange(uri, HttpMethod.GET, request, String.class);
		return response;
    }
	
	public static String request(HttpMethod method, String url, String body, String authorization) {
		StringBuffer result = new StringBuffer();
		
		HttpURLConnection conn = null;
		try {
			URL urlObject = new URL(url);
			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setRequestMethod(method.name());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			//conn.setRequestProperty(HttpHeaders.AUTHORIZATION, authorization);
			
			if( body != null) {
				BufferedWriter outputStreamWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
				outputStreamWriter.write(body);
				outputStreamWriter.close();
				conn.getOutputStream().flush();
				conn.getOutputStream().close();
			}
			
			InputStream connGetInputStream = null;
			HttpStatus httpStatus = HttpStatus.valueOf(conn.getResponseCode());
			switch(httpStatus) {
				case OK:
					connGetInputStream = conn.getInputStream();
					break;
				case BAD_REQUEST:
				case UNAUTHORIZED:
				case FORBIDDEN:
				case NOT_FOUND:
				case METHOD_NOT_ALLOWED:
				case UNSUPPORTED_MEDIA_TYPE:
				case INTERNAL_SERVER_ERROR:
				case BAD_GATEWAY:
					connGetInputStream = conn.getErrorStream();
					break;
				default:
					break;
			}
			
			BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(connGetInputStream, "UTF-8"));
			for( String line=null; (line=inputStreamReader.readLine()) != null;) {
				result.append(line);
			}
			inputStreamReader.close();
			connGetInputStream.close();
		} catch (IllegalArgumentException | IOException e) {
			return null;
		} finally {
			if( conn != null) {
				conn.disconnect();
			}
		}
		
		return result.toString();
	}
}
