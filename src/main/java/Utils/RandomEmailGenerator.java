package Utils;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomEmailGenerator {
    @Step
    static public String generateEmail() {
        String email = "testUser" + RandomStringUtils.randomAlphabetic(5) + "@invalidemail.com";
        return email;
    }
}

