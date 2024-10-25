package com.carsearch.Utils;

import java.util.Random;

public class RandomMiles {

    public static int GenerateMiles(){
        Random random = new Random();
        int low = 10000;
        int high = 50000;
        return random.nextInt(high-low) +low;
    }
}
