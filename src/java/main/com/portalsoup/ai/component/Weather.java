package com.portalsoup.ai.component;

import com.portalsoup.ai.AbstractComponent;

import java.util.Optional;

/**
 * Created by julian on 4/19/2017.
 */
public class Weather implements AbstractComponent {
    @Override
    public Optional<String> action(String query) {
        if (query.contains("weather")) {
            if (query.contains("today")) {
                return Optional.of("It's probably raining");
            } else if (query.contains("tomorrow")) {
                return Optional.of("It's probably raining still");
            }
        }
        return Optional.empty();
    }
}
