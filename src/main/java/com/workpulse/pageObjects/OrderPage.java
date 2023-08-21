package com.workpulse.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.workpulse.abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver ;
	
	public OrderPage(WebDriver driver)
	{
		
		//initillationsDriver
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean verifyOrderToDisplay(String productName)
	{
		Boolean match = null;
		try {
         match =	productNames.stream().anyMatch(productNames-> productNames.getText().equalsIgnoreCase(productName));
		
		}
		catch (Exception e)
		{
			System.out.println("Test cases has been failed :" +e.getMessage());
		}
		return match;
	}

	
	
	

}
