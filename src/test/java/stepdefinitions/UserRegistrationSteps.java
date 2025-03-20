package stepdefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchFunctionality;
import pages.ShoppingCart;
import pages.UserRegistration;
import org.testng.Assert;

public class UserRegistrationSteps {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    SearchFunctionality searchFunctionality;
    ProductDetailsPage productDetailsPage;
    ShoppingCart shoppingCart;
    UserRegistration userRegistration;

    @Before("@UserRegistrationTest")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        searchFunctionality = new SearchFunctionality(driver, wait);
        productDetailsPage = new ProductDetailsPage(driver, wait);
        shoppingCart = new ShoppingCart(driver, wait);
        userRegistration = new UserRegistration(driver, wait);
    }

    @Given("User on homepage")
    public void user_on_homepage() {
        driver.get("https://www.bookswagon.com/");
    }

    @When("User registers an account")
    public void user_registers_an_account() {
        userRegistration.clickMyAccount();
        userRegistration.clickSignup();
    }

    @When("User logs in with mobile number {string} and password {string}")
    public void user_logs_in_with_mobile_number_and_password(String email, String password) throws InterruptedException {
        userRegistration.clickLogin();
        userRegistration.login(email, password);
        Thread.sleep(5000);
    }

    @When("User changes the password to {string}")
    public void user_changes_the_password_to(String newPassword) {
        userRegistration.changePassword("Dipti@123", newPassword);
    }

    @Then("displays password changed successfully")
    public void displays_password_changed_successfully() {
        Assert.assertTrue(userRegistration.isPasswordChangedSuccessfully(), "Password change was not successful!");
    }

    @After("@UserRegistrationTest")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
