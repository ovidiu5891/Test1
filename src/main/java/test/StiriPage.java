package test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class StiriPage {

	@FindBy(xpath = "//*[@id='site-header']/div/nav/ul/li[1]/a/spand[1]")
	@CacheLookup
	public WebElement stiriBtn;

	



}
