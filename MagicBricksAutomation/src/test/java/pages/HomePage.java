package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class HomePage {

WebDriver driver;

public HomePage(WebDriver driver){
this.driver = driver;
PageFactory.initElements(driver,this);
}

@FindBy(id="keyword")
WebElement cityInput;

@FindBy(xpath="//button[contains(text(),'Search')]")
WebElement searchButton;

public void searchCity(String city){

cityInput.sendKeys(city);
searchButton.click();

}

}