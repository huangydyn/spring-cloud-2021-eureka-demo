package com.neo.controller;

import com.neo.remote.HelloFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ConsumerController {

    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    HelloFeign HelloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        logger.info("consumer start request name {}", name);
        return HelloRemote.hello(name);
    }

    @Scheduled(fixedDelay = 1000)
    public String index() {
        logger.info("scheduled start request name ");
        return HelloRemote.hello(UUID.randomUUID().toString());
    }
}