package com.acf.examples.java17.record.breed;

import java.util.List;

public record DetailsForBreedResponse(
    List<BreedRec> data
) { }
