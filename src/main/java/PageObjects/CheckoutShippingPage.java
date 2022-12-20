package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EmirSeho.AbstractComponents.AbstractComponent;

public class CheckoutShippingPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutShippingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "checkout-loader")
	WebElement checkoutLoader;
	
	By goToPaymantMethodPageButtonBy = By.cssSelector(".button.action.continue.primary");
	
	@FindBy(css = ".button.action.continue.primary")
	WebElement goToPaymantMethodPageButton;
	
	public PaymantMethodPage goToPaymantMethodPage() throws InterruptedException {
		//waitForElementToDisappear(checkoutLoader);
		waitForWebElementToBeClickable(goToPaymantMethodPageButtonBy);
		//goToPaymantMethodPageButton.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", goToPaymantMethodPageButton);
		PaymantMethodPage paymantMethodPage = new PaymantMethodPage(driver);
		return paymantMethodPage;
	}

}
