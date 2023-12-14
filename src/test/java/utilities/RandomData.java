package utilities;

import com.github.javafaker.Faker;

import java.util.Random;

public class RandomData {
    private final Faker faker;
    private final Random random;

    // Constructor that takes a Random instance with a fixed seed
    public RandomData(long seed) {
        this.faker = new Faker();
        this.random = new Random(seed);
    }

    public String generateRandomEmail(String domain) {
        String username = faker.name().username();
        String sanitizedUsername = username.replaceAll("\\.{2,}", ".");
        if (sanitizedUsername.startsWith(".")) {
            sanitizedUsername = sanitizedUsername.substring(1);
        }
        if (sanitizedUsername.endsWith(".")) {
            sanitizedUsername = sanitizedUsername.substring(0, sanitizedUsername.length() - 1);
        }
        return sanitizedUsername + "@" + domain;
    }
}
