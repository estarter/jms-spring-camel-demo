package com.example.jmsdemo;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsConsumer implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);
    private String id;

    public JmsConsumer(String id)  {
        logger.info("create consumer {}", id);
        this.id = id;
        this.id = new Random().nextInt(100) + "";
    }

    @Override
    public void onMessage(Message message) {
        logger.info("{}: consuming message {}", id, message);
        try {
            Thread.sleep(new Random().nextInt(4000));
        } catch (InterruptedException e) {}
        if (new Random().nextInt(5) > 1) {
            logger.info("{}: finish message {}", id, message);
//            try {
//                session.commit();
//            } catch (JMSException e) {
//                logger.error("can't commit the message", e);
//            }
        } else {
            logger.info("{}: reschedule message {}", id, message);
//            try {
//                session.rollback();
//            } catch (JMSException e) {
//                logger.error("can't rollback the message", e);
//            }
        }
    }

}
