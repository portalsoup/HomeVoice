package com.portalsoup.ai.component;

import com.portalsoup.ai.AbstractComponent;
import com.portalsoup.ai.AbstractComponent.Command;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by julian on 4/19/2017.
 */
@Command("weather")
public class Weather implements AbstractComponent {

    @Command("today")
    protected Supplier<Optional<String>> today = Optional::empty;

    @Command("tomorrow")
    protected Supplier<Optional<String>> tomorrow = Optional::empty;
}
