package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

	private By emailField = By.id("ctl00_phBody_SignIn_txtEmail");
	private By passwordField = By.id("ctl00_phBody_SignIn_txtPassword");
	private By loginButton = By.id("ctl00_phBody_SignIn_btnLogin");
	private By profileIcon = By.id("ctl00_lblUser");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}

	public boolean isUserLoggedIn() {
		return driver.findElement(profileIcon).isDisplayed();
	}
}
