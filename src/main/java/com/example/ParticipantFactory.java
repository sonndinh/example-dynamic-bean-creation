package com.example;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.reflect.ClassUtils;
import io.micronaut.core.type.Argument;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.TaskScheduler;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Factory
public class ParticipantFactory {

    private final BeanContext beanContext;
    private final TaskScheduler taskScheduler;
    ParticipantFactory(BeanContext beanContext,
                       @Named(TaskExecutors.SCHEDULED) TaskScheduler taskScheduler) {
        this.beanContext = beanContext;
        this.taskScheduler = taskScheduler;
    }

    @EachBean(ParticipantConfiguration.class)
    Participant createParticipant(ParticipantConfiguration participantConfiguration) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<AnAbstractBaseClass> writers = new ArrayList<>();
        for (String className : participantConfiguration.getWritersClassNames()) {
            Optional<Class> clazzOptional = ClassUtils.forName(className, classLoader);
            if (clazzOptional.isPresent()) {
                Class clazz = clazzOptional.get();
                if (AnAbstractBaseClass.class.isAssignableFrom(clazz)) {
                    Optional beanOptional = beanContext.findBean(clazz);
                    if (beanOptional.isPresent()) {
                        AnAbstractBaseClass bean = (AnAbstractBaseClass) beanOptional.get();
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
                        AnAbstractBaseClass writer = (AnAbstractBaseClass) introspection.instantiate(args);
                        beanContext.registerSingleton(clazz, writer);
                        writers.add(writer);
                    }
                }
            }
;        }
        return new Participant(participantConfiguration.getUsername(), writers);
    }
}
