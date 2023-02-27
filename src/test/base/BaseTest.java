package test.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import internal.pageobjects.BookerPlatform;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public BookerPlatform bookerPlatform;

	@BeforeMethod
	public BookerPlatform launchapplication() throws IOException {

		intializeDriver();
		bookerPlatform = new BookerPlatform(driver);
		bookerPlatform.init();
		return bookerPlatform;
	}

	@AfterMethod 
	public void TearDown() 
	{
		driver.close(); 
	}
	
	public WebDriver intializeDriver() throws IOException
	{

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\resources\\global.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) 
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		}
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			driver = new EdgeDriver(edgeOptions);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.manage().window().maximize();
		
		return driver;
	}
}
