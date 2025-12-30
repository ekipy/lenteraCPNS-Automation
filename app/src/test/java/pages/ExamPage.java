package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExamPage {

    private WebDriver driver;

    private By tabPaketSaya = By.xpath("//a[normalize-space()='Paket Saya']");
    private By filterPaketSaya = By.xpath("//div[@class='filter-group']");
    private By btnFilterTryOut = By.xpath("//button[normalize-space()='TRYOUT']");
    private By listTryOut = By.xpath("//div[@class='service-box xyz']");
    private By btnBukaPaket = By.xpath("//a[normalize-space()='Buka Paket']");
    private By labelPaket = By.xpath("//h2[normalize-space()='SKD CPNS 2026 - BATCH 1']");
    private By btnMulaiPaket = By.xpath("//a[normalize-space()='Mulai ujian']");
    private By popupMulaiPaket = By.xpath("//div[@class='modal-body']");
    private By mulaiUjian = By.xpath("//a[normalize-space()='Langsung ujian']");
    private By screenTest = By.xpath("//h2[contains(text(),'SKD CPNS 2026 - BATCH 1')]");
    private By livewireLoading = By.cssSelector("[wire\\:loading]");
    private By asnwerOption = By.cssSelector("label.option");
    private By nextQuestion = By.xpath("//button[contains(.,'Selanjutnya')]");
    private By questionNumberButton = By.cssSelector(".pagination-grid button.page");
    private By finishTest = By.xpath("//button[contains(.,'Selesai Ujian')]");
    private By scoreTest = By.xpath("//h1/b");

    public ExamPage(WebDriver driver){
        this.driver = driver;
    }


    // ================== SETUP =======================

    private void waitForLivewire() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
            driver.findElements(livewireLoading).isEmpty()
        );
    }

    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    private WebElement waitVisible(By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitClickable(By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitUntilQuestionReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.question")
        ));

        wait.until(driver ->
            driver.findElements(By.cssSelector("label.option")).size() > 0
        );
    }

    private void clickSafely(WebElement element) {
        scrollTo(element);
        waitForLivewire();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            // LAST RESORT: JS click (legal & stabil)
            ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
        }
    }

    // ================== ACTIONS =====================

    public void clickTabPaketSaya(){
        waitClickable(tabPaketSaya).click();
    }

    public void choosePaketBasedOnFilter(){
        waitVisible(filterPaketSaya).isDisplayed();
        waitClickable(btnFilterTryOut).click();
        waitVisible(listTryOut).isDisplayed();
    }

    public void bukaPaket(){
        waitClickable(btnBukaPaket).click();
        waitVisible(labelPaket).isDisplayed();
    }

    public void mulaiUjian(){
        WebElement btn = waitVisible(btnMulaiPaket);

        new Actions(driver)
            .moveToElement(btn)
            .pause(Duration.ofMillis(300))
            .perform();

        try {
            waitClickable(btnMulaiPaket).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
        }

        waitVisible(popupMulaiPaket);
    }

    public void langsungUjian(){
        waitClickable(mulaiUjian).click();
        waitVisible(screenTest).isDisplayed();
        waitVisible(questionNumberButton);
    }

    public void clickNext() {
        waitClickable(nextQuestion).click();
    }

    public void answerAllQuestionsRandomAndFinish() {

        Random random = new Random();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int totalQuestions =
        wait.until(ExpectedConditions
            .visibilityOfAllElementsLocatedBy(questionNumberButton))
            .size();

        for (int i = 1; i <= totalQuestions; i++) {

            waitForLivewire();
            waitUntilQuestionReady();

            List<WebElement> labels =
                driver.findElements(asnwerOption);

            WebElement chosen =
                labels.get(random.nextInt(labels.size()));

            clickSafely(chosen);

            if (i < totalQuestions) {

                WebElement next =
                    wait.until(ExpectedConditions.presenceOfElementLocated(nextQuestion));

                waitForLivewire();
                clickSafely(next);
            }
        }

        WebElement finish =
            wait.until(ExpectedConditions.presenceOfElementLocated(finishTest));

        waitForLivewire();
        clickSafely(finish);
    }

    public void validateTotalScoreDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions
                .visibilityOfElementLocated(scoreTest))
                .getText();
    }

}
