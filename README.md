# JMS Spring Camel demo

This project illustrate how to integrate camel routes for jms (ActiveMQ) queues in spring project.

Note: as of today (28 apr 2018) camel does not support spring-boot 2.

## How to run ActiveMQ

To run an ActiveMQ broker one can use a docker container:
```bash
docker run --name='activemq' -it --rm \
    -e 'ACTIVEMQ_CONFIG_MINMEMORY=512' \
    -e 'ACTIVEMQ_CONFIG_MAXMEMORY=2048'\
    -p 8161:8161 -p 61616:61616 -p 61613:61613   webcenter/activemq:latest
```

ActiveMQ web interface will be available [here](http://localhost:8161/) , credendials: admin / admin
