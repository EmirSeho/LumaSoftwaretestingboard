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

import EmirSeho.TestComponents.BaseTest;
import PageObjects.CartPage;
import PageObjects.CheckoutShippingPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MenJacketsCatalogue;
import PageObjects.MenPage;
import PageObjects.PaymantMethodPage;
import PageObjects.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "Lando Gym Jacket";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		HomePage homePage = new HomePage(driver);
		homePage.goTo();
		homePage.clickOnSignIn();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginApplication("seho-emir@gmail.com", "Verysecureone1");
		
		homePage.clickOnMenTabLink();
		
		MenPage menPage = new MenPage(driver);
		menPage.clickOnJackets();
		
		MenJacketsCatalogue menJacketsCatalogue = new MenJacketsCatalogue(driver);
		menJacketsCatalogue.addProductToCart(productName);
		
		ProductPage productPage = new ProductPage(driver);
		productPage.addItemToCart();
		productPage.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(productName);	
		Assert.assertTrue(match);	
		cartPage.goToCheckout();
		
		CheckoutShippingPage checkoutShippingPage = new CheckoutShippingPage(driver);
		checkoutShippingPage.goToPaymantMethodPage();
		
		PaymantMethodPage paymantMethodPage = new PaymantMethodPage(driver);
		paymantMethodPage.placeOrder();
		
	}

}























