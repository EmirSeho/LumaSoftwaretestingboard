package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".page-title-wrapper .page-title span")
	WebElement confirmationMessage;
	
	By continueShoppingButton = By.linkText("Continue Shopping");
	
	public String getConfirmationMessage()
	{
		waitForWebElementToBeClickable(continueShoppingButton);
		//PaymantMethodPage paymantMethodPage = new PaymantMethodPage(driver);	
		return confirmationMessage.getText();
	}
	
	
}