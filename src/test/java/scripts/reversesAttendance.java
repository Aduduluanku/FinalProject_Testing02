package scripts;

import listeners.SimpleListener;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateClass;
import pages.LoginPage;
import pages.MakeupClass;
import pages.ReversesAttendance;

@Listeners(SimpleListener.class)
public class reversesAttendance extends BaseTest{

    @Test
    public void verifyListMenu(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        ReversesAttendance reversesAttendance = new ReversesAttendance(driver);
        reversesAttendance.menuClick();
    }

    @Test
    public void verifyPopupReversesAttendance(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        ReversesAttendance reversesAttendance = new ReversesAttendance(driver);
        reversesAttendance.popupReversesAttendance();
    }

    @Test
    public void verifyCloseButton(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        ReversesAttendance reversesAttendance = new ReversesAttendance(driver);
        reversesAttendance.popupReversesAttendance();
        reversesAttendance.closeButton();
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
