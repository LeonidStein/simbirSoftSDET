package com.simbirsoft.framework.util;

import java.util.concurrent.ThreadLocalRandom;

public final class Helper {

    public static String repeatChar(char symbol, int length) {
        return length > 0 ? String.valueOf(symbol).repeat(length) : "";
    }

    public static int randomFrom1To10() {
        final int minValue = 1;
        final int bound = 11;

        return ThreadLocalRandom.current().nextInt(minValue, bound);
    }
}
