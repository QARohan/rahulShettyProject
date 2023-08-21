package com.workpulse.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.workpulse.pageObjects.CartPage;
import com.workpulse.pageObjects.ProductCatalogue;

import E2E.TestComponents.BaseTest;


public class ErrorValidationTest extends BaseTest {


	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException {
		
		ProductCatalogue productCatalogue =landigPage.loginApplication("rohankg09@gmail.om", "Artis!@#123");
	    try {
	    	Assert.assertEquals("Incorrect email  password.", landigPage.verifyErrorMessage());
	    }catch(Exception e)
	    {
	    	System.out.println("Test cases has been failed: "+ e.getMessage());
	    }
		
		

	}
	
	@Test(groups= {"ErrorHandling"})
	public void productErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		String countryName = "india";
		ProductCatalogue productCatalogue = landigPage.loginApplication("rohankg09@gmail.com", "Artis!@#123");
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage =  productCatalogue.goToCartPage();
		Boolean match =cartPage.verifyProductDisplay("ZARA COAT 3456");
	    Assert.assertFalse(match); // Because of product is not matched there and we added assertfalse option for this.
	   
	    
		
	}

}


