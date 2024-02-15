package com.acf.examples.java17.record.user;

/**
 * This is the full user record to be returned when calling the api via rest
 */
public record User(
        String gender,
        Name name,
        Location location,
        String email,
        String phone,
        String cell) { }
