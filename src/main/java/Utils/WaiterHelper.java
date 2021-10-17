package Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaiterHelper {
    @Step("Waiting for the button to be clickable and click")
    static public void waitAndClick(WebElement button, WebDriverWait wait, String name) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
        } catch (Exception e) {
            System.out.println("Couldn't find button" + name);
            throw e;
        }
    }

    @Step("Waiting for the element to be dislpayed")
    static public void waitFor(WebElement element, WebDriverWait wait, String name) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Couldn't find element" + name);
            throw e;
        }
    }
}

