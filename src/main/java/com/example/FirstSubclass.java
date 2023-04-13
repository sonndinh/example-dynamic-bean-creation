package com.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.scheduling.TaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Introspected
public class FirstSubclass extends AbstractWriter {
    private static final Logger LOG = LoggerFactory.getLogger(FirstSubclass.class);

    public FirstSubclass(TaskScheduler taskScheduler) {
        taskScheduler.schedule(Duration.ofSeconds(5), new DoSomething());
    }
    @Override
    public void someAbstractMethod() {

    }
}
