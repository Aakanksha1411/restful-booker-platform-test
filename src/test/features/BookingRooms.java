package test.features;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import internal.pageobjects.BookRoom;
import test.base.BaseTest;

public class BookingRooms extends BaseTest 
{

	@Test
	public void validRoomBooking() throws Exception {

		String expectedMessage = "1 night(s) - £100";
		List<String> dateList = Arrays.asList("22", "23");
		
		BookRoom bookRoomDetails = bookerPlatform.submitRoomBooking();
		bookRoomDetails.selectDate("August", dateList);
		String actualMessage = bookRoomDetails.selectedDates();
		Assert.assertTrue(actualMessage.equals(expectedMessage));

		bookRoomDetails.bookdetails("Aakanksha", "Mishra", "aakanksha@gmail.com", "97372299288290");
		String messageSuccess= bookRoomDetails.confirmation();
		Assert.assertTrue(messageSuccess.contains("Booking Successful!"));
		
		bookRoomDetails.closePopUp();
	}

	@Test
	public void validateNumberOfHyperLinksOnBookingPage()
	{
		Assert.assertEquals(bookerPlatform.getFooterCount(), 6);
	}

	@Test
	public void validateclicksHyperLinksOnBookingPage()
	{
		Assert.assertTrue(bookerPlatform.clickFooters());
	}
}
