package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Resources;

/**
 * A page class describes the page: It includes all related elements and action methods
 */

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver, Resources.getValue("CART_PAGE_TITLE"));
        PageFactory.initElements(driver, this);
    }

    public static final By horizonOrganicMilk = By.xpath("//*[contains(text(), 'Horizon Organic Whole Milk')]");

    @FindBy(xpath = "//a[@class='empty-cart']")
    WebElement emptyCart;

    @FindBy(xpath = "//button[@class='button ng-binding button-positive']")
    WebElement clearCartYES;

    @FindBy(xpath = "//div[@class='col text-right ng-binding']")
    WebElement subtotal;

    public void clearCart() {
        emptyCart.click();
        waitTheElementToBeVisible(clearCartYES);
        clearCartYES.click();
    }

    public Double getSubtotalPrice() {
        waitTheElementToBeVisible(subtotal);
        String str = subtotal.getText().substring(1, 5).trim();
        Double price = Double.parseDouble(str);
        return price;
    }

}
