package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class PaymantMethodPage extends AbstractComponent{

	WebDriver driver;
	
	public PaymantMethodPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By placeOrderBy = By.cssSelector(".payment-method._active .action.primary.checkout");
	
	@FindBy(css = ".payment-method._active .action.primary.checkout")
	WebElement placeOrder;
	
	public ConfirmationPage placeOrder() {
		waitForWebElementToBeClickable(placeOrderBy);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", placeOrder);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
