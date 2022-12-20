package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.CheckoutShippingPage;
import PageObjects.MenPage;


public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By messageSuccessBy = By.cssSelector(".message-success.success.message");
	
	@FindBy(css=".action.showcart")
	WebElement cartIcon;
	
	@FindBy(css=".action.viewcart")
	WebElement cartPageLink;
	
	@FindBy(xpath="//span[text()='Men']")
	WebElement menTabLink;
	
	@FindBy(id="top-cart-btn-checkout")
	WebElement proceedToCheckoutButton;
	
	By counterNumber = By.cssSelector(".counter.qty .counter-number");
	
	By cartPopup = By.id("ui-id-1");
	
	@FindBy(css = "#ui-id-1 .product-item-details .product-item-name a")
	private List<WebElement> productNames;
	
	public CartPage goToCartPage() {
		waitForElementToAppear(messageSuccessBy);
		cartIcon.click();
		cartPageLink.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public MenPage clickOnMenTabLink()
	{
		menTabLink.click();
		MenPage menPage = new MenPage(driver);
		return menPage;
	}
	
	public CheckoutShippingPage proceedToCheckoutShippingPage()
	{
		waitForElementToAppear(counterNumber);
		cartIcon.click();
		proceedToCheckoutButton.click();
		CheckoutShippingPage checkoutShippingPage = new CheckoutShippingPage(driver);
		return checkoutShippingPage;
	}
	
	public void checkoutCartPopup()
	{
		waitForWebElementToAppear(cartIcon);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", cartIcon);
	}
	
	public Boolean verifyOrdersInCartPopup(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToBeClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(15000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.invisibilityOf(ele));

	}

}
