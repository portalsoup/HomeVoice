package com.portalsoup.ai;

import java.util.Optional;

/**
 * Created by julian on 4/19/2017.
 */
public interface AbstractComponent {

    Optional<String> action(String query);
}
