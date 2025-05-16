package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Students {

    WebDriver driver;

    public Students(WebDriver driver){
        this.driver = driver;
    }

    public void menuClick(){
        driver.findElement(By.xpath("//nav//span[text()='Đào tạo']")).click();
        driver.findElement(By.xpath("//nav//p[text()='DS Học viên']")).click();
    }

    public void clearInputs(String... labels) {
        for (String label : labels) {
            List<WebElement> inputs = driver.findElements(
                    By.xpath("//label[text()='" + label + "']/parent::div/following-sibling::div//input")
            );
            if (!inputs.isEmpty()) {
                inputs.get(0).clear();
            }
        }
    }

    public void checkValidationErrors(String... labels) {
        List<String> errors = new ArrayList<>();
        for (String label : labels) {
            List<WebElement> possibleErrors = driver.findElements(
                    By.xpath("//label[text()='" + label + "']/parent::div/following-sibling::div//*[self::div or self::span]")
            );
            boolean hasError = possibleErrors.stream().anyMatch(el -> {
                String text = el.getText().trim().toLowerCase();
                return text.contains("bắt buộc") || text.contains("required") || text.contains("không được để trống");
            });
            if (!hasError) {
                errors.add("Không thấy thông báo lỗi cho trường: " + label);
            }
        }

        if (!errors.isEmpty()) {
            Assert.fail(String.join(", ", errors));
        }
    }

    public void addNewStudent(String firstName, String lastName, String englishName, String inputResult){
        driver.findElement(By.xpath("//label[text()='Họ và tên đệm']/following-sibling::div/input")).sendKeys(firstName);
        driver.findElement(By.xpath("//label[text()='Tên']/following-sibling::div/input")).sendKeys(lastName);
        driver.findElement(By.xpath("//label[text()='Tên tiếng Anh']/following-sibling::div/input")).sendKeys(englishName);
        driver.findElement(By.xpath("//label[text()='Kết quả test đầu vào']/following-sibling::div/input")).sendKeys(inputResult);
        driver.findElement(By.xpath("//label[text()='Chọn PH từ hệ thống (thông tin sẽ được tự động điền)']/following-sibling::div/input")).click();
        driver.findElement(By.xpath("//ul[@id='asynchronous-listbox']/li[@id='asynchronous-option-0']")).click();
    }

    public void addNewStudentInputParents(String firstName, String lastName, String englishName, String inputResult, String nameParent, String phone, String email){
        driver.findElement(By.xpath("//label[text()='Họ và tên đệm']/following-sibling::div/input")).sendKeys(firstName);
        driver.findElement(By.xpath("//label[text()='Tên']/following-sibling::div/input")).sendKeys(lastName);
        driver.findElement(By.xpath("//label[text()='Tên tiếng Anh']/following-sibling::div/input")).sendKeys(englishName);
        driver.findElement(By.xpath("//label[text()='Kết quả test đầu vào']/following-sibling::div/input")).sendKeys(inputResult);
        Actions actions = new Actions(driver);
        WebElement scroll =  driver.findElement(By.xpath("//label[text()='Tên PH']"));
        actions.scrollToElement(scroll);
        driver.findElement(By.xpath("//label[text()='Tên PH']/following-sibling::div/input")).sendKeys(nameParent);
        driver.findElement(By.xpath("//label[text()='SĐT']/following-sibling::div/input")).sendKeys(phone);
        driver.findElement(By.xpath("//label[text()='Email PH']/following-sibling::div/input")).sendKeys(email);
    }

    public void saveButton(){
        driver.findElement(By.xpath("//button[text()='Lưu']")).click();
    }

    public void closeButton(){
        driver.findElement(By.xpath("//button[text()='Huỷ bỏ']")).click();
    }
}
