package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
<<<<<<< HEAD

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    public void inputUser (String userName){
        driver.findElement(By.xpath("//input[@id=\"emailOrUsername\"]")).sendKeys(userName);
    }
    public void inputPass(String pass){
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
    }
    public void loginButton(){
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }
}
=======
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userName, String pwd){
        driver.findElement(By.xpath("//input[@id=\"emailOrUsername\"]")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys(pwd);
        driver.findElement(By.xpath("//button[@class=\"MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-fullWidth MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-fullWidth css-1ig2p77\"]")).click();
    }
}


>>>>>>> master
