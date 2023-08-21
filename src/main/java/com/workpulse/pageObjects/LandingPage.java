package com.workpulse.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.workpulse.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver ;
	
	public LandingPage(WebDriver driver)
	{
		
		//initillationsDriver
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement erroeMessage;
	
	
	
	
	public ProductCatalogue loginApplication(String email, String password ) {
		// TODO Auto-generated method stub
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();	
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	

	
	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String verifyErrorMessage() {
		waitForWebElementToAppear(erroeMessage);
		 return erroeMessage.getText();
	}

}
