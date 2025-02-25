import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class SongsTest extends BaseTest {
    @Test
    public void playSongTest () {
        logIn("faiz.ousmail@testpro.io","te$t$tudent1");
        clickSubmit();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        openAllSongs();
        playSong();
        Assert.assertTrue(isSongPlaying());
    }
}
