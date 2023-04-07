package com.example;

import java.util.List;

public interface ParticipantConfiguration {

    /**
     *
     * @return The bean name qualifier
     */
    String getName();

    String getUsername();

    List<String> getWritersClassNames();
}
