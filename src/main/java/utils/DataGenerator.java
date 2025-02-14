package utils;
import com.github.javafaker.Faker;

public class DataGenerator {
    private static final Faker faker = new Faker();

    // koristim faker klasu za generisanje random podataka za koriscenje u testovima

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password(7, 12);
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }
}


