/**
 *
 */
package com.boundlessgeo.ps.nj.orchestration.export;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.boundlessgeo.ps.nj.orchestration.InvalidRequestException;

/**
 * @author ssengupta
 */
public class ExportJobRouter extends RouteBuilder {
	private static final String JSON = "application/json";

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet")
				.bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true").contextPath("api")
				.port(8081);

		rest("/api").post("/exportJobs").consumes(JSON).produces(JSON).route()
				.bean(new MockSubscriptionService(), "isAllowed", true)
				.onException(WrongSubscriptionLevelException.class)
				.handled(true)
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
				.setHeader(Exchange.CONTENT_TYPE, constant(JSON)).setBody()
				.simple("${exception.message}").end()
				.onException(InvalidRequestException.class).handled(true)
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
				.setHeader(Exchange.CONTENT_TYPE, constant(JSON)).setBody()
				.simple("${exception.message}").end()
				.bean(new MockBillingService(), "isUserInGoodStanding", true)
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
				.setHeader(Exchange.CONTENT_TYPE, constant(JSON)).setBody()
				.simple("${body}");

		from("direct:getAllExportJobs").transform()
				.constant("There are no jobs");
	}
}
