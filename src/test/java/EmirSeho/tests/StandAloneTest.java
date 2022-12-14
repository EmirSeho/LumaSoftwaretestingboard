package EmirSeho.tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "Lando Gym Jacket";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.get("https://magento.softwaretestingboard.com/what-is-new.html");
		
		//log in
		driver.findElement(By.className("authorization-link")).click();
		driver.findElement(By.id("email")).sendKeys("seho-emir@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Verysecureone1");
		driver.findElement(By.id("send2")).click();
		
		
		//go to catalogue
		//driver.findElement(By.id("ui-id-5")).click();
		driver.findElement(By.xpath("//span[text()='Men']")).click();
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
		
		//find and click on item
		List<WebElement> products = driver.findElements(By.cssSelector(".item.product.product-item"));
		WebElement prod = products.stream().filter(p->p.findElement(By.cssSelector("div strong a")).getText().equals(productName)).findFirst().orElse(null);
		
		//prod.findElement(By.cssSelector("button span")).click();
		prod.findElement(By.cssSelector("div strong a")).click();
		
		//select item options and add it to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-size-143-item-168")));
		driver.findElement(By.id("option-label-size-143-item-168")).click();
		driver.findElement(By.id("option-label-color-93-item-50")).click();
		driver.findElement(By.id("product-addtocart-button")).click();
		
		//go to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-success.success.message")));
		driver.findElement(By.cssSelector(".action.showcart")).click();		
		driver.findElement(By.cssSelector(".action.viewcart")).click();
		
		//confirm item in cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='product-item-details'] strong a"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//go to checkout shipping
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("checkout-loader"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[class='item'] button")));
		driver.findElement(By.cssSelector("li[class='item'] button")).click();
		
		//go to checkout payment
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("checkout-loader"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.action.continue.primary")));
		driver.findElement(By.cssSelector(".button.action.continue.primary")).click();
		
		//place order
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".payment-method._active .action.primary.checkout")));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.findElement(By.cssSelector(".block.items-in-cart.active div[class='title']")).click();
		//driver.findElement(By.cssSelector(".payment-method._active .action.primary.checkout")).click();
		
		WebElement placeOrder = driver.findElement(By.cssSelector(".payment-method._active .action.primary.checkout"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", placeOrder);
		//Actions click = new Actions(driver);
		//click.moveToElement(placeOrder).click().build().perform();
		
		
	}

}























