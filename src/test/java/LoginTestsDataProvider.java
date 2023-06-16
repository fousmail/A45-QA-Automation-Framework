import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;


public class LoginTestsDataProvider extends BaseTest {


    @Test(dataProvider = "InvalidLoginCredentials",dataProviderClass = BaseTest.class,enabled = true,priority = 0, description = "Login with invalid email and password")
    public void LoginInvalidCredentialsTest(String email,String password){

//       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(getDriver());
        String url = "https://qa.koel.app/";
        loginPage.provideEmail(email).providePassword(password).clickSubmitBtn();
        Assert.assertEquals(getDriver().getCurrentUrl(),url);

    }

    @Test(enabled = true,priority = 1,description = "Login with valid email and valid password")
    public  void LoginValidEmailPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        //     loginPage.login();
        loginPage.provideEmail("faiz.ousmail@testpro.io").providePassword("te$t$tudent1").clickSubmitBtn();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }


}
