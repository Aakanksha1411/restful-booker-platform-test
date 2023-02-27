package test.features;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.base.BaseTest;

public class ErrorValidationsLanding extends BaseTest 
{
	
	@Test
	public void invalidNameDuringEnquiry()
	{
		bookerPlatform.submitUserDetails(" ", "aakanksha1411@gmail.com", "97170804521", "Hi There ", "Need to Book room for 4 people");
		Assert.assertEquals("Name may not be blank", bookerPlatform.getBlankErrorMessage());
	}

	
	@Test
	public void invalidEmailDuringEnquiry()
	{
		bookerPlatform.submitUserDetails("Aakanksha ", "aakanksha1411", "97170804521", "Hi There ", "Need to Book room for 4 people");
		Assert.assertEquals("must be a well-formed email address", bookerPlatform.getBlankErrorMessage());
	}
	

	@Test
	public void invalidContactDuringEnquiry()
	{
		bookerPlatform.submitUserDetails("Aakanksha ", "aakanksha1411@gmail.com", "971708045", "Hi There ", "Need to Book room for 4 people");
		Assert.assertEquals("Phone must be between 11 and 21 characters.", bookerPlatform.getBlankErrorMessage());
	}

	@Test
	public void invalidSubjectDuringEnquiry()
	{
		bookerPlatform.submitUserDetails("Aakanksha ", "aakanksha1411@gmail.com", "9717080454444", "Hi", "Need to Book room for 4 people");
		Assert.assertEquals("Subject must be between 5 and 100 characters.", bookerPlatform.getBlankErrorMessage());
	}

	@Test
	public void invalidMessageDuringEnquiry()
	{
		bookerPlatform.submitUserDetails("Aakanksha ", "aakanksha1411@gmail.com", "971708045234", "Hi There ", "Need to Book");
		Assert.assertEquals("Message must be between 20 and 2000 characters.", bookerPlatform.getBlankErrorMessage());
	}
	
}


	

