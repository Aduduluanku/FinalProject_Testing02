package scripts;

import listeners.SimpleListener;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateClass;
import pages.LoginPage;
import pages.Students;

@Listeners(SimpleListener.class)
public class addStudent extends BaseTest{

    @Test
    public void verifyListMenu(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        Students students = new Students(driver);
        students.menuClick();
    }

    @Test
    public void verifyAddClassPopup(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        Students students = new Students(driver);
        students.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
    }

    @Test
    public void verifyValidation(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        Students students = new Students(driver);
        students.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();
        students.clearInputs("Họ và tên đệm", "Tên");
        students.saveButton();
        students.checkValidationErrors("Họ và tên đệm", "Tên", "Tên PH", "SĐT", "Email PH");
    }

    @Test
    public void verifyAddStudent(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        Students students = new Students(driver);
        students.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();

        students.addNewStudent("Nguyen Van", "Teo","Tommy", "50");
        students.saveButton();
    }

    @Test
    public void verifyAddStudentInputParents(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUser("testadmin");
        loginPage.inputPass("test1234");
        loginPage.loginButton();

        Students students = new Students(driver);
        students.menuClick();
        driver.findElement(By.xpath("//button[text()='Tạo mới']")).click();

        students.addNewStudentInputParents("Nguyen Van", "Teo","Tommy", "50", "Nguyễn Hồng", "09099991876", "nguyenhong@gmail.com");
        students.saveButton();
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
