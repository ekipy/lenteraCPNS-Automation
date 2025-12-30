package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import hooks.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;

public class RegisterStep {

    RegisterPage registerPage;

    @Given("user open website")
    public void user_open_website(){
        Hook.driver.get("https://lenteracpns.com/register?jenis_user=cpns");
        registerPage = new RegisterPage(Hook.driver);
    }

    @When("user fill {string} field with {string}")
    public void user_fill_field_with(String fieldName, String value) {
        if (fieldName.equalsIgnoreCase("email") && value.equalsIgnoreCase("RANDOM")) {
            value = "user_" + System.currentTimeMillis() + "@gmail.com";
        }

        registerPage.fillField(fieldName, value);

    }

    @When("user click button register")
    @And("click button register")
    public void user_click_button_register() {
        registerPage.registerClick();
    }
   

    @Then("system will be display message validation in {string} field")
    public void system_will_be_display_message_validation(String fieldName) {
        
        // Memanggil logika dari Page Object Anda untuk mendapatkan pesan validasi
        String actualMessage = registerPage.getValidationMessageFor(fieldName);

        String expectedMessage = "Please fill out this field."; 
        
        // Assertion menggunakan JUnit 5
        assertEquals(expectedMessage, actualMessage, "Pesan validasi untuk field " + fieldName + " tidak sesuai.");
    }

    @Then("system will be display {string} validation for field email")
    public void system_will_be_display_validation_for_field_email(String expectedMessage) {
        List<String> actualMessage = registerPage.getEmailValidationMessagesList();

        assertTrue(
            actualMessage.contains(expectedMessage),
            () -> "Expected message NOT found.\nExpected: " 
                + expectedMessage + "\nActual: " + actualMessage
        );
    }
}