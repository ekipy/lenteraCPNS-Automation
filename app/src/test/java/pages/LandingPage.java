package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    private WebDriver driver;

    private By aksesBtn = By.xpath("//a[normalize-space()='Masuk / Daftar']");
    private By opsiProgram =  By.xpath("//div[@class='modal-body']");
    private By registrasiButton = By.xpath("//b[normalize-space()='Registrasi Disini']");

    public LandingPage(WebDriver driver){
        this.driver = driver;
    }

    public void masukordaftar(){
        driver.findElement(aksesBtn).click();
    }

    public void pilihJenisUser(){
        driver.findElement(opsiProgram).click();
    }
    
    public boolean tampilanLogin(){
        return driver.findElement(registrasiButton).isDisplayed();
    }
}
