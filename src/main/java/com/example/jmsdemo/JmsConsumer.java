package com.example.jmsdemo;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsConsumer implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);
    protected final Session session;
    protected final Connection connection;
    private final String id;

    public JmsConsumer(ConnectionFactory connectionFactory, String queueName, String id)  {
        this.id = id;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            Destination queue = session.createQueue(queueName);

            javax.jms.MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(this);
            connection.start();
        } catch (JMSException e) {
            throw new IllegalStateException("can't start consumer");
        }
    }

    public void stop() throws JMSException {
        connection.close();
    }

    @Override
    public void onMessage(Message message) {
        logger.info("{}: consuming message {}", id, message);
        try {
            Thread.sleep(new Random().nextInt(4) * 1000);
        } catch (InterruptedException e) {}
        if (new Random().nextInt(3) > 1) {
            logger.info("{}: finish message {}", id, message);
            try {
                session.commit();
            } catch (JMSException e) {
                logger.error("can't commit the message", e);
            }
        } else {
            logger.info("reschedule message {}", message);
            try {
                session.rollback();
            } catch (JMSException e) {
                logger.error("can't rollback the message", e);
            }
        }
    }

}
