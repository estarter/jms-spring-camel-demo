package com.example.jmsdemo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
class RestApi extends RouteBuilder {

    @Override
    public void configure() {
        CamelContext context = new DefaultCamelContext();
        restConfiguration();
//        rest("/api/");
//        from("direct:remoteService");
        from("jms:queue:input-queue")
                .routeId("test route")
                .log("run route for '${body}'")
                .to("jms:queue:output-1");
//        from("jms:queue:output-1")
//                .routeId("consume")
//                .bean(JmsConsumer.class);
    }
}