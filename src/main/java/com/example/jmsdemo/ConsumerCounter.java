package com.example.jmsdemo;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class ConsumerCounter {

    AtomicInteger amount = new AtomicInteger(0);

    public int inc() {
        return amount.incrementAndGet();
    }

    public int dec() {
        return amount.decrementAndGet();
    }

}
