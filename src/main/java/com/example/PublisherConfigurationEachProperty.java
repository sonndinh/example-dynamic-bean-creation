package com.example;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@EachProperty("participants.publishers")
public class PublisherConfigurationEachProperty implements PublisherConfiguration {
    private final String name;
    private List<String> writersClassNames;
    private static final Logger LOG = LoggerFactory.getLogger(PublisherConfigurationEachProperty.class);

    public PublisherConfigurationEachProperty(@Parameter String name) {
        this.name = name;
        LOG.info("Read configuration for publisher: " + name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getWritersClassNames() {
        return writersClassNames;
    }

    public void setWritersClassNames(List<String> writersClassNames) {
        this.writersClassNames = writersClassNames;
    }
}
