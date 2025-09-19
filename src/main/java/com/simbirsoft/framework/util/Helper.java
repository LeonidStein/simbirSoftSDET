package com.simbirsoft.framework.util;

import java.util.concurrent.ThreadLocalRandom;

public final class Helper {

    /**
     * Возвращает строку, состоящую из указанного количества символов.
     *
     * @param symbol символ, который будет повторяться
     * @param length количество символов в возвращаемой строке
     * @return строка из length символов symbol
     */
    public static String repeatChar(char symbol, int length) {
        return length > 0 ? String.valueOf(symbol).repeat(length) : "";
    }

    /**
     * Возвращает случайное целое число от 1 до 10
     *
     * @return случайное число > 0
     */
    public static int randomFrom1To10() {
        final int minValue = 1;
        final int bound = 11;

        return ThreadLocalRandom.current().nextInt(minValue, bound);
    }
}
