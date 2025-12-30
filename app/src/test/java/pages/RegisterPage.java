package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;

    private By namaField = By.xpath("//input[@id='name']");
    private By emailField = By.xpath("//input[@id='email']");
    private By noWhatsapp = By.xpath("//input[@id='whatsapp']");
    private By passField = By.xpath("//input[@id='password']");
    private By konfirmPass = By.xpath("//input[@id='password_confirmation']");
    private By labelEmailValid = By.xpath("//li[normalize-space()='The email field must be a valid email address.']");
    private By registBtn = By.xpath("//button[@type='submit']");
    private By masukBtn = By.xpath("//b[normalize-space()='Masuk Akun Disini']");
    // private By dashboard = By.id("navmenu");
    private By emailErrors = By.xpath("//li[contains(text(),'email')]");
    private By emailValidation = By.cssSelector("ul.text-red-600 li");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }
    
    public void registerClick(){
        driver.findElement(registBtn).click();
    }

    public boolean isValidationEmail(){
        return driver.findElement(labelEmailValid).isDisplayed();
    }

    public void loginClick(){
        driver.findElement(masukBtn).click();
    }

    public void fillField(String fieldName, String value) {
        By locator;

        switch (fieldName.toLowerCase()) {
            case "nama":
                locator = namaField;
                break;
            case "email":
                locator = emailField;
                break;
            case "password":
                locator = passField;
                break;
            case "konfirmasi":
                locator = konfirmPass;
                break;
            case "whatsapp":
                locator = noWhatsapp;
                break;    
            default:
                throw new IllegalArgumentException("Field name not found: " + fieldName);
        }

        driver.findElement(locator).sendKeys(value);
    }


    public String getValidationMessageFor(String fieldName) {
        By locator;
        switch (fieldName.toLowerCase()) {
            case "email":
                locator = emailField;
                break;
            case "whatsapp":
                locator = noWhatsapp;
                break;
            case "password":
                locator = passField;
                break;
            case "konfirmasi":
                locator = konfirmPass;
                break;
            case "nama":
                locator = namaField;
                break;    
            default:
                throw new IllegalArgumentException("Field name not found: " + fieldName);
        }
        
        // Menggunakan JavascriptExecutor melalui driver yang diteruskan
        return (String) ((JavascriptExecutor) driver)
            .executeScript("return arguments[0].validationMessage;", 
                driver.findElement(locator));
    }

    public List<String> getEmailValidationMessagesList() {
        return driver.findElements(emailErrors).stream()
                 .map(WebElement::getText)
                 .toList();
    }

    // public List<String> getEmailValidationMessagesList() {
    //     List<WebElement> elements = driver.findElements(emailValidation);

    //     return elements.stream()
    //             .map(WebElement::getText)
    //             .map(String::trim)
    //             .collect(Collectors.toList());
    // }

    public boolean isDashboardSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        return true;
    }

}
