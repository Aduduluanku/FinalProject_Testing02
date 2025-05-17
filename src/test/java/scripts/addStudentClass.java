package scripts;

import listeners.SimpleListener;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateClass;
import pages.LoginPage;

@Listeners(SimpleListener.class)
public class addStudentClass extends BaseTest {

    @Test
    public void verifyListMenu(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
    }
    @Test
    public void verifyAddClassPopup(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
    }
    @Test
    public void verifyValidation(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
        createClass.clearInputs("Tên lớp học", "Độ tuổi", "Khoá học", "Phòng học");
        createClass.saveButton();
        createClass.checkValidationErrors("Tên lớp học", "Độ tuổi", "Khoá học");
    }

    @Test
    public void verifyValidateTeacherDropdowns(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
        createClass.clearTeacherDropdowns();
        createClass.saveButton();
        createClass.checkTeacherValidationError();
    }

    @Test
    public void verifyValidateSchedule(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
        createClass.clearSchedule();
        createClass.saveButton();
        createClass.checkScheduleValidationError();
    }

    @Test
    public void verifyValidateRoom(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
        createClass.clearRoom();
        createClass.saveButton();
        createClass.checkRoomValidationError();
    }

    @Test
    public void verifyCreateNewClass(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();

        createClass.createNewClass("Toiec 1", "18-50", "18/05/2025","1/17:00-18:30", "18/05/2025");
        createClass.saveButton();
    }

    @Test
    public void verifyCloseButton(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        CreateClass createClass = new CreateClass(driver);
        createClass.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
        createClass.closeButton();
    }
    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
