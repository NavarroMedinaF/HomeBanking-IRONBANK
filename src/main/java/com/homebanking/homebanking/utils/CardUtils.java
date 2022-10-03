package com.homebanking.homebanking.utils;

public final class CardUtils {

    static int min = 1000;
    static int max = 9999;
    static int maxcvv= 999;

    static int mincvv= 100;
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getStringRandomNumber() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    public static int  getStringRandomcvv() {
        return getRandomNumber(mincvv, maxcvv);
    }
    private CardUtils() {
    }
}
