package components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LiveWireUtils {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LiveWireUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    /* ==========================
       SAFE CLICK (ANTI STALE)
       ========================== */
    public void click(By locator) {

        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
                );

                js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
                );

                js.executeScript("arguments[0].click();", element);
                waitForLivewire();
                return;

            } catch (StaleElementReferenceException e) {
                System.out.println("Retry click due to stale...");
            }
        }

        throw new RuntimeException("Failed clicking element: " + locator);
    }

    /* ==========================
       WAIT TEXT (LIVEWIRE SAFE)
       ========================== */
    public void waitText(By locator, String text) {
        wait.until(
            ExpectedConditions.refreshed(
                ExpectedConditions.textToBePresentInElementLocated(
                    locator, text
                )
            )
        );
    }

    /* ==========================
       WAIT VISIBLE (FRESH)
       ========================== */
    public WebElement waitVisible(By locator) {
        return wait.until(
            ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(locator)
            )
        );
    }

    /* ==========================
       WAIT LIVEWIRE FINISH
       ========================== */
    public void waitForLivewire() {
        wait.until(driver ->
            (Boolean) js.executeScript(
                "return window.Livewire === undefined || " +
                "Livewire.all().every(c => !c.isLoading)"
            )
        );
    }
}
