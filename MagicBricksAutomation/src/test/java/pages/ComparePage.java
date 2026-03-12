package pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class ComparePage {
    WebDriver driver;
    WebDriverWait wait;

    // TODO: Update this with the exact class for the entire property card wrapper
    @FindBy(css = ".mb-srp__card") // Example placeholder
    private List<WebElement> propertyCards;

    // Locators for elements INSIDE the property card (Use relative paths starting with ".")
    // TODO: Inspect the elements in the new tab and update these locators
    private By titleLocator = By.xpath(".//a[contains(@class, 'title')]"); 
    private By priceLocator = By.xpath(".//div[contains(@class, 'price')]");
    private By furnishLocator = By.xpath(".//div[contains(text(), 'FURNISHING')]/following-sibling::div | .//div[contains(@class, 'furnishing')]");

    public ComparePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new tab. Page Title: " + driver.getTitle());
                break;
            }
        }
    }

    
    public void validatePropertyDetails(SoftAssert softAssert) {
        System.out.println("Validating details for " + propertyCards.size() + " shortlisted properties...");
        
        for (int i = 0; i < propertyCards.size(); i++) {
            WebElement card = propertyCards.get(i);
            int propertyNum = i + 1;

            try {
                // Extract Text
                String titleText = card.findElement(titleLocator).getText().trim();
                String priceText = card.findElement(priceLocator).getText().trim();
                String furnishText = card.findElement(furnishLocator).getText().trim();

                System.out.println("Property " + propertyNum + ": " + titleText + " | " + priceText + " | " + furnishText);

                // Soft Assertions - checking they are displayed and not empty
                softAssert.assertNotNull(titleText, "Property " + propertyNum + " title is null");
                softAssert.assertFalse(titleText.isEmpty(), "Property " + propertyNum + " title is empty");

                softAssert.assertNotNull(priceText, "Property " + propertyNum + " price is null");
                softAssert.assertFalse(priceText.isEmpty(), "Property " + propertyNum + " price is empty");

                softAssert.assertNotNull(furnishText, "Property " + propertyNum + " furnish status is null");
                softAssert.assertFalse(furnishText.isEmpty(), "Property " + propertyNum + " furnish status is empty");

            } catch (Exception e) {
                // If an element isn't found, fail the assertion cleanly instead of breaking the loop
                softAssert.fail("Failed to extract details for Property " + propertyNum + ". Error: " + e.getMessage());
            }
        }
    }
}