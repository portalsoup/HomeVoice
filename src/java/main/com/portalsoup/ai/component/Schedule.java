package com.portalsoup.ai.component;

import com.portalsoup.ai.AbstractComponent;

import java.util.Optional;

/**
 * Created by julian on 4/19/2017.
 */
public class Schedule implements AbstractComponent {
    @Override
    public Optional<String> action(String query) {
        // Need to use the calendar api library for java
        // https://developers.google.com/api-client-library/java/apis/calendar/v3
        // Gotta figure that out first though
        if (query.contains("schedule")) {
            if (query.contains("today")) {
                return Optional.empty();
            } else if (query.contains("tomorrow")) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
