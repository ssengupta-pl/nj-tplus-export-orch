/**
 *
 */
package com.boundlessgeo.ps.nj.orchestration.export;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author ssengupta
 */
public class GoodStandingProcessor implements Processor {

	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange);
	}

}
