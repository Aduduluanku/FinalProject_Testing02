package scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CreateClass;
import pages.LoginPage;
import pages.MakeupClass;

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
    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
