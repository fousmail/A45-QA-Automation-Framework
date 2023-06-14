package pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage{

    private By emailField = By.cssSelector("[type='email']");
    private By passwordField = By.cssSelector("[type='password']");
    private By submitButtonLocator  = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver givenDriver){
        super(givenDriver);

    }
    public LoginPage provideEmail(String email){
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        return this;
    }
    public  LoginPage providePassword(String password){
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        return this;

    }
    public LoginPage clickSubmitBtn(){
        driver.findElement(submitButtonLocator).click();
        return this;
    }
    public void login(){
        provideEmail("faiz.ousmail@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmitBtn();
    }
}
