package pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage{

    private By emailField = By.cssSelector("[type='email']");
    private By passwordField = By.cssSelector("[type='password']");
    private By submitButtonLocator  = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver chromeDriver){
        super(chromeDriver);

    }
    public void provideEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    public  void providePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickSubmitBtn(){
        driver.findElement(submitButtonLocator).click();
    }
    public void login(){
        provideEmail("faiz.ousmail@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmitBtn();
    }
}
