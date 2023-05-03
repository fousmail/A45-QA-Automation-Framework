import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

    public class Homework17 extends BaseTest {

        @Test
        public void addSongToPlaylist() throws InterruptedException{
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


