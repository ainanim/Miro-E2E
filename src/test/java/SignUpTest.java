import Pages.EmailConfirmationPage;
import Pages.HomePage;
import Pages.SignUpPage;
import Utils.RandomEmailGenerator;
import Utils.WebDriverLauncher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class SignUpTest extends WebDriverLauncher {
    final String NAME = "testUser";
    final String PASSWORD = "Stongpassword1234";
    final String EMAIL = RandomEmailGenerator.generateEmail();

    final String EMAIL_NOTIFICATION = "Check your email";

    @Test
    @DisplayName("Scenario 1 : Successful signup")
    public void signUp() {
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        EmailConfirmationPage emailConfirmationPage = new EmailConfirmationPage(driver);

        homePage
                .open()
                .acceptCookiesIfShown()
                .clickSignUp();

        signUpPage
                .fillRegistrationData(NAME, PASSWORD, EMAIL)
                .agreeToTerms()
                .clickGetStarted();

        assertThat(emailConfirmationPage.confirmEmailTitle.getText(),
                containsString(EMAIL_NOTIFICATION));

        assertThat(emailConfirmationPage.confirmEmailInput.isDisplayed(),
                equalTo(true));

    }
}

