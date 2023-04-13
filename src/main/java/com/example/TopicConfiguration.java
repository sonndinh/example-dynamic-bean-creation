package com.example;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

@EachProperty("community-connector.topics")
public class TopicConfiguration {
    private String name;
    private String type;
    private String dpdEntity;

    public TopicConfiguration(@Parameter String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDpdEntity() {
        return dpdEntity;
    }

    public void setDpdEntity(String dpdEntity) {
        this.dpdEntity = dpdEntity;
    }
}
