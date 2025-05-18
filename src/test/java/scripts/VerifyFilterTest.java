package scripts;

import listeners.SimpleListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

@Listeners(SimpleListener.class)
public class VerifyFilterTest extends BaseTest {
    @Test
    public void verifyFilterDisplay() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);


        //check filter
        loginPage.login("testadmin","test1234");

        //writing list you wanna click
        String[] filters = {"Ngày sinh", "Phụ huynh", "Nguồn giới thiệu", "Ghi chú"};

        homePage.verifyFilterDisplay(filters);


    }
}