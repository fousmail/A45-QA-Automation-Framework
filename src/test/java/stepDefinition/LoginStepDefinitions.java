package stepDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class LoginStepDefinitions {

        WebDriver driver;
        WebDriverWait wait;
        @Before
        public void openBrowser() {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
       @After
       public void closeBrowser(){
           driver.quit();
       }

            @Given ("I open login page")
            public void openLoginPage(){ driver.get("https://qa.koel.app/");}

            @When("I enter my email {string}")
                    public void iEnterMyEmail(String email) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']"))).sendKeys(email);
            }

            @And("I enter my password {string}")
                    public void iEnterMyPassword(String password) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).sendKeys(password);
            }

            @And("I click submit")
                    public void clickSubmit() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']"))).click();
        }
            @Then("I am logged in")
                    public void user_Is_Logged_in() {
                Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
                    }
    }



