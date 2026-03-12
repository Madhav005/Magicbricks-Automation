package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class MagicBricksTest extends BaseTest {

@Test

public void propertyShortlistTest(){
	HomePage home = new HomePage(driver);

	home.searchCity("Bangalore");
    
	


}

}