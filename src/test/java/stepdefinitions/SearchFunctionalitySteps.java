package stepdefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchFunctionality;
import utils.DriverFactory;

public class SearchFunctionalitySteps {
	WebDriver driver;
	WebDriverWait wait;
	HomePage homePage;
	SearchFunctionality searchFunctionality;

	@Before("@SearchFunctionalityTest")
	public void setUp() {
		driver = DriverFactory.initializeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		homePage = new HomePage(driver);
		searchFunctionality = new SearchFunctionality(driver, wait);
	}
	@Given("User is on the homepage")
	public void user_is_on_the_bookswagon_homepage() {
		driver.get("https://www.bookswagon.com/");
	}
	@When("User searches for {string} book in the search bar")
	public void user_searches_for_a_book_in_the_search_bar(String bookName) {
		homePage.searchForBook(bookName);
	}
	@When("User filters the book by discount 21% - 30% in the refine search section")
	public void user_filters_the_book_by_discount_in_the_refine_search_section() {
	    WebElement discountFilter = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[3]/li[4]")
	    ));
	    discountFilter.click();
	}

	@When("User filters the book by price Rs.500 - Rs.1000 in the refine search section")
	public void user_filters_the_book_by_price_in_the_refine_search_section() {
		searchFunctionality.filterByPrice();		
	}
	
	@When("User filters the book by language English in the refine search section")
	public void user_filters_the_book_by_language_in_the_refine_search_section() {
		searchFunctionality.filterByLanguage();
	}


	@Then("Searched filters are displayed")
	public void search_results_are_displayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'search-results')]")));

	    By bookTitleLocator = By.xpath("//div[contains(@class, 'search-results')]//a[contains(text(), 'Harry Potter')]");

	    wait.until(ExpectedConditions.presenceOfElementLocated(bookTitleLocator));

	    boolean isBookPresent = driver.findElements(bookTitleLocator).size() > 0;

	    Assert.assertTrue(isBookPresent, "The book 'Harry Potter' should be present in the search results.");
	}


	@After("@SearchFunctionalityTest")
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}

