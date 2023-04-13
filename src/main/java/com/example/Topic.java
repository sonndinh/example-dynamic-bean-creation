package com.example;

import io.micronaut.scheduling.TaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Topic {
    private final String type;
    private final String entity;
    private TaskScheduler taskScheduler;
    private static final Logger LOG = LoggerFactory.getLogger(Topic.class);
    private static int count = 0;

    Topic(String type, String entity, TaskScheduler taskScheduler) {
        this.type = type;
        this.entity = entity;
        this.taskScheduler = taskScheduler;
        taskScheduler.scheduleAtFixedRate(Duration.ofSeconds(3), Duration.ofSeconds(5), new DoSomething(type));
    }
}
