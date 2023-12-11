package utilities;

import java.util.UUID;

public class RandomEmailGenerator {


    public static String generateRandomEmailId() {
        return UUID.randomUUID().toString() + "@gmail.com";
    }

}
