package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(className = "mb-srp__card__sort--icon")
    private List<WebElement> shortlistHearts;
    
    
    @FindBy(xpath = "//input[contains(@placeholder, 'Name')]") 
    private WebElement nameField;
    

 
    @FindBy(className = "mb-header__main__shortlist__cta")
    private WebElement shortlistHeaderBtn;

   
    @FindBy(xpath = "//*[contains(text(), 'View Your Shortlisted Properties')]")
    private WebElement viewShortlistTooltipLink;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the first three hearts. Pauses for manual login after the first click.
     */
    public void shortlistFirstThree() {
        int limit = Math.min(3, shortlistHearts.size());

        for (int i = 0; i < limit; i++) {
            try {
                WebElement heart = shortlistHearts.get(i);
                
                // Use JS Click to avoid "ElementClickIntercepted" 
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", heart);
                
                System.out.println("Heart #" + (i + 1) + " clicked.");

                // After the first heart, the login popup appears. Wait for manual login.
                if (i == 0) {
                    waitForManualLogin();
                }
                
                Thread.sleep(1500); // Give time for the UI to settle before the next click
                
            } catch (Exception e) {
                System.out.println("Could not click heart at index " + i + ": " + e.getMessage());
            }
        }
    }
    
    
    public void waitForManualLogin() {
        try {
            System.out.println("Waiting for manual login. Please complete the login in the browser...");
            
            // Create a longer wait specifically for manual human action (e.g., 90 seconds)
            WebDriverWait manualWait = new WebDriverWait(driver, Duration.ofSeconds(50));
            
            // 1. Wait briefly for the popup to appear so we know it triggered
            manualWait.until(ExpectedConditions.visibilityOf(nameField));
            
            // 2. Pause execution UNTIL the popup disappears (which happens after successful manual login)
            manualWait.until(ExpectedConditions.invisibilityOf(nameField));
            
            System.out.println("Manual login complete! Popup closed. Resuming automation...");
            
            // Give a small buffer for the page to refresh or settle after logging in
            Thread.sleep(3000); 
            
        } catch (Exception e) {
            System.out.println("Manual login wait timed out or failed: " + e.getMessage());
        }
    }

    // --- Scroll Methods ---
    
    public void scrollUntilPropertiesLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while(true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if(newHeight == lastHeight) break;
            lastHeight = newHeight;
        }
    }

    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0; i<5; i++) {
            js.executeScript("window.scrollBy(0,2000)");
            try { Thread.sleep(2000); } catch(Exception e){}
        }
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,0)");
    }
    
    public void clickShortlistHeaderButton() {
        try {
            System.out.println("Waiting for the Shortlist header button to be clickable...");
            wait.until(ExpectedConditions.elementToBeClickable(shortlistHeaderBtn));
            
            try {
                shortlistHeaderBtn.click();
            } catch (Exception e) {
                // Fallback to JS click if a notification or overlay intercepts the standard click
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", shortlistHeaderBtn);
            }
            
            System.out.println("Clicked on the Shortlist header button.");
            Thread.sleep(2000); // Give it a moment to navigate or open the dropdown
            
        } catch (Exception e) {
            System.out.println("Failed to click the Shortlist header button: " + e.getMessage());
        }
    }
    
    public void navigateToShortlistedProperties() {
        try {
            System.out.println("Hovering over the Shortlist header button...");
            
            // Use Actions class to hover over the shortlist button
            Actions actions = new Actions(driver);
            actions.moveToElement(shortlistHeaderBtn).perform();
            
            // If hover doesn't trigger the dropdown, uncomment the click below
            // shortlistHeaderBtn.click();
            
            System.out.println("Waiting for the tooltip link to appear...");
            wait.until(ExpectedConditions.visibilityOf(viewShortlistTooltipLink));
            wait.until(ExpectedConditions.elementToBeClickable(viewShortlistTooltipLink));
            
            System.out.println("Clicking 'View Your Shortlisted Properties'...");
            try {
                viewShortlistTooltipLink.click();
            } catch (Exception e) {
                // Fallback to JS click if a notification or overlay intercepts
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", viewShortlistTooltipLink);
            }
            
            System.out.println("Successfully navigated to the Shortlist/Compare page.");
            Thread.sleep(2000); // Give the new page time to load
            
        } catch (Exception e) {
            System.out.println("Failed to click the tooltip: " + e.getMessage());
        }
    }
}