package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoSomething implements Runnable {
    String topic;
    private static final Logger LOG = LoggerFactory.getLogger(Topic.class);

    DoSomething() {
        topic = null;
    }

    DoSomething(String topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        LOG.info("Topic type: " + topic + " -- Simple Job every 5 seconds: {}", new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
    }
}
