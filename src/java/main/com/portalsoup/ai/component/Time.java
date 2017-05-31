package com.portalsoup.ai.component;


import com.portalsoup.ai.AbstractComponent;
import com.portalsoup.ai.AbstractComponent.Command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by julian on 4/19/2017.
 */
@Command("time")
public class Time implements AbstractComponent {

    @Default
    protected Supplier<Optional<String>> time = () -> {
        Date date = new Date();
        String time = new SimpleDateFormat("HH:mm aa").format(date);
        return Optional.of("The current time is " + time);
    };
}
