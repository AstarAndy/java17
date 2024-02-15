package com.acf.examples.java17.record.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
    Street street,
    String city,
    String state,
    String country,
    String postcode,
    @JsonProperty(value = "timezone")
    Timezone tz) { }
