package com.example;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class Publisher {
    private final List<? extends AbstractWriter> writers;

    public Publisher(List<? extends AbstractWriter> writers) {
        this.writers = writers;
    }

    List<? extends AbstractWriter> getWriters() {
        return writers;
    }
}
