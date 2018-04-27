package com.example.jmsdemo;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/produce")
    public void produce() {
        jmsTemplate.convertAndSend("input-queue", "test " + new Random().nextInt(1000));
        logger.info("produce a message");
    }

    @PostConstruct
    public void generate() {
        for (int i = 0; i < 30; i++) {
            produce();
        }
    }
}
