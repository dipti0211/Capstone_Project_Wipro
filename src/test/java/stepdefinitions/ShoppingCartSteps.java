package stepdefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchFunctionality;
import pages.ShoppingCart;
import utils.DriverFactory;
import utils.ScreenshotUtil;

public class ShoppingCartSteps {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SearchFunctionality searchFunctionality;
    ProductDetailsPage productDetailsPage;
    ShoppingCart shoppingCart;
    LoginPage loginPage;

    @Before("@ShoppingCartTest")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        searchFunctionality = new SearchFunctionality(driver, wait);
        productDetailsPage = new ProductDetailsPage(driver, wait);
        shoppingCart = new ShoppingCart(driver, wait);
        loginPage = new LoginPage(driver); // ✅ Initialize LoginPage
    }

    @Given("User Entered homepage")
    public void userEnteredHomepage() {
        driver.get("https://www.bookswagon.com");
        ScreenshotUtil.captureScreenshot(driver, "Homepage_Loaded");
    }

    @When("User logged in to the account with {string} and password {string}")
    public void userLoggedInToTheAccount(String email, String password) throws InterruptedException {
        WebElement myAccButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_lblUser")));
        myAccButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_SignIn_txtEmail")));

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        Thread.sleep(5000);
        ScreenshotUtil.captureScreenshot(driver, "After_Login");

        Assert.assertTrue(loginPage.isUserLoggedIn(), "User login failed.");
    }

    @When("User searches {string} and adds products to the cart")
    public void user_searches_and_adds_products_to_the_cart(String bookName) throws InterruptedException {
        homePage.searchForBook(bookName); // Use HomePage method to search
        shoppingCart.addItemToCart();     // Use ShoppingCart method to add the item
        Thread.sleep(5000);               // Wait for the UI to update
        ScreenshotUtil.captureScreenshot(driver, "Product_Added_To_Cart");
    }


    @When("User removes the product and updates the quantities")
    public void user_removes_the_product_and_updates_the_quantities() throws InterruptedException {
        shoppingCart.openCart();
        shoppingCart.removeProduct();
        shoppingCart.increaseQuantity();
        Thread.sleep(5000);
    }

    @When("User proceeds to the checkout")
    public void user_proceeds_to_the_checkout() throws InterruptedException {
        shoppingCart.proceedToCheckout();
        Thread.sleep(5000);
    }

    @Then("Checkout page is displayed")
    public void checkout_page_is_displayed() throws InterruptedException {
    	String actualTitle = shoppingCart.verifyCheckoutPage();
		String expectedTitle = "Checkout Your Cart";
		Assert.assertEquals(actualTitle, expectedTitle, "Checkout page title mismatch!");
		Thread.sleep(5000);
    }

    @After("@ShoppingCartTest")
    public void tearDown() {
    	DriverFactory.quitDriver();
    }
}
