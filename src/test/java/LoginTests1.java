import org.testng.Assert;
import org.testng.annotations.Test;
import pom.LoginPage;
import pom.HomePage;

public class LoginTests1 extends BaseTest{
    String url = "https://qa.koel.app/";
    @Test
    public void loginEmptyEmailTest() {
        // GIVEN

        LoginPage loginPage = new LoginPage(getDriver());

        // WHEN
        loginPage.provideEmail("")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        Assert.assertEquals(getDriver().getCurrentUrl(),url);
    }

    @Test
    public void loginWrongPasswordTest() {
        // GIVEN
        LoginPage loginPage = new LoginPage(getDriver());

        // WHEN
        loginPage.provideEmail("demo@class.com")
                .providePassword("te$t123")
                .clickSubmitBtn();

        // THEN
        Assert.assertEquals(getDriver().getCurrentUrl(),url);
    }

    @Test
    public void loginEmptyPasswordTest() {
        // GIVEN
        LoginPage loginPage = new LoginPage(getDriver());

        // WHEN
        loginPage.provideEmail("demo@class.com")
                .providePassword("")
                .clickSubmitBtn();

        // THEN
        Assert.assertEquals(getDriver().getCurrentUrl(),url);
    }

    @Test
    public void loginWrongEmailTest() {
        // GIVEN
        LoginPage loginPage = new LoginPage(getDriver());

        // WHEN
        loginPage.provideEmail("wrongemail@class.com")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        // THEN
        Assert.assertEquals(getDriver().getCurrentUrl(),url);
    }

    @Test
    public void loginValidEmailPasswordTest() {
        // GIVEN
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        // WHEN
        loginPage.provideEmail("demo@class.com");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmitBtn();

        // THEN
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
}




