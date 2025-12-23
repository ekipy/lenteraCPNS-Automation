package steps;

import hooks.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStep {
    LoginPage loginPage;

    @Given("user open the website")
    public void user_open_the_website(){
        Hook.driver.get("https://lenteracpns.com/login");
        loginPage = new LoginPage(Hook.driver);
    }

    @When("user choose jenis user {string}")
    public void user_choose_jenis_user(String jenisUserr){
        loginPage.chooseJenisUser(jenisUserr);
    }

    @And("user input email with {string}")
    public void user_input_email(String email){
        loginPage.fillEmailField(email);
    }

    @And("user input password with {string}")
    public void user_input_password(String password){
        loginPage.fillPassField(password);
    }

    @When("click button login")
    public void click_button_login() {
        loginPage.loginClick();
    }
}
