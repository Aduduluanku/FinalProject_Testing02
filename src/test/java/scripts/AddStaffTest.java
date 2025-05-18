package scripts;

import listeners.SimpleListener;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;



@Listeners(SimpleListener.class)
public class AddStaffTest extends BaseTest{
    @Test
    public void addingTest() throws InterruptedException {

        Actions actions = new Actions(driver);

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        logger.info("Start logging.....");
        loginPage.login("testadmin", "test1234");
        logger.info("Login Successfully!!!");

        homePage.openAddStaff();
        logger.info("Start logging.....");
        homePage.addStaff("Nguyen Van", "To", "nguyenvanto1"
        , "latuidayne", "0909090909");
        logger.info("Created Successfully!!!");

        loginPage.login("nguyenvanto1", "latuidayne");

        logger.info("On waiting...");
        Thread.sleep(5000);
        logger.info("Done wait!!!");




    }
}

