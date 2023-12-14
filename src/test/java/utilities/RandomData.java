package utilities;

import com.github.javafaker.Faker;

/**
 * A utility class for generating random email addresses using the Faker library.
 */
public class RandomData {
    private final Faker faker = new Faker();

    public String generateRandomEmail(String domain) {
        // create a more robust email generator that includes verifications
        String username = faker.name().username();
        // Replace consecutive dots or any other unwanted characters that appear due to random generation logic.
        String sanitizedUsername = username.replaceAll("\\.{2,}", ".");
        // Ensure the local part of the email does not begin or end with a dot
        if (sanitizedUsername.startsWith(".")) {
            sanitizedUsername = sanitizedUsername.substring(1);
        }
        if (sanitizedUsername.endsWith(".")) {
            sanitizedUsername = sanitizedUsername.substring(0, sanitizedUsername.length() - 1);
        }
        return sanitizedUsername + "@" + domain;
    }
}
