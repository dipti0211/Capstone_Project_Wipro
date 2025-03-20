package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFunctionality {
	private WebDriver driver;
	private WebDriverWait wait;

	private By discountFilter = By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[3]/li[4]/a");
	private By priceFilter = By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[2]/li[2]/a");
	private By languageFilter = By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[8]/li[2]/a");
	private By searchResultHeader = By.xpath("//*[@id='site-wrapper']/div[1]/div[2]/div[1]/div[1]/h1/span");

	public SearchFunctionality(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void filterByDiscount() {
		try {
			WebElement discountElement = wait.until(ExpectedConditions.elementToBeClickable(discountFilter));
			discountElement.click();

			wait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfElementLocated(searchResultHeader)));
		} catch (StaleElementReferenceException e) {
			WebElement discountElement = wait.until(ExpectedConditions.elementToBeClickable(discountFilter));
			discountElement.click();
		}
	}


	public void filterByPrice() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(priceFilter)).click();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-indicator")));
			System.out.println("Price filter applied successfully.");
		} catch (StaleElementReferenceException e) {
			System.out.println("StaleElementReferenceException encountered. Retrying...");
			wait.until(ExpectedConditions.elementToBeClickable(priceFilter)).click();

		}
	}



	public void filterByLanguage() {
		try {

			WebElement languageElement = wait.until(ExpectedConditions.elementToBeClickable(languageFilter));
			languageElement.click();
			wait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfElementLocated(searchResultHeader)));
		} catch (StaleElementReferenceException e) {
			WebElement languageElement = wait.until(ExpectedConditions.elementToBeClickable(languageFilter));
			languageElement.click();
		}
	}


	public boolean areSearchResultsDisplayed() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        WebElement searchResults = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-results-item"))
	        );
	        return searchResults.isDisplayed();
	    } catch (Exception e) {
	        System.out.println("Search results did not load in time.");
	        return false;
	    }
	}

}
