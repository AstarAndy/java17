package com.acf.examples.java17.record.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Name(
        String title,
        @JsonProperty(value = "first")
        String firstName,
        @JsonProperty(value = "last")
        String lastName) { }
