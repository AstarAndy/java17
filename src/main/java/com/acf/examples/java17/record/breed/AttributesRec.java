package com.acf.examples.java17.record.breed;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AttributesRec(

        @NotNull
        String name,
        String description,
        MinMaxGroupRec life,
        @JsonProperty("male_weight")
        MinMaxGroupRec maleWeight,
        @JsonProperty("female_weight")
        MinMaxGroupRec femaleWeight,
        Boolean hypoallergenic) {
}
