package scripts;

import listeners.SimpleListener;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

@Listeners(SimpleListener.class)
public class VerifySearchTest extends BaseTest {
    @Test
    public void VerifyInputSearchTest() throws InterruptedException {
        Actions action = new Actions(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("testadmin","test1234");
        //search box you wanna search
        homePage.VerifySearchTest("Tên HV", "Việt Cường");
        homePage.VerifyDropdownSearchTest("Trạng thái", "Nợ phí");

    }
}