package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	WebElement userEmail;
	
	@FindBy(id="pass")
	WebElement passwordEle;
	
	@FindBy(id="send2")
	WebElement submit;
	
	@FindBy(css=".message-error.error.message div")
	WebElement errorMessage;
	
	public void loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
