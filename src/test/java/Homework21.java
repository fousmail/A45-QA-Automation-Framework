import org.testng.Assert;
import org.testng.annotations.Test;
public class Homework21 extends BaseTest {

    @Test
    public void renamePlaylist(){
        logIn("faiz.ousmail@testpro.io","te$t$tudent1");
        clickSubmit();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertTrue(doesPlaylistExist());
    }
}
