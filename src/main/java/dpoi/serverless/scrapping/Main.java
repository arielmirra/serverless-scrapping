package dpoi.serverless.scrapping;

import java.util.Random;

public class Main {

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int handle() {
        return getRandomNumberInRange(0, 100);
    }

    public static void main(String[] args) {
    }
}

