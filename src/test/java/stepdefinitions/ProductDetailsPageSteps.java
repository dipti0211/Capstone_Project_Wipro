package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchFunctionality;
import utils.DriverFactory;
import utils.ScreenshotUtil;

public class ProductDetailsPageSteps {

	private WebDriver driver;
	private WebDriverWait wait;

	private HomePage homePage;
	private LoginPage loginPage;
	private SearchFunctionality searchFunctionality;
	private ProductDetailsPage productDetailsPage;

	@Before("@ProductDetailsPageTest")
	public void setUp() throws InterruptedException {
	    System.out.println("Setup method is running..."); // Debugging line

	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    homePage = new HomePage(driver);
	    loginPage = new LoginPage(driver); // ✅ Ensure loginPage is initialized
	    searchFunctionality = new SearchFunctionality(driver, wait);
	    productDetailsPage = new ProductDetailsPage(driver, wait);
	}


	@Given("User Entered the homepage")
	public void userEnteredTheHomepage() {
		driver.get("https://www.bookswagon.com/");
		ScreenshotUtil.captureScreenshot(driver, "Homepage_Loaded");
	}

	@When("User logs in with email {string} and password {string}")
	public void userLogsInWithEmailAndPassword(String email, String password) throws InterruptedException {
		WebElement myAccButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_lblUser")));
		myAccButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_SignIn_txtEmail")));

		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		Thread.sleep(5000);
		ScreenshotUtil.captureScreenshot(driver, "After_Login");

		Assert.assertTrue(loginPage.isUserLoggedIn(), "User login failed.");
		ScreenshotUtil.captureScreenshot(driver, "After_Login");
	}


	@When("User selects the product {string}")
	public void user_selects_the_product(String productName) {
		// Example: If your HomePage has a method searchForBook()
		homePage.searchForBook(productName);
		ScreenshotUtil.captureScreenshot(driver, "Searched_Book_" + productName);
	}

	@When("User applies necessary filters")
	public void userAppliesNecessaryFilters() {
		// Example: If your SearchFunctionality class has methods for filtering
		searchFunctionality.filterByDiscount();
		searchFunctionality.filterByPrice();
		searchFunctionality.filterByLanguage();
		ScreenshotUtil.captureScreenshot(driver, "Filters_Applied");
	}

	@When("User validates the product details including title, description, price, images, and availability")
	public void userValidatesTheProductDetails() {
		String expectedTitle = "Harry Potter and the Cursed Child, Parts One and Two: The Official Playscript of the Original West End Production";
		String expectedDescription = "The Eighth Story. Nineteen Years Later...";
		double minPrice = 500;
		double maxPrice = 1000;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			System.out.println("Starting product details validation...");


			productDetailsPage.validateProductDetails(expectedTitle, expectedDescription, minPrice, maxPrice);
			System.out.println("Product details validated successfully.");

			ScreenshotUtil.captureScreenshot(driver, "Product_Details_Validated");

		} catch (Exception e) {
			ScreenshotUtil.captureScreenshot(driver, "Product_Details_Validation_Failed");

			System.err.println("Product details validation failed: " + e.getMessage());
		}
	}


	@When("User adds the product to the wishlist")
	public void userAddsTheProductToWishlist() {
		productDetailsPage.addToWishlist();
		ScreenshotUtil.captureScreenshot(driver, "Product_Added_To_Wishlist");
	}

	@When("User adds the product to the cart")
	public void userAddsTheProductToCart() {
		productDetailsPage.addToCart();
		ScreenshotUtil.captureScreenshot(driver, "Product_Added_To_Cart");
	}

	@Then("The product is displayed in the cart")
	public void theProductIsDisplayedInTheCart() {
		boolean isProductInCart = productDetailsPage.isProductInCart();
		Assert.assertTrue(isProductInCart, "Product not found in the cart!");
		ScreenshotUtil.captureScreenshot(driver, "Product_Displayed_In_Cart");
	}

	@After("@ProductDetailsPageTest")
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
