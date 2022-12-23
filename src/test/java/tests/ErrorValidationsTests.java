package tests;

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

import PageObjects.CartPage;
import PageObjects.CheckoutShippingPage;
import PageObjects.ConfirmationPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MenTopsCatalogue;
import PageObjects.MenPage;
import PageObjects.PaymantMethodPage;
import PageObjects.ProductPage;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTests extends BaseTest{

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidtion() throws InterruptedException, IOException {
		
		String productName = "Lando Gym Jacket";
		
		//removed bacause of @BeforeMethod
		//HomePage homePage = launchApplication();

		LoginPage loginPage = homePage.clickOnSignIn();
		
		loginPage.loginApplication("seho-emir@gmail.com", "VeryINsecureone1");
		
		Assert.assertEquals("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.",
				loginPage.getErrorMessage());	
		
	}
	
	@Test
	public void ProductErrorValidtion() throws InterruptedException, IOException {
		
		String productName = "Lando Gym Jacket";
		
		//removed bacause of @BeforeMethod
		//HomePage homePage = launchApplication();

		LoginPage loginPage = homePage.clickOnSignIn();
		
		loginPage.loginApplication("sehoemir@gmail.com", "Verysecureone1");
		
		MenPage menPage = homePage.clickOnMenTabLink();
		
		MenTopsCatalogue menTopsCatalogue = menPage.clickOnTops();
		
		ProductPage productPage = menTopsCatalogue.addProductToCart(productName);
		
		productPage.addItemToCart();
		
		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("BRANDO Gym Jacket");	
		Assert.assertFalse(match);
	}

}























