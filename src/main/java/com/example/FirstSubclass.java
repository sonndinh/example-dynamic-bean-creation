package com.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.scheduling.TaskScheduler;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Introspected
public class FirstSubclass extends AnAbstractBaseClass {
    private static final Logger LOG = LoggerFactory.getLogger(FirstSubclass.class);

    public FirstSubclass(TaskScheduler taskScheduler) {
        taskScheduler.schedule(Duration.ofSeconds(10), new DoSomething());
    }
    @Override
    public void someAbstractMethod() {

    }
}
