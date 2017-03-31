package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Resources;

/**
 * A page class describes the page: It includes all related elements and action methods
 */

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver, Resources.getValue("HOME_PAGE_TITLE"));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='search']")
    WebElement searchField;

    @FindBy(xpath = "//*[contains(text(), 'Horizon Organic Whole Milk')]")
    WebElement HorizonOrganicMilk;

    @FindBy(xpath = "//h2[@class='product-name dark ng-binding']")
    WebElement popupItemName;

    @FindBy(xpath = "//span[@class='price ng-binding']")
    WebElement popupItemPrice;

    @FindBy(xpath = "//div[@class='padding']//*[contains(text(), 'Add To Cart')]")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[@ng-click='cartClick()']")
    WebElement cartButton;

    @FindBy(xpath = "//button[@class='button button-clear']")
    WebElement xButton;


    public void searchMilk() {
        waitTheElementToBeVisible(searchField);
        searchField.sendKeys("milk");
        searchField.submit();
    }

    public void selectHorizonOrganicMilk() {
        waitTheElementToBeVisible(HorizonOrganicMilk);
        HorizonOrganicMilk.click();
    }

    public void clickAddToCart() throws Exception {
        Thread.sleep(1800);
        addToCartButton.click();
    }

    public void clickXButton() throws InterruptedException {
        Thread.sleep(1000);
        xButton.click();
    }

    public String getPopupItemName() {
        return popupItemName.getText();
    }

    public Double getPopupItemPrice() {
        waitTheElementToBeVisible(popupItemName);
        String str = popupItemPrice.getText().substring(1, 5).trim();
        Double price = Double.parseDouble(str);
        return price;
    }

    public Double getPriceInCart() throws InterruptedException {
        Thread.sleep(2500);
        String str = cartButton.getText().substring(1, 5).trim();
        Double price = Double.parseDouble(str);
        return price;
    }

    public void clickCartButton() throws InterruptedException {
        Thread.sleep(1800);
        cartButton.click();
    }

    public void clearShoppingCart(WebDriver driver) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickCartButton();
        CartPage cartPage = new CartPage(driver);
        cartPage.clearCart();
        driver.navigate().back();
    }
}



