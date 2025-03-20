package stepdefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;
import utils.ScreenshotUtil;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before("@LoginTest")
    public void setUp() {
    	driver = DriverFactory.initializeDriver();
		driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }


    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://www.bookswagon.com/login");
    }

    @When("User enters email {string} and password {string}")
    public void user_enters_email_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("Clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        boolean isLoggedIn = loginPage.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new AssertionError("User login failed!");
        }
    }

    @After("@LoginTest")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtil.captureScreenshot(driver, scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}

