import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest{
    @Test
    public void deletePlaylist() throws InterruptedException {
        String deletedPlaylistMSG = "Deleted playlist";
        logIn("faiz.ousmail@testpro.io","te$t$tudent1");
        clickSubmit();
        Thread.sleep(2000);
        openPlaylist();
        clickDeletePlaylistBtn();
        Assert.assertTrue(getDeletedPlaylistMSG().contains(deletedPlaylistMSG));
    }
}
