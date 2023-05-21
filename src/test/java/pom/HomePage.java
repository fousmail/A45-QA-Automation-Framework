package pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private By userAvatarIcon = By.cssSelector("img.avatar");
    public HomePage(WebDriver chromedriver){
        super(chromedriver);
    }
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }
}
