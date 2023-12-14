package utilities;

import com.github.javafaker.Faker;

/**
 * A utility class for generating random email addresses using the Faker library.
 */
public class RandomData {
    private Faker faker = new Faker();

    public String generateRandomEmail(String domain) {
        return faker.name().username() + "@" + domain;
    }
}