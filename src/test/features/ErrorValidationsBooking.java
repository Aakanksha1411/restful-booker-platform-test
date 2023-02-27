package test.features;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import internal.pageobjects.BookRoom;
import test.base.BaseTest;

public class ErrorValidationsBooking extends BaseTest 
{

	@Test
	public void invalidNameDuringBooking() throws Exception
	{
		BookRoom bookRoomDetails = bookerPlatform.submitRoomBooking();
		bookRoomDetails.selectDate("September", Arrays.asList("22", "23", "24"));
		bookRoomDetails.bookdetails("Aa", "Mishra", "aakanksha1411@gmail.com", "9713437080452");
		Assert.assertEquals("size must be between 3 and 18", bookRoomDetails.getBlankErrorMessage());
	}

	@Test
	public void invalidEmailDuringBooking() throws Exception
	{
		BookRoom bookRoomDetails = bookerPlatform.submitRoomBooking();
		bookRoomDetails.selectDate("July", Arrays.asList("2", "3", "4"));
		bookRoomDetails.bookdetails("Aakanksha", "Mishra", "gmail", "97170855550452");
		Assert.assertEquals("must be a well-formed email address", bookRoomDetails.getBlankErrorMessage());
	}

	@Test
	public void inValidPhoneDuringBooking() throws Exception
	{
		BookRoom bookRoomDetails = bookerPlatform.submitRoomBooking();
		bookRoomDetails.selectDate("July", Arrays.asList("2", "3", "4"));
		bookRoomDetails.bookdetails("Aakanksha", "Mishra", "aakanksha@gmail.com", "9733331788217777722992290");
		Assert.assertEquals("size must be between 11 and 21", bookRoomDetails.getBlankErrorMessage());
	}

	@Test
	public void dateNullDuringBooking() throws Exception
	{
		BookRoom bookRoomDetails = bookerPlatform.submitRoomBooking();
		bookRoomDetails.bookdetails("Aakanksha", "Mishra", "aakanksha@gmail.com", "97333312992290");
		Assert.assertEquals("must not be null", bookRoomDetails.getBlankErrorMessage());
	}

	@Test
	public void unavailableDate() throws Exception
	{
		BookRoom bookRoomDetails = bookerPlatform.submitRoomBooking();
		bookRoomDetails.selectDate("April", Arrays.asList("13", "14"));
		bookRoomDetails.bookdetails("Aakanksha", "Mishra", "aakanksha@gmail.com", "973333178558290");
		bookRoomDetails.closePopUp();
		
		BookRoom bookroomdetails1 = bookerPlatform.submitRoomBooking();
		bookroomdetails1.selectDate("April", Arrays.asList("13", "14"));
		bookroomdetails1.bookdetails("John", "monroe", "John1344@gmail.com", "9713437080452");
		Assert.assertEquals(
				"The room dates are either invalid or are already booked for one or more of the dates that you have selected.",
				bookRoomDetails.getBlankErrorMessageNull());
	}

}
