package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage {

    private WebDriver driver;

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
    }

    // ========= UTILITY =============

    protected void clickLivewireSafely(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOf(element));

        js.executeScript(
            "window.scrollTo(0, arguments[0].offsetTop - 150);",
            element
        );

        wait.until(ExpectedConditions.elementToBeClickable(element));

        new Actions(driver)
            .moveToElement(element)
            .pause(Duration.ofMillis(100))
            .click()
            .perform();
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

        WebElement btnSelengkapnya = driver.findElement(cardLocator);
        clickLivewireSafely(btnSelengkapnya);
    }

    public void waitUntilProductDetailDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            detailProducct
        )).click();
    }

    public void productCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // WebElement trigger = wait.until(
        //     ExpectedConditions.elementToBeClickable(productDetails)
        // );
        // clickLivewireSafely(trigger);

        // // 🔥 WAJIB: ambil ulang setelah Livewire render
        // wait.until(ExpectedConditions.stalenessOf(trigger));

        WebElement serviceDetails = wait.until(
            ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(productDetails)
            )
        );

        clickLivewireSafely(serviceDetails);

        wait.until(ExpectedConditions.textToBePresentInElement(
            serviceDetails,
            "Kategori: CPNS / TRYOUT"
        ));

        String sectionText = serviceDetails.getText();

        assertTrue(sectionText.contains("Kategori: CPNS / TRYOUT"));
        assertTrue(sectionText.contains(
            "Berisikan soal try out SKD CPNS untuk persiapan ujian tahun 2026"
        ));

        assertTrue(sectionText.contains("Satu Paket Try Out Online"));
        assertTrue(sectionText.contains("Bebas kerjain berkali-kali"));
        assertTrue(sectionText.contains("Soal-soal terbaru, update banget!"));
        assertTrue(sectionText.contains("Skor langsung keluar, super detail"));
        assertTrue(sectionText.contains("Ada pembahasan lengkap, nggak cuma jawab"));
        assertTrue(sectionText.contains("Bisa gabung grup Lentera CPNS"));
        assertTrue(sectionText.contains("Ranking otomatis update"));
    }

    public void ambilProduct(){
        driver.findElement(checkoutProduct).click();
    }

    public String getProductPriceFromDetailPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement priceElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                labelHarga
            )
        );

        return priceElement.getText().trim(); // Rp 15.000
    }

    public String getCheckoutTotalPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement priceElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                validatedProduct
            )
        );

        return priceElement.getText().trim(); // Rp15.000
    }

    public void bayarSekarang(){
        driver.findElement(bayarSekarangButton).click();
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
