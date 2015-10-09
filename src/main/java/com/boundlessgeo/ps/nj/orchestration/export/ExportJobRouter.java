/**
 *
 */
package com.boundlessgeo.ps.nj.orchestration.export;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * @author ssengupta
 */
public class ExportJobRouter extends RouteBuilder {
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

		// rest("/api").post("/exportJobs").consumes("application/json")
		// .produces("application/json").route()
		// .bean(new MockSubscriptionService(), "isAllowed", true)
		// .onException(WrongSubscriptionLevelException.class)
		// .handled(true)
		// .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .setBody().simple("{\"error\": \"${exception.message}\"}").end()
		// .onException(InvalidRequestException.class).handled(true)
		// .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .setBody().simple("{\"error\": \"${exception.message}\"}").end()
		// .bean(new MockBillingService(), "isUserInGoodStanding", true)
		// .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .setBody().simple("${body}");

		rest("/api").post("/exportJobs").type(ExportJob.class)
				.to("direct:checkSubscription");

		from("direct:checkSubscription")
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader(Exchange.HTTP_URI,
						simple("http://localhost:8082/mss/${body.subscriptionLevel}/${body.requestedResource}/${body.requestedOperation}/check"))
				.marshal().json(JsonLibrary.Jackson).to("log:out")
				.to("http://dummyhost?throwExceptionOnFailure=false")
				.unmarshal().json(JsonLibrary.Jackson).to("log:out").choice()
				.when(simple("${body[result]} == 'Error'"))
				.to("direct:subscriptionCheckFailed").otherwise()
				.to("direct:checkGoodStanding");

		from("direct:subscriptionCheckFailed").to("log:out");

		from("direct:checkGoodStanding")
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader(Exchange.HTTP_URI,
						simple("http://localhost:8082/mbs/users/${body.user}"))
				.marshal().json(JsonLibrary.Jackson).to("log:out")
				.to("http://dummyhost?throwExceptionOnFailure=false")
				.unmarshal().json(JsonLibrary.Jackson).to("log:out").choice();

		from("direct:getAllExportJobs").transform()
				.constant("There are no jobs");
	}
}
