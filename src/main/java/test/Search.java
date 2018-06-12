package test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class Search extends PageObjectsPage<Search> {
	


	@FindBy(xpath = "//*[@id='site-header']/div/nav/ul/li[1]/div/div/div/div[2]/div/div[1]/a[1]")
	@CacheLookup
	public WebElement social;

	public void searchArticle() {
		
		social.click();
	}

}
