package com.acf.examples.java17.record.breed;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record MinMaxGroupRec(
        @Min(1)
        Integer min,
        @Max(250)
        Integer max
) { }
