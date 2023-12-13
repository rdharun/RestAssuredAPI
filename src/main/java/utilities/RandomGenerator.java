package utilities;

import com.github.javafaker.Faker;

public class RandomGenerator {
    private Faker faker = new Faker();


    /**
     * Generates a completely random email address.
     *
     * @return A randomly generated email address.
     */
    public String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generates a random email address with a random username and the specified domain.
     *
     * @param domain domain The domain for the email address.
     * @return A randomly generated email address.
     */
    public String generateRandomEmail(String domain) {
        return faker.name().username() + "@" + domain;
    }
}
