package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductDetailsPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By wishlistButton = By.xpath("//div[@id=\"listSearchResult\"]//input[@value=\"Add to Wishlist\"]");
    
    private By wishlistcountButton = By.id("ctl00_lblWishlistCount");
    private By addtocartButton = By.id("ctl00_phBody_WishList_lvWishList_ctrl0_btnaddtocart");
    private By cartItems = By.id("ctl00_lblTotalCartItems");
    private By cartProducts = By.id("ctl00_phBody_BookCart_lvCart_lblCartItems");

	public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void validateProductDetails(String expectedTitle, String expectedDescription, double minPrice, double maxPrice) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		By productTitle = By.id("ctl00_phBody_ProductDetail_lblTitle");
		WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
		String actualTitle = titleElement.getText();
		Assert.assertEquals(actualTitle, expectedTitle, "Book title mismatch!");

		By productDescription = By.id("ctl00_phBody_ProductDetail_lblLongDesc");
		WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productDescription));
		String actualDescription = descriptionElement.getText();
		Assert.assertEquals(actualDescription, expectedDescription, "Book description mismatch!");

		By productPrice = By.id("ctl00_phBody_ProductDetail_lblourPrice");
		WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
		String priceText = priceElement.getText().replaceAll("[^0-9.]", "").trim();
		double bookPriceValue = Double.parseDouble(priceText);
		Assert.assertTrue(bookPriceValue >= minPrice && bookPriceValue <= maxPrice, "Price is not within the expected range!");

		By productImage = By.cssSelector("img[alt*='Harry Potter']");
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(productImage)).isDisplayed(), "Book image is missing!");

		By productAvailability = By.id("ctl00_phBody_ProductDetail_lblAvailable");
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(productAvailability)).isDisplayed(), "Book availability info missing!");
	}


	public void addToWishlist() {
		wait.until(ExpectedConditions.presenceOfElementLocated(wishlistButton));
		WebElement wishlist = wait.until(ExpectedConditions.elementToBeClickable(wishlistButton));
		wishlist.click();
	}


	public void addToCart() {
		wait.until(ExpectedConditions.presenceOfElementLocated(wishlistcountButton));
        WebElement wishlistcount = wait.until(ExpectedConditions.elementToBeClickable(wishlistcountButton));
        wishlistcount.click();
        WebElement addtocart = wait.until(ExpectedConditions.elementToBeClickable(addtocartButton));
        addtocart.click();
	}


	public boolean isProductInCart() {
		driver.findElement(cartItems).click();
		return driver.findElements(cartProducts).size() > 0;
	}
}