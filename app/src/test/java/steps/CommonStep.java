package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;

import hooks.Hook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import pages.RegisterPage;

public class CommonStep {
    WebDriver driver;
    LoginPage loginPage;
    RegisterPage registerPage;

    public CommonStep(Hook hooks) {
        this.driver = hooks.getDriver();
        this.loginPage = new LoginPage(hooks.getDriver());
        this.registerPage = new RegisterPage(hooks.getDriver());
    }
    
    @Given("user initiated to login")
    public void user_initiated_to_login(){
        driver.get("https://lenteracpns.com/login");
        loginPage.chooseJenisUser("cpns");
        loginPage.fillEmailField("test3@gmail.com");
        loginPage.fillPassField("12345678");
        loginPage.loginClick();
    }

    @Given("user initiated to login for exam")
    public void user_initiated_to_login_exam(){
        driver.get("https://lenteracpns.com/login");
        loginPage.chooseJenisUser("cpns");
        loginPage.fillEmailField("test2@gmail.com");
        loginPage.fillPassField("test1234");
        loginPage.loginClick();
    }

    @Then("system will be display screen dashboard")
    public void system_will_be_display_screen_dashboard() {
        assertTrue(registerPage.isDashboardSuccess());
    }
}
