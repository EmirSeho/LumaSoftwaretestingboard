package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class MenPage  extends AbstractComponent{

	WebDriver driver;
	
	public MenPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(xpath="//a[text()='Jackets']")
	@FindBy(xpath="//a[text()='Tops']")
	WebElement tops;
	
	public MenTopsCatalogue clickOnTops()
	{
		tops.click();
		MenTopsCatalogue menTopsCatalogue = new MenTopsCatalogue(driver);
		return menTopsCatalogue;
	}
	

}
