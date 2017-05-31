package com.portalsoup.ai;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by julian on 4/19/2017.
 */
public interface AbstractComponent {

    default Optional<String> action(String query) throws InvocationTargetException, IllegalAccessException {

        //Utility
        Function<Object, String> sanitizeReturn = (maybeRaw) -> {
            if (maybeRaw instanceof Optional) {
                Object raw = ((Optional<?>) maybeRaw).get();
                if (raw instanceof String) {
                    return (String) raw;
                }
            }
            return null;
        };

        // Get the keyword for this action
        Class<?> thiz = this.getClass();
        String keyword = "";
        try {
             keyword = thiz.getDeclaredAnnotation(Command.class).value();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (query.contains(keyword)) {

            for (Method aMethod : thiz.getDeclaredMethods()) {
                if (aMethod.getDeclaredAnnotation(Command.class) != null) {
                    Object maybeRaw = aMethod.invoke(this);
                    return Optional.ofNullable(sanitizeReturn.apply(maybeRaw));
                }
            }

            for (Method aMethod : thiz.getDeclaredMethods()) {
                if (aMethod.getDeclaredAnnotation(Default.class) != null) {
                    Object maybeRaw = aMethod.invoke(this);
                        return Optional.ofNullable(sanitizeReturn.apply(maybeRaw));
                }
            }
        }
        return Optional.empty();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Command {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Default { }
}
