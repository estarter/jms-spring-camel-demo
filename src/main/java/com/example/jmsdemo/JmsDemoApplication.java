package com.example.jmsdemo;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpoint;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.MethodJmsListenerEndpoint;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableJms
public class JmsDemoApplication /*implements JmsListenerConfigurer*/ {


    public static void main(String[] args) {
        SpringApplication.run(JmsDemoApplication.class, args);

//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JmsConsumer.class);
//        builder.addPropertyReference("propertyName", "consumer2");  // add dependency to other bean
//        builder.addPropertyValue("propertyName", someValue);      // set property value
//        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
//        factory.registerBeanDefinition("beanName", builder.getBeanDefinition());
    }


    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public DefaultMessageListenerContainer listener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("input-queue");
        container.setMessageListener(new JmsConsumer("1"));
        container.setSessionTransacted(true);
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(5);
        return container;
    }

//    @Override
//    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
//        Function<String, JmsListenerEndpoint> f = (id) -> {
//            SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
//            endpoint.setId("myJmsEndpoint_"+id);
//            endpoint.setDestination("input-queue");
//            endpoint.setMessageListener(new JmsConsumer(id));
//            return endpoint;
//        };
//        registrar.registerEndpoint(f.apply("1"));
//        registrar.registerEndpoint(f.apply("2"));
//    }
}
