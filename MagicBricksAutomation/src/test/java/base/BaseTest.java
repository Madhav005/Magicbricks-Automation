package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

public static WebDriver driver;

@BeforeMethod
public void setup(){

driver = new ChromeDriver();
driver.manage().window().maximize();
driver.get("https://www.magicbricks.com");

}

@AfterMethod
public void tearDown(){

//driver.quit();

}

}