package com.example.jmsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ImportResource("${route:camel.xml}")
public class JmsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsDemoApplication.class);
    }

}
