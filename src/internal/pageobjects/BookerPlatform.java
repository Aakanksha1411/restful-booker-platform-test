package internal.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import internal.base.AbstractComponent;

public class BookerPlatform extends AbstractComponent{

	WebDriver driver;

	@FindBy(css =".row.hotel-description")
	WebElement welcomeMessage;

	@FindBy(id ="name")
	WebElement name;

	@FindBy(id ="email")
	WebElement email;

	@FindBy(id ="phone")
	WebElement phone;

	@FindBy(id ="subject")
	WebElement subject;

	@FindBy(id ="description")
	WebElement description;

	@FindBy(id ="submitContact")
	WebElement submitContact;

	@FindBy(css = "div[class='alert alert-danger'] p")
	WebElement errorMessage;

	@FindBy(css = "div[class='col-sm-5'] div h2")
	WebElement confirmMessage;

	@FindBy(xpath = "//button[normalize-space()='Book this room']")
	WebElement bookingRoom;

	public BookerPlatform(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void submitUserDetails(String userName, String userEmail, String userContact, String subjectMsg, String userMessage) 
	{ 
		name.sendKeys(userName); 
		email.sendKeys(userEmail);
		phone.sendKeys(userContact); 
		subject.sendKeys(subjectMsg);
		description.sendKeys(userMessage); 
		submitContact.click();
	}

	public BookRoom submitRoomBooking()
	{
		bookingRoom.click();
		BookRoom bookRoomDetails = new BookRoom(driver);  
		return bookRoomDetails;
	}

	public String getWelcomeMessage()
	{
		return welcomeMessage.getText();
	}

	public String getBlankErrorMessage()
	{
		return errorMessage.getText();
	}

	public String confirmMessage()
	{
		return confirmMessage.getText();
	}

	public void init()
	{
		driver.get("https://automationintesting.online/#/");
	}

}


		


		

	


