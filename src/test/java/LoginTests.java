import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;
import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public static void LoginValidEmailPasswordTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
   //     loginPage.login();
     loginPage.provideEmail("faiz.ousmail@testpro.io").providePassword("te$t$tudent1").clickSubmitBtn();
//
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }


}
