package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import components.LiveWireUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage {

    private WebDriver driver;
    private LiveWireUtils liveWire;

    private By selectCardProduct = By.xpath("//div[contains(@class,'card')]");
    private By detailProducct = By.xpath(".//a[contains(normalize-space(),'Selengkapnya')]");
    private By productDetails = By.id("service-details");
    private By labelHarga = By.cssSelector("#service-details div.buy h3.text-danger");
    private By checkoutProduct = By.xpath("//section[@id='service-details']//a[normalize-space()='Ambil Paket Ini, Gaskeun!']");
    private By validatedProduct = By.cssSelector("table tbody tr td:nth-child(3)");
    private By bayarSekarangButton = By.xpath("//button[@id='pay-button']");
    // private By listPaymentMethod = By.xpath("//h2[contains(text(),'Invoice')]");
    private By chooseQRPayment = By.id("other_qris");
    private By qrCodeDisplay = By.cssSelector("img.qr-image");
    //private By checkoutCard = By.xpath("//div[@class='card-wrapper']");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        this.liveWire = new LiveWireUtils(driver);
    }

    // ========= UTILITY =============

    protected void clickLivewireSafely(By locator) {

        int timeout = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS")) ? 20 : 10;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 🔥 ambil element fresh
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
            return;

        } catch (StaleElementReferenceException e) {
            System.out.println("Retry click due to stale element...");
        }
    }

        throw new RuntimeException("Failed to click element due to stale");
    }

    private WebElement waitVisible(By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitClickable(By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ========== ACTIONS ==============

    public List<WebElement> getAllProducts() {
        return driver.findElements(selectCardProduct);
    }
    
    public void chooseProductByName(String nameProduct){
        By cardLocator = By.xpath(
            "//h4[contains(@class,'card-title')]//b[normalize-space()='" + nameProduct + "']" +
            "/ancestor::div[contains(@class,'card')]" +
            "//a[contains(normalize-space(),'Selengkapnya')]"
        );

        clickLivewireSafely(cardLocator);
    }

    public void waitUntilProductDetailDisplayed() {
        liveWire.click(detailProducct);
    }

    public void productCheckout(){
        liveWire.click(productDetails);
        liveWire.waitText(productDetails, "Kategori: CPNS / TRYOUT");
    }

    public void ambilProduct(){
        liveWire.click(checkoutProduct);
    }

    public String getProductPriceFromDetailPage() {
        return liveWire
                .waitVisible(labelHarga)
                .getText()
                .trim(); // Rp 15.000
    }

    public String getCheckoutTotalPrice() {
        return liveWire
                .waitVisible(validatedProduct)
                .getText()
                .trim(); // Rp15.000
    }

    public void bayarSekarang(){
        liveWire.click(bayarSekarangButton);
    }

    public void verifyInvoicePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.titleContains("Invoice"));

        assertTrue(driver.getTitle().contains("Invoice"));
    }

    public void switchToMidtransIframe() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By midtransIframe = By.id("snap-midtrans");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(midtransIframe));
    }

    public void verifyPaymentMethodPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement qris = wait.until(
            ExpectedConditions.visibilityOfElementLocated(chooseQRPayment)
        );

        assertTrue(qris.isDisplayed());
    }

    public void chooseMethodQR(){
        waitClickable(chooseQRPayment).click();
    }

    public void displayQRCode(){
        waitVisible(qrCodeDisplay);
    }

}
