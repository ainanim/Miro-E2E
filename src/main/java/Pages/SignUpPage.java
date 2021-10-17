package Pages;

import Utils.WaiterHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Utils.WaiterHelper.waitAndClick;
import static Utils.WebDriverLauncher.driver;
import static Utils.WebDriverLauncher.driverWait;

public class SignUpPage {
    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Open Sign Up register form")
    public SignUpPage open() {
        driver.get("https://miro.com/signup");
        WaiterHelper.waitFor(nameField, driverWait, "Wait for Sign Up page to open");
        return this;
    }

    private final String acceptAllCookiesXpath = ".//button[@id='onetrust-accept-btn-handler']";

    @FindBy(xpath = ".//*[contains(concat(' ', @data-autotest-id, ' '), ' mr-form-signup-name-1')]")
    private WebElement nameField;

    @FindBy(xpath = ".//*[contains(concat(' ', @data-autotest-id, ' '), ' mr-form-signup-email-1')]")
    private WebElement emailField;

    @FindBy(xpath = ".//*[contains(concat(' ', @data-autotest-id, ' '), ' mr-form-signup-password-1')]")
    private WebElement passwordField;

    @FindBy(xpath = ".//*[contains(concat(' ', @data-autotest-id, ' '), ' mr-form-signup-btn-start-1')]")
    private WebElement getStartedButton;

    @FindBy(xpath = ".//*[@class='mr-checkbox-1__icon']")
    public WebElement iAgreeToTerms;

    @FindBy(xpath = acceptAllCookiesXpath)
    private WebElement acceptAllCookies;

    @FindBy(xpath = ".//*[@id='nameError']")
    public WebElement nameErrorMessage;

    @FindBy(xpath = ".//*[contains(concat(' ', @data-autotest-id, ' '), ' please-enter-your-password-1')]")
    public WebElement emptyPasswordErrorMessage;

    @FindBy(xpath = ".//*[@id='termsError' and contains(@class,'js-error')]")
    public WebElement termNotAcceptedMessage;

    @FindBy(xpath = ".//*[@id='signup-form-password' and contains(@class,'signup__input-hint-text')]")
    public WebElement passwordStrengthMessage;

    @FindBy(xpath = ".//*[@id='emailError']")
    public WebElement emailErrorMessage;

    @Step("Fill registration data")
    public final SignUpPage fillRegistrationData(String name, String password, String email) {
        waitAndClick(nameField, driverWait, "Sign Up Button, sign up page");
        nameField.sendKeys(name);
        passwordField.click();
        passwordField.sendKeys(password);
        emailField.click();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Click Accept all cookies if shown")
    public SignUpPage acceptCookiesIfShown() {
        if (!driver.findElements(By.xpath(acceptAllCookiesXpath)).isEmpty()) {
            waitAndClick(acceptAllCookies, driverWait, "Accept All Cookies button, main page");
        }
        return this;
    }

    @Step("Click on Agreement to Terms and Privacy Policy")
    public final SignUpPage agreeToTerms() {
        waitAndClick(iAgreeToTerms, driverWait, "I Agree To Terms button, sign up page");
        return this;
    }

    @Step("Click on Get Started Now button")
    public final SignUpPage clickGetStarted() {
        waitAndClick(getStartedButton, driverWait, "Get Started Now button, sign up page");
        return this;
    }
}
