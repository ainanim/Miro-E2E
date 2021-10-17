import Pages.SignUpPage;
import Utils.RandomEmailGenerator;
import Utils.WebDriverLauncher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Utils.WaiterHelper.waitFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class PasswordStrengthValidationTest extends WebDriverLauncher {
    final String NAME = "testUser";
    final String EMAIL = RandomEmailGenerator.generateEmail();

    final String TOO_SHORT_PASSWORD = "short";
    final String WEAK_PASSWORD = "weakPassword";
    final String SO_SO_PASSWORD = "so-soPassword";
    final String GOOD_PASSWORD = "str0ng_PasSword";
    final String GREAT_PASSWORD = "Gr_!eatPas1wOrd";

    final String WEAK_PASSWORD_MESSAGE = "Weak password";
    final String SO_SO_PASSWORD_MESSAGE = "So-so password";
    final String GOOD_PASSWORD_MESSAGE = "Good password";
    final String GREAT_PASSWORD_MESSAGE = "Great password";

    final String TOO_SHORT_PASSWORD_MESSAGE = "Please use 8+ characters for secure password";

    @Test
    @DisplayName("Scenario 7: Too short password")
    public void tooShortPasswordTest() {
        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage
                .open()
                .acceptCookiesIfShown()
                .fillRegistrationData(NAME, TOO_SHORT_PASSWORD, EMAIL)
                .agreeToTerms()
                .clickGetStarted();

        waitFor(signUpPage.passwordStrengthMessage, driverWait, "Waiting for the message");
        assertThat(signUpPage.passwordStrengthMessage.getText(),
                containsString(TOO_SHORT_PASSWORD_MESSAGE));
    }

    @Test
    @DisplayName("Scenario 8: Password strength message check")
    public void passwordStrenghtTest() {
        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage
                .open()
                .acceptCookiesIfShown()
                .fillRegistrationData(NAME, WEAK_PASSWORD, EMAIL);

        waitFor(signUpPage.passwordStrengthMessage, driverWait, "Waiting for the message");
        assertThat(signUpPage.passwordStrengthMessage.getText(),
                containsString(WEAK_PASSWORD_MESSAGE));

        signUpPage
                .open()
                .fillRegistrationData(NAME, SO_SO_PASSWORD, EMAIL);

        waitFor(signUpPage.passwordStrengthMessage, driverWait, "Waiting for the message");
        assertThat(signUpPage.passwordStrengthMessage.getText(),
                containsString(SO_SO_PASSWORD_MESSAGE));

        signUpPage
                .open()
                .fillRegistrationData(NAME, GOOD_PASSWORD, EMAIL);

        waitFor(signUpPage.passwordStrengthMessage, driverWait, "Waiting for the message");
        assertThat(signUpPage.passwordStrengthMessage.getText(),
                containsString(GOOD_PASSWORD_MESSAGE));

        signUpPage
                .open()
                .fillRegistrationData(NAME, GREAT_PASSWORD, EMAIL);

        waitFor(signUpPage.passwordStrengthMessage, driverWait, "Waiting for the message");
        assertThat(signUpPage.passwordStrengthMessage.getText(),
                containsString(GREAT_PASSWORD_MESSAGE));
    }
}

