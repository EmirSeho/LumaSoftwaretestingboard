package EmirSeho.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
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
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		//removed bacause of @BeforeMethod
		//HomePage homePage = launchApplication();

		LoginPage loginPage = homePage.clickOnSignIn();
		
		loginPage.loginApplication(input.get("email"), input.get("password"));
		
		MenPage menPage = homePage.clickOnMenTabLink();
		
		MenJacketsCatalogue menJacketsCatalogue = menPage.clickOnJackets();
		
		ProductPage productPage = menJacketsCatalogue.addProductToCart(input.get("product"));
		
		productPage.addItemToCart();
		
		CartPage cartPage = productPage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));	
		Assert.assertTrue(match);
		
		CheckoutShippingPage checkoutShippingPage = cartPage.goToCheckout();
		
		PaymantMethodPage paymantMethodPage = checkoutShippingPage.goToPaymantMethodPage();
		
		ConfirmationPage confirmationPage = paymantMethodPage.placeOrder();
		
		System.out.println(confirmationPage.getConfirmationMessage());
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"}, dataProvider = "getData", groups = {"Purchase"})
	public void CheckoutCartPopupOrderHistoryTest(HashMap<String,String> input)
	{
		LoginPage loginPage = homePage.clickOnSignIn();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		homePage.checkoutCartPopup();
		Assert.assertFalse(homePage.verifyOrdersInCartPopup(input.get("product")));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{		
		List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\Emir\\Desktop\\New folder\\eclipse\\workpl\\LumaSoftwaretestingboard\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}























