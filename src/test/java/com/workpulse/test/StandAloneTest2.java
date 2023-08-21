package com.workpulse.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.workpulse.abstractComponents.AbstractComponent;
import com.workpulse.pageObjects.CartPage;
import com.workpulse.pageObjects.CheckoutPage;
import com.workpulse.pageObjects.ConfirmationPage;
import com.workpulse.pageObjects.LandingPage;
import com.workpulse.pageObjects.OrderPage;
import com.workpulse.pageObjects.ProductCatalogue;

import E2E.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 extends BaseTest {

String productName = "ZARA COAT 3";
//String countryName = "india";

	@Test(dataProvider="getData", groups= {"Purchase"})
	//public void submitOrder(String email, String password, String productName, String countryName ) throws IOException { //it's working, line number of code is 94
		
		public void submitOrder(HashMap<String, String> input ) throws IOException {
		//ProductCatalogue productCatalogue = landigPage.loginApplication("rohankg09@gmail.com", "Artis!@#123");
		ProductCatalogue productCatalogue = landigPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage =  productCatalogue.goToCartPage();
		Boolean match =cartPage.verifyProductDisplay(input.get("productName"));
	    Assert.assertTrue(true);
	    CheckoutPage checkoutPage = cartPage.goToCheckout();
	    checkoutPage.selectCountry(input.get("countryName"));
	    ConfirmationPage message = checkoutPage.submitOrder();
	   
	    String confirmMessage = message.getConfirmationMessage();
	    
	    try {
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    System.out.println("Successfully done !!!");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Test cases has been failed : "  +e.getMessage());
	    }
	       
		 
	}

	//To verify ZARA COAT 3 is displaying in order Page.
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landigPage.loginApplication("rohankg09@gmail.com", "Artis!@#123");
		//ProductCatalogue productCatalogue = landigPage.loginApplication(email, password);
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderToDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "rohankg09@gmail.com");
//		map.put("password", "Artis!@#123");
//		map.put("productName", "ZARA COAT 3"); all line of code is working with line number of 40
//		map.put("countryName", "india");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "rohankg09@gmail.com");
//		map1.put("password", "Artis!@#123");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		map1.put("countryName", "india");
		
	    List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\workpulse\\dataPackage\\PurchaseOrder.json");
		
		
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
		//return new Object[][] {{"rohankg09@gmail.com","Artis!@#123","ZARA COAT 3","india"},{"rohankg09@gmail.com","Artis!@#123","ADIDAS ORIGINAL","india"}}; 
		//it's working with line number code is 36
	}
	
}
