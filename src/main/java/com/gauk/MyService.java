package com.gauk;

import org.springframework.web.client.RestTemplate;

public class MyService {

	public String loadTest(String uri) {
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		return result;
	}
	
}
