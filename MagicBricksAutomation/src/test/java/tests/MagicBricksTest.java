package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.SearchPage;

public class MagicBricksTest extends BaseTest {

@Test

public void propertyShortlistTest(){
	HomePage home = new HomePage(driver);

	home.searchCity("Bangalore");
	SearchPage searchPage = new SearchPage(driver);
	//searchPage.scrollUntilPropertiesLoad();
	searchPage.scrollPage();
    
	


}

}