
import org.testng.annotations.Test;

public class Homework17 extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException{
        openLoginUrl();
        enterEmail("faiz.ousmail@testpro.io");
        enterPassword("te$t$tudent1");
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
