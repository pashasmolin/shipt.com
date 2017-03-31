package suits;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.IndexPage;
import pages.LoginPage;
import utils.Browsers;
import utils.Resources;
import utils.Screenshots;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * A class contains one end-to-end test: Log In, Search an item, Add the item to cart, Verify the item and its price exist in the cart and matches selected item"
 * BeforeClass instantiates Chrome driver, makes sure it's getting right base url;
 * Test includes all the steps and assertions in the end. It makes sure the cart is empty before adding any new item to it.
 * AfterMethod takes a screenshot if testcase failed
 * AfterClass clears the cart again and closes the driver
 */

public class EndToEndTests {

    private String baseURL = Resources.getValue("BASE_PAGE");
    WebDriver driver = null;
    IndexPage indexPage;
    LoginPage loginPage;
    HomePage homePage;
    private CartPage cartPage;

    @BeforeClass
    public void beforeClass() {
        driver = Browsers.getBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(baseURL);
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(baseURL)) driver.get(baseURL);
    }

    @Test(priority = 1, description = "End-to-end test001: Log In, Search an item, Add the item to cart, Verify the item and its price exist in the cart and matches selected item")
    public void test001_E2E_LogInSearchItemAddItemToCart() throws Exception {
        indexPage = new IndexPage(driver);
        indexPage.clickLogIn();
        loginPage = new LoginPage(driver);
        loginPage.logInWithValidCredentials();
        homePage = new HomePage(driver);
        System.out.println(driver.getTitle());
        if (homePage.getPriceInCart() != 0.0) {
            homePage.clearShoppingCart(driver);
        }
        homePage.searchMilk();
        homePage.selectHorizonOrganicMilk();
        String itemName = homePage.getPopupItemName();
        Double itemPrice = homePage.getPopupItemPrice();
        System.out.println(itemName);
        System.out.println(itemPrice);
        homePage.clickAddToCart();
        homePage.clickXButton();
        homePage.clickCartButton();
        cartPage = new CartPage(driver);
        Double subtotalPrice = cartPage.getSubtotalPrice();

        assertTrue("Horizon Organic Milk was not found in the cart", cartPage.verifyElementExists(cartPage.horizonOrganicMilk));
        assertEquals(subtotalPrice, itemPrice, "Subtotal price and item price did not match");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE){
            Screenshots.takeScreenshot(driver, testResult.getName());
        }
    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        cartPage.clearCart();
        driver.close();
    }
}
