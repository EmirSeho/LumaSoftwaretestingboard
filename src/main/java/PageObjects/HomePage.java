package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EmirSeho.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="authorization-link")
	WebElement signInEle;
	
	public void goTo()
	{
		driver.get("https://magento.softwaretestingboard.com");
	}
	
	public LoginPage clickOnSignIn()
	{
		signInEle.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}

}
