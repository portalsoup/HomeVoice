package com.portalsoup.ai.entity;

import com.portalsoup.ai.AbstractComponent;
import com.portalsoup.ai.AbstractEntity;
import com.portalsoup.ai.component.Schedule;
import com.portalsoup.ai.component.Time;
import com.portalsoup.ai.component.Weather;

import java.util.Optional;

public class Hal extends AbstractEntity {

    private boolean quiet;

    public Hal() {
        super("hal");
        quiet = false;
    }

    public static Hal init() {
        Hal hal = new Hal();
        hal.addBehavior(new Time());
        hal.addBehavior(new Weather());
        hal.addBehavior(new Schedule());

        return hal;
    }

    @Override
    public String process(String query) {
        String response = "";
        if (query.equals("quiet mode")) {
            quiet = !quiet;
            return "quiet mode " + (quiet ? "enabled" : "disabled");
        }

        if (quiet) {
            return "";
        }

        for (AbstractComponent aBehavior : getBehaviors()) {
            Optional<String> aResponse = aBehavior.action(query);
            if (aResponse.isPresent() && !aResponse.get().isEmpty()) {
                return aResponse.get();
            }
        }

        return response;
    }
}
