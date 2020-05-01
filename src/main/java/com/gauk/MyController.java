package com.gauk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.applicationinsights.TelemetryClient;

@RestController
public class MyController {

	public static String uri;
	public static int counter = 0;

	@Autowired
	TelemetryClient telemetryClient;

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello";
	}

	@GetMapping(value = "/snat")
	public String snatTest(@RequestParam(name = "uri") String uri, @RequestParam(name="count") int count) {
		MyController.uri = uri;

		for(int i=0; i<count; i++) {
			new Thread(new MyThread()).start();
			increaseCount();
		}



		/*
		 * for(int i=0; i<count; i++) { try { new Thread(new MyThread()).start();
		 * increaseCount(); }catch(Exception e) { e.printStackTrace();
		 * telemetryClient.trackEvent("Request to " + MyController.uri +
		 * " failed with the exception::: " + e.getMessage());
		 * System.out.println(e.getMessage()); }
		 * 
		 * }
		 */

		telemetryClient.trackEvent("Custom Event::: Sent " + MyController.counter + " requests to + " + MyController.uri );
		return "OK --- " + MyController.counter;
	}


	@GetMapping(value = "/")
	public String welcome() {
		return "Welcome to AppsReadyNext";
	}

	@GetMapping("/check")
	public String checkAppInsights(@RequestParam(name = "key") String key) {
		if(key.equals("gauk")) {
			return System.getenv("APPLICATION_INSIGHTS_KEY");
		}else {
			return "Access Denied.";
		}
	}

	synchronized public static void increaseCount() {
		MyController.counter++;
	}

}

class MyThread implements Runnable{

	@Override
	public void run() {
		new MyService().loadTest(MyController.uri);
		System.out.println(Thread.currentThread().getName() + " running...");
	}

}
