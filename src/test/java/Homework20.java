import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework20 extends BaseTest{


        @Test
        public void deletePlaylist() throws InterruptedException {
            String deletedPlaylistMSG = "Deleted playlist";
            logIn("faiz.ousmail@testpro.io","te$t$tudent1");
            clickSubmit();
            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            openPlaylist();
            clickDeletePlaylistBtn();
            Assert.assertTrue(getDeletedPlaylistMSG().contains(deletedPlaylistMSG));
        }
    }


