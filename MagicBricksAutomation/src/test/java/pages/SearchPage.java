package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;

public class SearchPage {

WebDriver driver;

public SearchPage(WebDriver driver){

this.driver = driver;
PageFactory.initElements(driver,this);

}

@FindBy(xpath="//div[contains(@class,'mb-srp__card')]")
List<WebElement> propertyCards;

@FindBy(xpath="//button[contains(text(),'Compare')]")
WebElement compareButton;

public void shortlistProperties(){



}
}