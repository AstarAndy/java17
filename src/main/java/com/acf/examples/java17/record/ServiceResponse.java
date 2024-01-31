package com.acf.examples.java17.record;

import jakarta.validation.constraints.NotNull;

public record ServiceResponse<T>(
        @NotNull
        T data
    ) {

}
