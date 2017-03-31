package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Resources;

/**
 * A page class describes the page: It includes all related elements and action methods
 */

public class IndexPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Log In')]")
    WebElement logInButton;

    public IndexPage(WebDriver driver) {
        super(driver, Resources.getValue("INDEX_PAGE_TITLE"));
        PageFactory.initElements(driver, this);
    }

    public void clickLogIn() {
        waitTheElementToBeVisible(logInButton);
        logInButton.click();
    }
}
