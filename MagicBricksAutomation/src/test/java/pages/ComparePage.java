package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class ComparePage {

WebDriver driver;

public ComparePage(WebDriver driver){

this.driver = driver;
PageFactory.initElements(driver,this);

}

@FindBy(xpath="//div[contains(@class,'rent')]")
WebElement rentValue;

@FindBy(xpath="//div[contains(@class,'furnishing')]")
WebElement furnishingValue;

public boolean isRentDisplayed(){

return rentValue.isDisplayed();

}

public boolean isFurnishingDisplayed(){

return furnishingValue.isDisplayed();

}

}