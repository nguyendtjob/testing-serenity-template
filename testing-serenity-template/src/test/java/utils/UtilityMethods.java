package utils;

import java.time.Instant;

public class UtilityMethods {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * ((max+1) - min)) + min);
    }

    public static String generateEpochString(){
        long now = Instant.now().toEpochMilli();
        return String.valueOf(now);
    }
}
