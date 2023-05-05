import org.testng.Assert;
import org.testng.annotations.Test;
public class Homework18 extends BaseTest {
    @Test
    public void playSongTest () throws InterruptedException {
        logIn("faiz.ousmail@testpro.io","te$t$tudent1");
        clickSubmit();
        Thread.sleep(5000);
        openAllSongs();
        playSong();
        Assert.assertTrue(isSongPlaying());
    }
}
