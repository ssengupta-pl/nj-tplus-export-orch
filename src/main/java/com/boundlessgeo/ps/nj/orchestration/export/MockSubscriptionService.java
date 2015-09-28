/**
 *
 */
package com.boundlessgeo.ps.nj.orchestration.export;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.boundlessgeo.ps.nj.orchestration.InvalidRequestException;

/**
 * @author ssengupta
 */
@Component
public class MockSubscriptionService {
	public void isAllowed(HashMap<String, String> arguments)
			throws WrongSubscriptionLevelException, InvalidRequestException {
		String subscriptionLevel = arguments.get("subscriptionLevel");
		String requestedResource = arguments.get("requestedResource");
		String requestedOperation = arguments.get("requestedOperation");

		if (subscriptionLevel == null || "".equals(subscriptionLevel)
				|| requestedResource == null || "".equals(requestedResource)
				|| requestedOperation == null
				|| "".equals(requestedOperation)) {
			throw new InvalidRequestException(
					"Request is missing critical information");
		}

		if ("Silver".equals(subscriptionLevel)) {
			if ("exportJobs".equals(requestedResource)) {
				if ("DELETE".equalsIgnoreCase(requestedOperation)) {
					throw new WrongSubscriptionLevelException(
							requestedOperation + " on " + requestedResource
									+ " not allowed for subscription level "
									+ subscriptionLevel);
				}
			}
		}
	}
}
