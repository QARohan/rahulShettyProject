package com.workpulse.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.workpulse.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver ;
	
	public CheckoutPage(WebDriver driver)
	{
		
		//initillationsDriver
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	@FindBy(css="[placeholder = 'Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement selectCountry;
	
	By result = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(result);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		ConfirmationPage message = new ConfirmationPage(driver);
		return message;
		
	}

	
	
	

}
