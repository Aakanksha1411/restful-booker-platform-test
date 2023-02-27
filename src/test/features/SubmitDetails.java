package test.features;

import org.testng.Assert;
import org.testng.annotations.Test;

import internal.pageobjects.BookerPlatform;
import test.base.BaseTest;

public class SubmitDetails extends BaseTest 
{

	@Test
	public void Successdetailsubmission() 
	{
		BookerPlatform bookerPlatform = new BookerPlatform(driver);
		bookerPlatform.submitUserDetails("Aakanksha", "aakanksha1411@gmail.com", "971708504521", "Hi There ",
				"Need to Book room for 4 people");
		String message = bookerPlatform.confirmMessage();
		Assert.assertTrue(message.contains("Aakanksha"));
	}

	@Test
	public void validateHomePageMessage() 
	{
		String Message = bookerPlatform.getWelcomeMessage();
		Assert.assertEquals(Message,
				"Welcome to Shady Meadows, a delightful Bed & Breakfast nestled in the hills on Newingtonfordburyshire. A place so beautiful you will never want to leave. All our rooms have comfortable beds and we provide breakfast from the locally sourced supermarket. It is a delightful place.");
	}

	@Test
	public void validateFooterlinkOnBookRoom() 
	{
		Assert.assertEquals(bookerPlatform.getFooterCount(), 6);
	}
}
