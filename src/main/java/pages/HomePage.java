package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;


	private By searchBar = By.id("inputbar");
	private By searchButton = By.id("btnTopSearch");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchForBook(String bookName) {
		driver.findElement(searchBar).sendKeys(bookName);
		driver.findElement(searchButton).click();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

}