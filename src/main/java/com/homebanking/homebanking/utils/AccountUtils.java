package com.homebanking.homebanking.utils;

public final class AccountUtils {

    static int min = 10000000;
    static int max = 99999999;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getStringRandomNumber() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    private AccountUtils() {
    }
}
