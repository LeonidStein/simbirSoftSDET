package com.simbirsoft.data;

import com.github.javafaker.Faker;

public final class FakeUserData {

    public static String getFirstName() {
        return new Faker().name().firstName();
    }

    public static String getPassword() {
        return new Faker().internet().password();
    }

    public static String getEmail() {
        final String domainPart = "@example.com";

        return new Faker().name().firstName() + domainPart;
    }
}
