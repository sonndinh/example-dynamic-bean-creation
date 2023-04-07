package com.example;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

import java.util.List;

@EachProperty("participants")
public class ParticipantConfigurationEachProperty implements ParticipantConfiguration {
    private final String name;
    private String username;
    private List<String> writersClassNames;

    public ParticipantConfigurationEachProperty(@Parameter String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public List<String> getWritersClassNames() {
        return writersClassNames;
    }

    public void setWritersClassNames(List<String> writersClassNames) {
        this.writersClassNames = writersClassNames;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
