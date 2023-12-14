package utilities;


import java.util.UUID;

public class RandomGenerator {

    public static String generateRandomEmailId() {
        return UUID.randomUUID().toString() + "@gmail.com";
    }

}
