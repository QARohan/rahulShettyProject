package E2E.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.workpulse.pageObjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage landigPage;
	public ExtentSparkReporter reporter ;
	public ExtentReports extent ;
	public WebDriver intialization() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\workpulse\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); // When we will use system variable data so need the java ternary operator.
		
	    //String browserName = prop.getProperty("browser"); It's working while we are not use the system variable property like execute code via mvn using passing the data parameter.
		
		
	    if(browserName.equalsIgnoreCase("chrome"))
	    {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	
	    }
	    else if (browserName.equalsIgnoreCase("firefox"))
	    {
	    	// Add the firefox driver with system properties 
	    }
	    else if (browserName.equalsIgnoreCase("edge"))
	    {
	    	// Add the edge driver with system properties 
	    }
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    return driver;
	       
	}
	
	
	public ExtentReports extentReportToTest()
	{
		//code is working but we created same methods under the ExtentReporterNG.java file.
		String path = System.getProperty("user.dir")+"\\Reports\\index.html"; 
		reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Workpulse Automation Framework !");
		reporter.config().setDocumentTitle("Workpulse Test Result !");
		
		//Main extent report creation 
		 extent = new ExtentReports();
		 extent.attachReporter(reporter);
		 extent.setSystemInfo("Tester", "Rohan Gupta");
		 return extent;
		
		
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string 
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//convert String to HashMap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		try {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\" +testCaseName+ ".png");
		FileUtils.copyDirectory(source, file);
		}catch (IOException e)
		{
			System.out.println("Failed to take the screenshot: " + e.getMessage());
			
		}
		return System.getProperty("user.dir")+"\\Reports\\" +testCaseName+ ".png";
	}
	
	
	
	@BeforeMethod(alwaysRun =true)
	public LandingPage launchApplication() throws IOException
	{
		
		driver = intialization();
	    landigPage = new LandingPage(driver);
		landigPage.goTo();
		return landigPage;
		
	}
	
	@AfterMethod(alwaysRun =true)
	public void tearDown()
	{
		driver.close();	
	}

}
