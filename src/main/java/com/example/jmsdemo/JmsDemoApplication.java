package com.example.jmsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ImportResource("classpath:camel.xml")
public class JmsDemoApplication /*implements JmsListenerConfigurer*/ {


    public static void main(String[] args) {
        SpringApplication.run(JmsDemoApplication.class);

//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JmsConsumer.class);
//        builder.addPropertyReference("propertyName", "consumer2");  // add dependency to other bean
//        builder.addPropertyValue("propertyName", someValue);      // set property value
//        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
//        factory.registerBeanDefinition("beanName", builder.getBeanDefinition());
    }

//    @Bean
//    ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(),
//                "camel/*");
//        servlet.setName("CamelServlet");
//        return servlet;
//    }


//    @Autowired
//    ConnectionFactory connectionFactory;
//
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        return factory;
//    }
//
//    @Bean
//    public DefaultMessageListenerContainer listener() {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setDestinationName("input-queue");
//        container.setMessageListener(new JmsConsumer("1"));
//        container.setSessionTransacted(true);
//        container.setConcurrentConsumers(5);
//        container.setMaxConcurrentConsumers(5);
//        return container;
//    }

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
