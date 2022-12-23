package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;



public class MenTopsCatalogue extends AbstractComponent{

	WebDriver driver;
		
	public MenTopsCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".product.details.product-item-details strong a")
	List<WebElement> products;
	
	//By addToCart = By.cssSelector("div strong a");
	
	public List<WebElement> getProductList() {
		return products;
	}
	
	//By addToCart = By.cssSelector(".primary .action.tocart.primary");
	
	@FindBy(xpath = "//div[contains(@class,'column main')]/div[4]//a[contains(@class,'next')]")
	WebElement nextPage;
	
	public ProductPage addProductToCart(String productName)
	{		
		WebElement prod = getProductList().stream().filter(product->product.getText().equals(productName)).findFirst().orElse(null);
		
		while(prod == null) {
			nextPage.click();
			prod = getProductList().stream().filter(product->product.getText().equals(productName)).findFirst().orElse(null);
		}
		
		prod.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	
}
