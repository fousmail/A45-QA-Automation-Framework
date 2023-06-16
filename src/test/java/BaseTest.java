import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    WebDriverWait wait;
    String url = "";
    WebDriver driver ;
    Actions actions = null;
    String newPlaylistName = "Updated playlist";
    static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
        // added static keyword which will allow storing and retrieving the instance for each thread.
    @BeforeSuite
    static void setupClass() {
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
    }
    @DataProvider(name="InvalidLoginCredentials")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"Invalid@mail.com","InvalidPassword"},
                {"demo@class.com","InvalidPassword"},
                {"Invalid@mail.com","te$t$tudent"},
                {"demo@class.com",""},
                {"","te$t$tudent"},
                {"",""}
        };
    }
    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) throws MalformedURLException {

        threadDriver.set(pickBrowser(System.getProperty("browser")));

        wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
        actions = new Actions(getDriver());

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        url = BaseURL;
        navigateToPage();
    }



    public void navigateToPage(){getDriver().get(url);}

    @AfterMethod
    public void closeBrowser() {
//          getDriver().quit();
          threadDriver.get().close();
          threadDriver.remove();

   }
    public static WebDriver getDriver() {
        return threadDriver.get();
    }
    // This getDriver() method returns the current instance of WebDriver associated with the current thread.
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.86.102:4444";
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            }
            case "MicrosoftEdge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
                eOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return driver = new EdgeDriver(eOptions);
            }
            case "grid-edge" -> {
                caps.setCapability("browserName","MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            }
            case "grid-firefox" -> {
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            }
            case "grid-chrome" -> {
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            }
            case "lambda" -> {
                return lambdaTest();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return driver = new ChromeDriver(options);
            }
        }
    }

    public static WebDriver lambdaTest() throws MalformedURLException {
        String username = "faiz.ousmail";
        String accessToken = "3WtNG6vLGJdOmex6MtmkCYyjdLB7vOQvAhBd58p3fDVtwx3uPS";
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("114.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", accessToken);
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(new URL(hubURL), browserOptions);

    }

    public void openAllSongs(){
        WebElement clickOpenAllSongs = getDriver().findElement(By.cssSelector("a[href='#!/songs']"));
        clickOpenAllSongs.click();
    }
    public void playSong(){
        WebElement clickPlaySongBTN = getDriver().findElement(By.xpath("//span[@data-testid='play-btn']"));
        clickPlaySongBTN.click();
    }
    public boolean isSongPlaying(){
        WebElement soundBar = getDriver().findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
    public void logIn(String email, String password) {
        WebElement emailField = getDriver().findElement(By.cssSelector("input[type='email']"));
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("input[type='email']")))).click();
        emailField.clear();
        emailField.sendKeys(email);
        WebElement passwordField = getDriver().findElement(By.cssSelector("input[type='password']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']"))).click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickSubmit() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']"))); //driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();
    }
    public void searchSong(String songTitleKeyword) {
        WebElement searchField = getDriver().findElement(By.cssSelector("input[type='search']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='search']"))).click();
        searchField.sendKeys(songTitleKeyword);
    }
    public void clickViewAllButton()  {
        WebElement viewAllSearchResults = getDriver().findElement(By.cssSelector("div.results h1 > button"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.results h1 > button")));
        viewAllSearchResults.click();
    }
    public void selectFirstSongResult() {
        WebElement firstSongResult = getDriver().findElement(By.cssSelector("section#songResultsWrapper div.song-list-wrap div.virtual-scroller.scroller div.item-container  table.items tr.song-item td.title"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("section#songResultsWrapper div.song-list-wrap div.virtual-scroller.scroller div.item-container  table.items tr.song-item td.title")));
        firstSongResult.click();
    }
    public void clickAddToBtn()  {
        WebElement addToBtn = getDriver().findElement(By.cssSelector("section#songResultsWrapper button.btn-add-to"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("section#songResultsWrapper button.btn-add-to")));
        addToBtn.click();
    }
    public void choosePlaylist()  {
        WebElement playListElement = getDriver().findElement(By.xpath("//section[@id='songResultsWrapper']// li[contains(text(),'TEST')]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']// li[contains(text(),'TEST')]")));
        playListElement.click();
    }
    public void getNotificationText() {
        WebElement notificationElement = getDriver().findElement(By.cssSelector("div.success.show"));
        Assert.assertEquals(notificationElement.getText(), "Added 1 song into \"TEST.\"");
    }
    public void openPlaylist(){
        WebElement playList = getDriver().findElement(By.cssSelector(".playlist:nth-child(3)"));
        playList.click();
    }
    public void clickDeletePlaylistBtn(){
        WebElement deleteBTN = getDriver().findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        deleteBTN.click();
    }
    public String getDeletedPlaylistMSG(){
        WebElement deletionMSG = getDriver().findElement(By.cssSelector("div.success.show"));
        return deletionMSG.getText();
    }

    public void doubleClickPlaylist(){
        WebElement playListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playListElement).perform();
    }

    public void enterNewPlaylistName(){
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist(){
        WebElement playListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+newPlaylistName+"']")));
        return playListElement.isDisplayed();
    }
}