package EmirSeho.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import EmirSeho.TestComponents.BaseTest;
import PageObjects.CartPage;
import PageObjects.CheckoutShippingPage;
import PageObjects.ConfirmationPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MenJacketsCatalogue;
import PageObjects.MenPage;
import PageObjects.PaymantMethodPage;
import PageObjects.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName = "Lando Gym Jacket";
	
	@Test
	public void SubmitOrder() throws InterruptedException, IOException {
		
		//removed bacause of @BeforeMethod
		//HomePage homePage = launchApplication();

		LoginPage loginPage = homePage.clickOnSignIn();
		
		loginPage.loginApplication("seho-emir@gmail.com", "Verysecureone1");
		
		MenPage menPage = homePage.clickOnMenTabLink();
		
		MenJacketsCatalogue menJacketsCatalogue = menPage.clickOnJackets();
		
		ProductPage productPage = menJacketsCatalogue.addProductToCart(productName);
		
		productPage.addItemToCart();
		
		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);	
		Assert.assertTrue(match);
		
		CheckoutShippingPage checkoutShippingPage = cartPage.goToCheckout();
		
		PaymantMethodPage paymantMethodPage = checkoutShippingPage.goToPaymantMethodPage();
		
		ConfirmationPage confirmationPage = paymantMethodPage.placeOrder();
		
		System.out.println(confirmationPage.getConfirmationMessage());
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void CheckoutCartPopupOrderHistoryTest()
	{
		LoginPage loginPage = homePage.clickOnSignIn();
		loginPage.loginApplication("seho-emir@gmail.com", "Verysecureone1");
		homePage.checkoutCartPopup();
		Assert.assertFalse(homePage.verifyOrdersInCartPopup(productName));
	}

}























