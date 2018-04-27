package com.example.jmsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@SpringBootApplication
@EnableJms
@ImportResource("classpath:camel.xml")
public class JmsDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(JmsDemoApplication.class);
    }

}
