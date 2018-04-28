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
    private ConsumerCounter consumerCounter;

    public JmsConsumer(ConsumerCounter consumerCounter)  {
        this.consumerCounter = consumerCounter;
    }

    @Override
    public void onMessage(Message omessage, Session session) throws JMSException {
        TextMessage message = (TextMessage) omessage;
        logger.info("consume message {} , parallel tasks amount {}", message.getText(), consumerCounter.inc());
        try {
//            Thread.sleep(new Random().nextInt(4000));
            Thread.sleep(5_000);
        } catch (InterruptedException e) {}
        if (new Random().nextInt(5) > 1) {
            logger.info("finish message {} , parallel tasks amount {}", message.getText(), consumerCounter.dec());
            try {
                session.commit();
            } catch (JMSException e) {
                logger.error("can't commit the message", e);
            }
        } else {
            logger.info("reschedule message {} , parallel tasks amount {}", message.getText(), consumerCounter.dec());
            try {
                session.rollback();
            } catch (JMSException e) {
                logger.error("can't rollback the message", e);
            }
        }
    }

}
