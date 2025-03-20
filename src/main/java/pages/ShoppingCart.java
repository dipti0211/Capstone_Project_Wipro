package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShoppingCart {
	private WebDriver driver;
	private WebDriverWait wait;

	//private By cartIcon = By.id("ctl00_lblTotalCartItems");
	private By cartIcon=By.xpath("//*[@id=\"aspnetForm\"]/header/div[2]/div/div[3]/ul/li[2]/a/span");
	private By deleteProduct = By.id("ctl00_phBody_BookCart_lvCart_ctrl0_imgDelete");
	private By increaseQuantity = By.id("ctl00_phBody_BookCart_lvCart_ctrl1_btnPlus");
	private By checkoutButton = By.id("ctl00_phBody_BookCart_lvCart_imgPayment");
	private By checkoutPageTitle = By.tagName("h1");

	private By addToCartButton1 = By.xpath("//*[@id=\"listSearchResult\"]/div[1]/div[4]/div[4]/input[1]");
	private By addToCartButton2 = By.xpath("//*[@id=\"listSearchResult\"]/div[2]/div[4]/div[5]/input[1]");

	// **Constructor**
	public ShoppingCart(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void openCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
		driver.findElement(cartIcon).click();
	}

	public void addItemToCart() throws InterruptedException {

		WebElement cartButton1 = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton1));
		Thread.sleep(1000); 
		cartButton1.click();

		WebElement cartButton2 = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton2));
		Thread.sleep(1000);
		cartButton2.click();
	}


	public void removeProduct() {
		WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_phBody_BookCart_lvCart_ctrl0_imgDelete")));
		deleteIcon.click();

		wait.until(ExpectedConditions.stalenessOf(deleteIcon));

		System.out.println("Product successfully removed from the cart.");
	}



	public void increaseQuantity() {
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(increaseQuantity));

			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

			System.out.println("Product quantity incremented successfully.");

		} catch (TimeoutException e) {
			System.out.println("Timeout while waiting for the quantity increment button: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred while increasing quantity: " + e.getMessage());
		}
	}

	public void proceedToCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
	}

	public String verifyCheckoutPage() {
		return driver.findElement(checkoutPageTitle).getText();

	}
}
