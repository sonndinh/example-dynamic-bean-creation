package com.example;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class ParticipantTest {

    @Inject
    BeanContext beanContext;

    @Test
    void participantBeans() {
        Collection<Publisher> publishers = beanContext.getBeansOfType(Publisher.class);
        assertEquals(2, publishers.size());
        Collection<SecondSubclass> secondSubclasses = beanContext.getBeansOfType(SecondSubclass.class);
        assertEquals(1, secondSubclasses.size());
        Collection<FirstSubclass> firstSubclasses = beanContext.getBeansOfType(FirstSubclass.class);
        assertEquals(1, firstSubclasses.size());
    }
}
