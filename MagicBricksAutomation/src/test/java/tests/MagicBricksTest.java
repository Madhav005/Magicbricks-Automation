package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.ComparePage;
import pages.HomePage;
import pages.SearchPage;

public class MagicBricksTest extends BaseTest {

    @Test
    public void propertyShortlistTest() throws InterruptedException {
    	
    	SoftAssert softAssert = new SoftAssert();
        HomePage home = new HomePage(driver);
        home.searchCity("Bangalore");
        
        SearchPage searchPage = new SearchPage(driver);
        
        // searchPage.scrollUntilPropertiesLoad();
        searchPage.scrollPage();
        searchPage.scrollToTop();
        
        
        searchPage.shortlistFirstThree();
        
        searchPage.clickShortlistHeaderButton();
        
        searchPage.navigateToShortlistedProperties();
        
        ComparePage comparePage = new ComparePage(driver);
        comparePage.switchToNewTab();

       
        
       comparePage.validatePropertyDetails(softAssert);
        
        // 5. Assert all collected soft assertions
        softAssert.assertAll();
    }

}