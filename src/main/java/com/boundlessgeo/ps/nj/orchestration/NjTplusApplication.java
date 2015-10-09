package com.boundlessgeo.ps.nj.orchestration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.boundlessgeo.ps.nj.orchestration.export.ExportJobRouter;

@SpringBootApplication(exclude = { DispatcherServletAutoConfiguration.class })
public class NjTplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjTplusApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean bean = new ServletRegistrationBean(
				new CamelHttpTransportServlet(), "/tplus/*");
		bean.setName("CamelServlet");

		return bean;
	}

	@Bean
	public SpringCamelContext camelContext(
			ApplicationContext applicationContext) throws Exception {
		SpringCamelContext camelContext = new SpringCamelContext(
				applicationContext);
		camelContext.addRoutes(routeBuilder());
		camelContext.setTracing(true);
		return camelContext;
	}

	@Bean
	public RouteBuilder routeBuilder() {
		return new ExportJobRouter();
	}
}
