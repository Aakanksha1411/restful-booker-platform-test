package internal.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import internal.base.AbstractComponent;

public class BookRoom extends AbstractComponent {

	WebDriver driver;
	
	public BookRoom bookroomdetails;

	@FindBy(css = "div[aria-label='Month View']")
	WebElement selectedDates;

	@FindBy(css = "input[placeholder='Firstname']")
	WebElement firstName;

	@FindBy(css = "input[placeholder='Lastname']")
	WebElement lastName;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	@FindBy(xpath = "//input[@name='phone']")
	WebElement phone;

	@FindBy(css = ".btn.btn-outline-primary.float-right.book-room")
	WebElement book;

	@FindBy(css = "div[class='alert alert-danger'] p")
	WebElement errorMessage;

	@FindBy(css = ".alert.alert-danger")
	WebElement errorMessageNull;

	@FindBy(css = "button[class='btn btn-outline-primary']")
	WebElement close;

	@FindBy(xpath = ".//div[@aria-label='onRequestClose Example']")
	WebElement successMessage;
	
	
	

	@FindBy(css = ".rbc-event-content")
	WebElement dateSelection;

	public BookRoom(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void bookdetails(String userFirstName, String userLastName, String userEmail, String userContact) 
	{
		firstName.sendKeys(userFirstName);
		lastName.sendKeys(userLastName);
		email.sendKeys(userEmail);
		phone.sendKeys(userContact);
		book.click();
	}

	public String getBlankErrorMessage() {

		return errorMessage.getText();
	}

	public String getBlankErrorMessageNull() {

		return errorMessageNull.getText();
	}

	public void selectDate(String month, List<String> daysList) throws Exception
	{
		List<String> sortedList = daysList.stream().sorted().collect(Collectors.toList());     

		Actions actions = new Actions(driver);

		while (!driver.findElement(By.cssSelector(".rbc-toolbar-label")).getText().contains(month)) {
			driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		}

		WebElement startDay = driver.findElement(
				By.xpath("//div[@class='rbc-row ']//div[not(contains(@class,'rbc-off-range'))]/button[text()="
						+ sortedList.get(0) + "]"));

		WebElement endDay = driver.findElement(
				By.xpath("//div[@class='rbc-row ']//div[not(contains(@class,'rbc-off-range'))]/button[text()="
						+ sortedList.get(sortedList.size()-1) + "]"));

		actions.clickAndHold(startDay)
		.moveByOffset(-6, 0)
		.moveToElement(endDay)
		.release().build().perform();
	}

	public void closePopUp() 
	{
		close.click();
	}

	public String confirmation()
	{
		return successMessage.getText();
	}

	public String selectedDates()
	{
		return dateSelection.getText();
	}
}
