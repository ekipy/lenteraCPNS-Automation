package steps;

import org.openqa.selenium.WebDriver;

import hooks.Hook;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ExamPage;

public class ExamStep {

    WebDriver driver;
    ExamPage examPage;

    public ExamStep(Hook hook){
        this.driver = hook.getDriver();
        this.examPage = new ExamPage(driver);
    }

    @When("user click tab paket saya")
    public void click_tab_paket_saya(){
        examPage.clickTabPaketSaya();
    }

    @When("system display list of paket has been purchase")
    public void list_paket(){
        examPage.choosePaketBasedOnFilter();
    }
    
    @When("user click buka paket on list of paket")
    public void buka_paket(){
        examPage.bukaPaket();
    }

    @When("click mulai ujian")
    public void mulai_ujian(){
        examPage.mulaiUjian();
    }

    @When("click langsung ujian")
    public void langsung_ujian(){
        examPage.langsungUjian();
    }

    @When("choose option on each question and finish the exam")
    public void choose_option_and_finish(){
        examPage.answerAllQuestionsRandomAndFinish();
    }

    @Then("check the result")
    public void check_result(){
        examPage.validateTotalScoreDisplayed();
    }
}
