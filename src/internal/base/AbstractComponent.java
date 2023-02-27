package internal.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponent {

	WebDriver driver ;	

	@FindBy(id = "footer")
	WebElement footerLink;

	public AbstractComponent(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public int getFooterCount()
	{
		List<WebElement> footerLinkList= footerLink.findElements(By.tagName("a"));

		return footerLinkList.size();
	}

	public boolean clickFooters()
	{

		List<WebElement> footerLinkList= footerLink.findElements(By.tagName("a"));
		try {
			for (int i =1 ; i <footerLinkList.size();i++)
			{
				String footerlinkclick = Keys.chord(Keys.CONTROL, Keys.ENTER);
				footerLinkList.get(i).sendKeys(footerlinkclick);			
			}
		}
		catch(IllegalArgumentException ex)
		{
			return false;
		}
		return true;
	}	
}
