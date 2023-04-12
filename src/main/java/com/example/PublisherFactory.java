package com.example;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.reflect.ClassUtils;
import io.micronaut.core.type.Argument;
import io.micronaut.logging.LogLevel;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.TaskScheduler;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Factory
public class PublisherFactory {
    private final BeanContext beanContext;
    private final TaskScheduler taskScheduler;
    private static final Logger LOG = LoggerFactory.getLogger(PublisherFactory.class);

    PublisherFactory(BeanContext beanContext, @Named(TaskExecutors.SCHEDULED) TaskScheduler taskScheduler) {
        this.beanContext = beanContext;
        this.taskScheduler = taskScheduler;
    }

    @EachBean(PublisherConfiguration.class)
    Publisher createPublisher(PublisherConfiguration publisherConfiguration) {
        LOG.info("Creating a publisher bean....");
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<AbstractWriter> writers = new ArrayList<>();
        for (String className : publisherConfiguration.getWritersClassNames()) {
            Optional<Class> clazzOptional = ClassUtils.forName(className, classLoader);
            if (clazzOptional.isPresent()) {
                Class clazz = clazzOptional.get();
                if (AbstractWriter.class.isAssignableFrom(clazz)) {
                    Optional beanOptional = beanContext.findBean(clazz);
                    if (beanOptional.isPresent()) {
                        AbstractWriter bean = (AbstractWriter) beanOptional.get();
                        writers.add(bean);
                    } else {
                        BeanIntrospection introspection = BeanIntrospection.getIntrospection(clazz);
                        Object[] args = new Object[introspection.getConstructorArguments().length];
                        int count = 0;
                        for (Argument arg : introspection.getConstructorArguments()) {
                            if (arg.isAssignableFrom(TaskScheduler.class)) {
                                args[count++] = taskScheduler;
                            } else {
                                args[count++] = null;
                            }
                        }
                        AbstractWriter writer = (AbstractWriter) introspection.instantiate(args);
                        beanContext.registerSingleton(clazz, writer);
                        writers.add(writer);
                    }
                }
            }
        }
        return new Publisher(writers);
    }
}
