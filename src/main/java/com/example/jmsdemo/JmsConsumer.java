package com.example.jmsdemo;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class JmsConsumer implements SessionAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);
    private String id;

    public JmsConsumer(String id)  {
        logger.info("create consumer {}", id);
        this.id = id;
        this.id = new Random().nextInt(100) + "";
    }

    @Override
    public void onMessage(Message omessage, Session session) throws JMSException {
        TextMessage message = (TextMessage) omessage;
        logger.info("{}: consuming message {}", id, message.getText());
        try {
            Thread.sleep(new Random().nextInt(4000));
        } catch (InterruptedException e) {}
        if (new Random().nextInt(5) > 1) {
            logger.info("{}: finish message {}", id, message.getText());
            try {
                session.commit();
            } catch (JMSException e) {
                logger.error("can't commit the message", e);
            }
        } else {
            logger.info("{}: reschedule message {}", id, message.getText());
            try {
                session.rollback();
            } catch (JMSException e) {
                logger.error("can't rollback the message", e);
            }
        }
    }

}
