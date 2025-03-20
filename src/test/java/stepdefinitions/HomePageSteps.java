package stepdefinitions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.DriverFactory;

public class HomePageSteps {
	WebDriver driver;
	WebDriverWait wait;
	HomePage homePage;

	@Before("@SearchTest")
	public void setUp() {
		driver = DriverFactory.initializeDriver();
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
	}

	@Given("User is on the Bookswagon homepage")
	public void user_is_on_the_bookswagon_homepage() {
		driver.get("https://www.bookswagon.com/");
	}
	@When("User searches for a book {string} in the search bar")
	public void user_searches_for_a_book_in_the_search_bar(String bookName) {
		homePage.searchForBook(bookName);
	}
	@Then("Search results are displayed")
	public void search_results_are_displayed() {
		String actualTitle=homePage.getPageTitle();
		String expectedTitle = "harry potter - Books - 24x7 online bookstore Bookswagon.com";
		Assert.assertEquals(actualTitle,expectedTitle);
		boolean isBookPresent = driver.findElements(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[2]/div[1]/div[1]/h1/span")).size() > 0;
		Assert.assertTrue(isBookPresent, "The book 'Harry Potter' should be present in the search results.");
		}
	@After
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}