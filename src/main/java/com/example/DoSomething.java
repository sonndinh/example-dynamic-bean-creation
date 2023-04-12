package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoSomething implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FirstSubclass.class);

    @Override
    public void run() {
        LOG.info("Simple Job every 5 seconds: {}", new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
    }
}
