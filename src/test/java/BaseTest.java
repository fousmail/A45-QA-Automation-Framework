import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    String url = "";
    public static WebDriver driver = null;


    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @DataProvider(name="InvalidLoginCredentials")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"Invalid@mail.com","InvalidPassword"},
                {"demo@class.com",""},
                {"",""}
        };

    }


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void openLoginUrl(String BaseURL) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        url = BaseURL;
        driver.get(BaseURL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }




    public void playSong(){
        WebElement play1Song = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        WebElement playNextSong = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        play1Song.click();
        playNextSong.click();
    }
    public boolean isSongPlaying(){
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
    public void logIn(String email, String password) {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    public void clickSubmit() {
        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();
    }

    public void searchSong(String songTitleKeyword) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("input[type='search']"));
        searchField.sendKeys(songTitleKeyword);

        Thread.sleep(2000);
    }

    public void clickViewAllButton() throws InterruptedException {
        WebElement viewAllSearchResults = driver.findElement(By.cssSelector("div.results h1 > button"));
        viewAllSearchResults.click();
        Thread.sleep(2000);
    }

    public void selectFirstSongResult() throws InterruptedException {
        WebElement firstSongResult = driver.findElement(By.cssSelector("section#songResultsWrapper div.song-list-wrap div.virtual-scroller.scroller div.item-container  table.items tr.song-item td.title"));
        firstSongResult.click();
        Thread.sleep(2000);
    }

    public void clickAddToBtn() throws InterruptedException {
        WebElement addToBtn = driver.findElement(By.cssSelector("section#songResultsWrapper button.btn-add-to"));
        addToBtn.click();
        Thread.sleep(2000);
    }

    public void choosePlaylist() throws InterruptedException {
        WebElement playListElement = driver.findElement(By.xpath  ("//section[@id='songResultsWrapper']// li[contains(text(),'TEST')]"));
        playListElement.click();
        Thread.sleep(2000);
    }

    public void getNotificationText() {
        WebElement notificationElement = driver.findElement(By.cssSelector("div.success.show"));
        Assert.assertEquals(notificationElement.getText(), "Added 1 song into \"TEST.\"");


    }
    public void openPlaylist(){
        WebElement playList = driver.findElement(By.cssSelector(".playlist:nth-child(4)"));
        playList.click();
    }
    public void clickDeletePlaylistBtn(){
        WebElement deleteBTN = driver.findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        deleteBTN.click();
    }
    public String getDeletedPlaylistMSG(){
        WebElement deletionMSG = driver.findElement(By.cssSelector("div.success.show"));
        return deletionMSG.getText();
    }
}