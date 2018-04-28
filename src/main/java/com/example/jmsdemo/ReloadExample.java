package com.example.jmsdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ReloadExample {

    @Value("${foo.bar:undef}")
    private String value;

    @RequestMapping("/test")
    public String sayValue() {
        return value;
    }

}
