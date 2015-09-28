/**
 *
 */
package com.boundlessgeo.ps.nj.orchestration.export;

import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * @author ssengupta
 */
@Component
public class MockBillingService {
	public String isUserInGoodStanding(HashMap<String, String> arguments) {
		String user = arguments.get("user");
		String json = "{" + "\"user\": " + user + ","
				+ "\"standing\":{{standing}}" + "}";

		return Math.round(Math.random() * 100) > 30
				? json.replace("{{standing}}", "good")
				: json.replace("{{standing}}", "bad");
	}

	public Object performBilling() {
		return new Object();
	}
}
