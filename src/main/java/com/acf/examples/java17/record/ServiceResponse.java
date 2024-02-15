package com.acf.examples.java17.record;

import jakarta.validation.constraints.NotNull;

/**
 * This will be the outermost element in a response from the
 * randomuser.me/api
 * @param results This will be either a list of something or a single entry
 * @param <T> Application defined type based on the response from this rest service
 */
public record ServiceResponse<T>(
        @NotNull
        T results
    ) {

}
