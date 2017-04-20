package com.portalsoup.ai.component;


import com.portalsoup.ai.AbstractComponent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Created by julian on 4/19/2017.
 */
public class Time implements AbstractComponent {


    @Override
    public Optional<String> action(String query) {
        if (query.contains("time")) {
            Date date = new Date();
            String time = new SimpleDateFormat("HH:mm aa").format(date);
            return Optional.of("The current time is " + time);
        }
        return Optional.empty();
    }
}
