package com.acf.examples.java17.record.breed;

import java.util.UUID;
public record BreedRec(
        UUID id,
        String type,
        AttributesRec attributes
) {
}
