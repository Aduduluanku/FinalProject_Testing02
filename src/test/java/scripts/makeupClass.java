package scripts;

import listeners.SimpleListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateClass;
import pages.LoginPage;
import pages.MakeupClass;

@Listeners(SimpleListener.class)
public class makeupClass extends BaseTest{

    @Test
    public void verifyListMenu(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.menuClick();
    }

    @Test
    public void verifyMarkMakeupClass(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.markIconMakeupClass("16/05/2025 10:20");
        makeupClass.saveButton();
    }

    @Test
    public void verifyMarkPaidAbsence(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.markPaidAbsence();
        makeupClass.saveButton();
    }

    @Test
    public void verifyClosePopupMarkPaidAbsence(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.markPaidAbsence();
        makeupClass.closeButton();
    }

    @Test
    public void verifyRollCall(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.rollCall();
        makeupClass.saveButton();
    }

    @Test
    public void verifyFilterStatus(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.filterStatus();
    }

    @Test
    public void verifyFilterMonth(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        MakeupClass makeupClass = new MakeupClass(driver);
        makeupClass.filterMonth();
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
