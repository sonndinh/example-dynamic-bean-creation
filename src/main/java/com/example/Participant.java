package com.example;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class Participant {
    private final String username;
    private final List<? extends AnAbstractBaseClass> writers;

    public Participant(String username, List<? extends AnAbstractBaseClass> writers) {
        this.username = username;
        this.writers = writers;
    }

    public String getUsername() {
        return username;
    }

    public List<? extends AnAbstractBaseClass> getWriters() {
        return writers;
    }
}
