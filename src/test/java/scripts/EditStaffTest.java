package scripts;

import listeners.SimpleListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

@Listeners(SimpleListener.class)
public class EditStaffTest extends BaseTest {
    @Test
    public void editTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        logger.info("Start logging.....");
        loginPage.login("testadmin", "test1234");
        logger.info("Login Successfully!!!");

        logger.info("Start logging.....");
        homePage.editStaff("Nguyen Thanh Tran", "Longg", "luan1234"
                , "long1234", "0909090909");
        logger.info("Edited Successfully!!!");

        loginPage.login("luan1234", "long1234");


        logger.info("On waiting...");
        Thread.sleep(5000);
        logger.info("Done wait!!!");
    }
}
