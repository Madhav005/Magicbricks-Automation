package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

WebDriver driver;
WebDriverWait wait;

public HomePage(WebDriver driver)
{
this.driver = driver;
PageFactory.initElements(driver, this);
wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}

@FindBy(id="tabRENT")
WebElement rentTab;

@FindBy(id="keyword")
WebElement cityInput;

@FindBy(xpath="//div[contains(@class,'mb-search__btn')]")
WebElement searchButton;

public void searchCity(String city)
{

// click RENT tab first
wait.until(ExpectedConditions.elementToBeClickable(rentTab));
rentTab.click();

// enter city
wait.until(ExpectedConditions.visibilityOf(cityInput));
cityInput.clear();
cityInput.sendKeys(city);

// click search
wait.until(ExpectedConditions.elementToBeClickable(searchButton));
searchButton.click();



}

}