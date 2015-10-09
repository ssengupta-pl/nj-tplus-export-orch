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
	public HashMap<String, String> isUserInGoodStanding(
			HashMap<String, String> arguments) {
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("user", arguments.get("user"));
		response.put("standing",
				Math.round(Math.random() * 100) > 30 ? "good" : "bad");

		return response;
	}

	public Object performBilling() {
		return new Object();
	}
}
