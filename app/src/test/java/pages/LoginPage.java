package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

    private WebDriver driver;

    private By jenisUser = By.xpath("//select[@id='jenis_user']");
    private By emailFieldLogin = By.xpath("//input[@id='email']");
    private By passFieldLogin = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//b[normalize-space()='Masuk Akun']");
    private By dashboardScreen = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > section:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1)");


    public LoginPage (WebDriver driver){
        this.driver = driver;
    }

    public void chooseJenisUser(String userType) {
        WebElement dropdown = driver.findElement(jenisUser);
        Select select = new Select(dropdown);
        select.selectByValue(userType);  // contoh: "pppk"
    }

    public void fillEmailField(String email){
        driver.findElement(emailFieldLogin).sendKeys(email);
    }

    public void fillPassField(String password){
        driver.findElement(passFieldLogin).sendKeys(password);
    }

    public void loginClick(){
        driver.findElement(loginButton).click();
    }

    public boolean isDashboardPageVisible(){
        return driver.findElement(dashboardScreen).isDisplayed();
    }

    
}
