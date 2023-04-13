package com.example;

import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.TaskScheduler;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Factory
public class TopicFactory {
    private final TaskScheduler taskScheduler;
    private static final Logger LOG = LoggerFactory.getLogger(TopicFactory.class);

    TopicFactory(@Named(TaskExecutors.SCHEDULED) TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @EachBean(TopicConfiguration.class)
    Topic createPublisher(TopicConfiguration topicConfiguration) {
        final String topic = topicConfiguration.getName();
        final String type = topicConfiguration.getType();
        final String entity = topicConfiguration.getDpdEntity();
        LOG.info("Create topic " + topic + " with type " + type + " and DPD entity " + entity);
        return new Topic(type, entity, taskScheduler);
    }
}
