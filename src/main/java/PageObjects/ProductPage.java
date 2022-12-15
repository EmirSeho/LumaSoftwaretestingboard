package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import EmirSeho.AbstractComponents.AbstractComponent;

public class ProductPage  extends AbstractComponent{

	WebDriver driver;
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="option-label-size-143-item-168")
	WebElement productSize;
	
	@FindBy(id="option-label-color-93-item-50")
	WebElement productColor;
	
	@FindBy(id="product-addtocart-button")
	WebElement addtoCartButton;
	
	By productSizeBy = By.id("option-label-size-143-item-168");
	
	
	public void addItemToCart()
	{
		waitForElementToAppear(productSizeBy);
		productSize.click();
		productColor.click();
		addtoCartButton.click();
	}

}
