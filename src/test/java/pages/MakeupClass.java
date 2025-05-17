package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MakeupClass {
    WebDriver driver;

    public MakeupClass(WebDriver driver){
        this.driver = driver;
    }
    public void menuClick(){
        driver.findElement(By.xpath("//nav//span[text()='Đào tạo']")).click();
        driver.findElement(By.xpath("//nav//p[text()='DS Học viên bồi bài']")).click();
    }

    public void markIconMakeupClass(String dateTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("(//tr[td//span[text()='Chưa bồi']])[1]/td[last()]//button[1]")).click();
        driver.findElement(By.xpath("//*[@aria-label='Choose date']")).click();

        String day = dateTime.split(" ")[0].split("/")[0];
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='button' and normalize-space(text())='" + day + "']")));
        dayElement.click();

        String[] timeParts = dateTime.split(" ")[1].split(":");
        String hour = timeParts[0];
        String minute = timeParts[1];

        WebElement hourList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@aria-label='Select hours']")));
        WebElement hourOption = hourList.findElement(By.xpath(".//li[normalize-space(text())='" + hour + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(hourOption)).click();

        WebElement minuteList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@aria-label='Select minutes']")));
        WebElement minuteOption = minuteList.findElement(By.xpath(".//li[normalize-space(text())='" + minute + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(minuteOption)).click();

        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(text())='OK']")));
        okButton.click();

        WebElement input = driver.findElement(By.xpath("//input[@placeholder='DD/MM/YYYY hh:mm']"));
        input.clear();
        input.sendKeys(dateTime);
        input.sendKeys(Keys.ENTER);
    }

    public void markPaidAbsence(){
        driver.findElement(By.xpath("(//tr[td//span[text()='Chưa bồi']])[1]/td[last()]//button[2]")).click();
    }

    public void rollCall(){
        driver.findElement(By.xpath("(//tr[td//span[text()='Đã bồi']])[1]/td[last()]//button[1]")).click();
    }

    public void filterStatus(){
        driver.findElement(By.xpath("//label[text()='Trạng thái']/following-sibling::div//div[@role='combobox']")).click();
        driver.findElement(By.xpath("//ul[@role='listbox']/li[1]")).click();
    }

    public void filterMonth(){
        driver.findElement(By.xpath("//label[text()='Tháng']/following-sibling::div//div[@role='combobox']")).click();
        driver.findElement(By.xpath("//ul[@role='listbox']/li[1]")).click();
    }

    public void saveButton(){
        driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();
    }

    public void closeButton(){
        driver.findElement(By.xpath("//button[text()='Huỷ bỏ']")).click();
    }
}
