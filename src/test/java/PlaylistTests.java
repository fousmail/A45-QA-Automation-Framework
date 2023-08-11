import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

import java.time.Duration;

public class PlaylistTests extends BaseTest{

    LoginPage loginPage = new LoginPage(getDriver());
    HomePage homePage = new HomePage(getDriver());

    @Test(priority = 0)
    public void renamePlaylist(){
        loginPage.login();
        homePage.getUserAvatar();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertTrue(doesPlaylistExist());
    }
    @Test(priority = 1)
    public void deletePlaylist()  {
        String deletedPlaylistMSG = "Deleted playlist";
        loginPage.login();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        openPlaylist();
        clickDeletePlaylistBtn();
        Assert.assertTrue(getDeletedPlaylistMSG().contains(deletedPlaylistMSG));
    }
    @Test(priority = 2)
    public void addSongToPlaylist(){
        logIn("faiz.ousmail@testpro.io","te$t$tudent1");
        clickSubmit();
        searchSong("Episode2");
        clickViewAllButton();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlaylist();
        getNotificationText();
        closeBrowser();

    }
}
