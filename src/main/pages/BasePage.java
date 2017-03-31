package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Page classes extend BasePage. When certain page is loading - the driver waits for specific page title.
 */

public class BasePage {

    WebDriver driver = null;

    public BasePage(WebDriver d, String title) {
        this.driver = d;

        Wait wait = new FluentWait(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public boolean verifyElementExists(By path) throws Exception {
        boolean elementExistence = false;
        if (driver.findElements(path).size() != 0) elementExistence = true;
        return elementExistence;
    }

    public void waitTheElementToBeVisible(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(el));
    }
}

