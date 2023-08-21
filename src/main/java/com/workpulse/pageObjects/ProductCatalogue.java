package com.workpulse.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.workpulse.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver ;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		//initillationsDriver
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory 
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement sppiner; 
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By tostMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName )
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(tostMessage);
		waitForElementToDisappear(sppiner);				
	}

	


}
