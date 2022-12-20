package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;



public class MenJacketsCatalogue extends AbstractComponent{

	WebDriver driver;
		
	public MenJacketsCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".item.product.product-item")
	List<WebElement> products;
	
	By addToCart = By.cssSelector("div strong a");
	
	public List<WebElement> getProductList() {
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("div strong a")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public ProductPage addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	
	
	
	
	
}
