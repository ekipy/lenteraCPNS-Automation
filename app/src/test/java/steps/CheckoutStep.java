package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;

import hooks.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;

public class CheckoutStep {

    WebDriver driver;
    CheckoutPage checkoutPage;

    private String productPrice;

    private String normalizePrice(String price) {
        return price.replaceAll("\\s+", "");
    }
    
    public CheckoutStep(Hook hook){
        this.driver = hook.getDriver();
        this.checkoutPage = new CheckoutPage(driver);
    }

    @When("click product {string}")
    public void click_product_will_be_purchase(String productName){
        checkoutPage.chooseProductByName(productName);
        checkoutPage.waitUntilProductDetailDisplayed();
    }

    @And("product details will be display")
    public void product_details_will_be_display(){
        checkoutPage.productCheckout();
        productPrice = checkoutPage.getProductPriceFromDetailPage(); 
        
    }

    @And("user choose to checkout product")
    public void validatePrice(){
        checkoutPage.ambilProduct();
    }

    @When("user will be display total amount")
    public void user_will_be_display_total_amount() {
        String checkoutPrice = checkoutPage.getCheckoutTotalPrice();

        assertEquals(
            normalizePrice(productPrice),
            normalizePrice(checkoutPrice)
        );
    }

    @When("user will see invoice page")
    public void user_will_see_invoice_page() {
        checkoutPage.verifyInvoicePage();
    }

    @When("user click button Bayar Sekarang")
    public void user_click_button_Bayar_Sekarang() {
        checkoutPage.bayarSekarang();
        checkoutPage.switchToMidtransIframe();
    }

    @When("user will be see payment method")
    public void user_will_be_see_payment_method() {
        checkoutPage.verifyPaymentMethodPage();
    }

    @When("user click payment with QR")
    public void user_click_payment_with_QR() {
        checkoutPage.chooseMethodQR();
    }

    @Then("QR successfully display")
    public void QR_successfully_display() {
        checkoutPage.displayQRCode();
    }
}
