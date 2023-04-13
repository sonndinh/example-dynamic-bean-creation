package com.example;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(notEnv = Environment.TEST)
@Singleton
public class Boostrap implements ApplicationEventListener<ServerStartupEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(Boostrap.class);
    private final BeanContext beanContext;

    public Boostrap(BeanContext beanContext) {
        this.beanContext = beanContext;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        LOG.info("Loading publisher and topic beans upon application startup");
        beanContext.getBeansOfType(Publisher.class);
        int noTopicBeans = beanContext.getBeansOfType(Topic.class).size();
        LOG.info("Number of Topic beans: " + noTopicBeans);
    }
}
