package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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
    private By listPaymentMethod = By.id("header");
    private By chooseQRPayment = By.id("other_qris");
    private By qrCodeDisplay = By.cssSelector("img.qr-image");
    //private By checkoutCard = By.xpath("//div[@class='card-wrapper']");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        this.liveWire = new LiveWireUtils(driver);
    }

    // ========== ACTIONS ==============

    public List<WebElement> getAllProducts() {
        return driver.findElements(selectCardProduct);
    }
    
    public void focusProductByName(String nameProduct){
        By productCard = By.xpath(
        "//div[contains(@class,'card')][.//h4[contains(normalize-space(),'" + nameProduct + "')]]"
    );
        liveWire.waitVisible(productCard);
    }

    public void clickProductDetails() {
        liveWire.click(detailProducct);
    }

    public void productCheckout(){
        liveWire.click(productDetails);
        liveWire.waitText(productDetails, "Kategori: CPNS / TRYOUT");
    }

    public void ambilProduct(){
        driver.findElement(checkoutProduct).click();
    }

    public String getProductPriceFromDetailPage() {
        liveWire.waitForLivewire();
        
        return liveWire
                .waitVisible(labelHarga)
                .getText()
                .trim(); // Rp 15.000
    }

    public String getCheckoutTotalPrice() {
        liveWire.waitForLivewire();

        return liveWire
                .waitVisible(validatedProduct)
                .getText()
                .trim(); // Rp15.000
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
        liveWire.waitVisible(listPaymentMethod);
    }

    public void chooseMethodQR(){
        driver.findElement(chooseQRPayment).click();
    }

    public void displayQRCode(){
        liveWire.waitVisible(qrCodeDisplay);
    }

}
