package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
