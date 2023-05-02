import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest {
    @Test
    public void playSongTest () throws InterruptedException {
        openLoginUrl();
        enterEmail("faiz.ousmail@testpro.io");
        enterPassword("te$t$tudent1");
        clickSubmit();
        Thread.sleep(5000);
        playSong();
        Assert.assertTrue(isSongPlaying());


    }

}
