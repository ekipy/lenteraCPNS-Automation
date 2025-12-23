package hooks;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hook {

    public static WebDriver driver;

    @Before
    public void setup() {
        
        //WebDriver duluan memastikan chromeDriver versinya cocok
        WebDriverManager.chromedriver().setup();

        //Membuat chromoption ketika menjalankan chromedriver nanti apa yang disable/enable
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-features=PasswordManagerUI");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--incognito");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        boolean isCI = Boolean.parseBoolean(
            System.getenv().getOrDefault("CI", "false")
        );

        if (isCI) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        //menjalankan chromedriver dengan options yang sudah dibuat diatas
        driver = new ChromeDriver(options);
        if (!isCI) {
            driver.manage().window().maximize();
        }
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
