package com.example.jmsdemo;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class JmsConsumer implements SessionAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);
    private final String id;

    public JmsConsumer(String id)  {
        this.id = id;
    }

    @Override
    public void onMessage(Message message, Session session) {
        logger.info("{}: consuming message {}", id, message);
//        try {
//            Thread.sleep(new Random().nextInt(4) * 1000);
//        } catch (InterruptedException e) {}
        if (new Random().nextInt(5) > 1) {
            logger.info("{}: finish message {}", id, message);
            try {
                session.commit();
            } catch (JMSException e) {
                logger.error("can't commit the message", e);
            }
        } else {
            logger.info("{}: reschedule message {}", id, message);
            try {
                session.rollback();
            } catch (JMSException e) {
                logger.error("can't rollback the message", e);
            }
        }
    }

}
