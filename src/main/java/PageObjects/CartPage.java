package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EmirSeho.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	By goToCheckoutBy = By.cssSelector("li[class='item'] button");

	@FindBy(css = "li[class='item'] button")
	WebElement goToCheckoutEle;

	@FindBy(css = "div[class='product-item-details'] strong a")
	private List<WebElement> cartProducts;

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutShippingPage goToCheckout() {
		waitForWebElementToBeClickable(goToCheckoutBy);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", goToCheckoutEle);
		CheckoutShippingPage checkoutShippingPage = new CheckoutShippingPage(driver);
		return checkoutShippingPage;
	}
	
}
