package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    
    @FindBy(className = "mb-srp__list")
    private List<WebElement> propertyCards;

    
    @FindBy(css = ".mb-srp__card__photo__fig--heart") 
    private List<WebElement> shortlistIcons;

    @FindBy(xpath="//div[contains(@class,'mb-srp__card')]")
    List<WebElement> properties;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void shortlistFirstThree()
    {

    for(int i=0;i<3;i++)
    {
    properties.get(i).click();
    }

    }
     
   
    
    public void scrollUntilPropertiesLoad()
    {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

    while(true)
    {

    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    try {
    Thread.sleep(2000);   // wait for new properties to load
    } catch (InterruptedException e) {
    e.printStackTrace();
    }

    long newHeight = (long) js.executeScript("return document.body.scrollHeight");

    if(newHeight == lastHeight)
    {
    break;
    }
    lastHeight = newHeight;
    }
    }
    
    public void scrollPage()
    {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    for(int i=0; i<5; i++)
    {

    js.executeScript("window.scrollBy(0,2000)");

    try {
    Thread.sleep(2000);
    } catch(Exception e){}

    }

    }
}