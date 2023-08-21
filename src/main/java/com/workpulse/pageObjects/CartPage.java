package com.workpulse.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.workpulse.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver ;
	
	public CartPage(WebDriver driver)
	{
		
		//initillationsDriver
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cardProducts;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match =	cardProducts.stream().anyMatch(cardProduct-> cardProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		
	}
	
	
	

}
