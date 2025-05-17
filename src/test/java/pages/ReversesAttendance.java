package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReversesAttendance {

    WebDriver driver;

    public ReversesAttendance(WebDriver driver){
        this.driver = driver;
    }

    public void menuClick(){
        driver.findElement(By.xpath("//nav//span[text()='Đào tạo']")).click();
        driver.findElement(By.xpath("//nav//p[text()='DS Học viên bồi bài']")).click();
    }

    public void popupReversesAttendance(){
        driver.findElement(By.xpath("//tr[@role='checkbox'][1]//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//button[text()='Điểm danh']")).click();
    }

    public void saveButton(){
        driver.findElement(By.xpath("//button[text()='Lưu']")).click();
    }

    public void closeButton(){
        driver.findElement(By.xpath("//button[text()='Huỷ bỏ']")).click();
    }
}
