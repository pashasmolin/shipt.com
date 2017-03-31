package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Resources;

/**
 * A page class describes the page: It includes all related elements and action methods
 */

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;

    @FindBy(css = "#start_shopping_login_button")
    public WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver, Resources.getValue("LOGIN_PAGE_TITLE"));
        PageFactory.initElements(driver, this);
    }

    public void logInWithValidCredentials() {
        waitTheElementToBeVisible(emailField);
        emailField.sendKeys(Resources.getValue("VALID_EMAIL"));
        passwordField.sendKeys(Resources.getValue("VALID_PASSWORD"));
        logInButton.click();
    }
}
