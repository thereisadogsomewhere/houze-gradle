package commons;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker();

    public static DataHelper getData() {
        return new DataHelper();
    }

    public String getFullname() {
        return faker.name().fullName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getUsername() {
        return faker.name().username().toUpperCase();
    }

    public String getEmployeeCode() {
        locale.getDisplayCountry();
        return faker.code().isbn13().toUpperCase();

    }

    public String getPhone() {
        String num = faker.phoneNumber().subscriberNumber(8);
        if (num.startsWith("000")) {
            return num.substring(2);
        } else if (num.startsWith("00")) {
            return num.substring(1);
        } else if (num.startsWith("0")) {
            return num;
        }
        return "0" + num;
    }

    public String getIDCard() {
        return faker.phoneNumber().subscriberNumber(9);
    }

    public String getTitle() {
        return faker.name().title();
    }

    public String getUrl() {
        return faker.internet().url();
    }

    public String getNumber() {
        return String.valueOf(faker.random().nextInt(999999));
    }
}
