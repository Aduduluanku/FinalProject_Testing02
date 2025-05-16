package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateClass {

    WebDriver driver;

    public CreateClass(WebDriver driver){
        this.driver = driver;
    }

    public void menuClick(){
        driver.findElement(By.xpath("//nav//span[text()='Đào tạo']")).click();
        driver.findElement(By.xpath("//nav//p[text()='Lớp học']")).click();
    }

    public void clearInputs(String... labels) {
        for (String label : labels) {
            List<WebElement> inputs = driver.findElements(
                    By.xpath("//label[text()='" + label + "']/following-sibling::div//input")
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
                    By.xpath("//label[text()='" + label + "']/following-sibling::div//*[self::div or self::span]")
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

    public void validateAgeRangeField() {
        driver.findElement(By.xpath("//label[text()='Lịch học']/following-sibling::button")).click();
        WebElement input = driver.findElement(By.xpath("//label[text()='Độ tuổi']/following::input"));
        String ageRange = input.getAttribute("value").trim();
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(ageRange);
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            if (number < 0) {
                Assert.fail("Không được nhập số âm: " + number);
            }
        }
    }

    public void clearSchedule() {
        WebElement button = driver.findElement(By.xpath("//label[text()='Lịch học']/following-sibling::button"));
        button.click();
        List<WebElement> selected = driver.findElements(By.xpath("//div[contains(@class,'selected')]"));
        for (WebElement el : selected) {
            el.click();
        }
    }

    public void checkScheduleValidationError() {
        WebElement comboBox = driver.findElement(
                By.xpath("//label[text()='Lịch học']/following::div[@role='combobox'][1]")
        );
        String value = "";
        List<WebElement> inputs = comboBox.findElements(By.tagName("input"));
        if (!inputs.isEmpty()) {
            value = inputs.get(0).getAttribute("value").trim();
        } else {
            value = comboBox.getText().trim();
        }
        if (value.isEmpty() || value.equals("\u200B")) {
            Assert.fail("Không thấy thông báo lỗi validate cho lịch học");
        }
    }

    public void clearRoom() {
        List<WebElement> inputs = driver.findElements(
                By.xpath("//label[text()='Phòng học']/following-sibling::div//input")
        );
        if (!inputs.isEmpty()) {
            inputs.get(0).clear();
        }
    }
    public void checkRoomValidationError() {
        List<WebElement> possibleErrors = driver.findElements(
                By.xpath("//label[text()='Phòng học']/following-sibling::div//*[self::div or self::span]")
        );

        boolean hasError = possibleErrors.stream().anyMatch(el -> {
            String text = el.getText().trim().toLowerCase();
            return text.contains("bắt buộc") || text.contains("required") || text.contains("không được để trống");
        });

        if (!hasError) {
            Assert.fail("Không thấy thông báo lỗi cho trường: Phòng học");
        }
    }


    public void clearTeacherDropdowns() {
        String[] labels = {
                "Giáo viên VN",
                "Giáo viên nước ngoài",
                "Trợ giảng",
                "Người nhận xét"
        };

        for (String label : labels) {
            List<WebElement> inputs = driver.findElements(
                    By.xpath("//label[text()='" + label + "']/following::input")
            );
            if (!inputs.isEmpty()) {
                inputs.get(0).clear();
            }
        }
    }

    public void checkTeacherValidationError() {
        List<WebElement> possibleErrors = driver.findElements(
                By.xpath("//label[text()='Giáo viên VN']/following-sibling::div//*[self::div or self::span]")
        );

        boolean hasError = possibleErrors.stream().anyMatch(el -> {
            String text = el.getText().trim().toLowerCase();
            return text.contains("bắt buộc") || text.contains("required") || text.contains("chọn giáo viên");
        });

        if (!hasError) {
            Assert.fail("Không thấy thông báo lỗi khi không chọn giáo viên VN và giáo viên nước ngoài");
        }
    }

    public void createNewClass(String nameClass, String age, String dateStart, String schedule, String dateSchedule){
        // Điền tên lớp học
        driver.findElement(By.xpath("//label[text()='Tên lớp học']/following-sibling::div/input")).sendKeys(nameClass);

        // Điền độ tuổi
        driver.findElement(By.xpath("//label[text()='Độ tuổi']/following-sibling::div/input")).sendKeys(age);

        // Ngày bắt đầu của lớp
        driver.findElement(By.xpath("(//label[text()='Ngày bắt đầu']/following::input[1])[1]")).sendKeys(dateStart);

        // Chọn khoá học
        driver.findElement(By.xpath("//label[text()='Khoá học']/following-sibling::div/input")).click();
        driver.findElement(By.xpath("//ul[@id='asynchronous-listbox']/li[@id='asynchronous-option-0']")).click();

        // Chọn lịch học
        driver.findElement(By.xpath("//label[text()='Lịch học']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Lịch học']/following::div[@role='combobox'][1]")).click();
        driver.findElement(By.xpath("//li[@data-value='" + schedule + "']")).click();

        // Ngày bắt đầu của lịch học
        driver.findElement(By.xpath("(//label[text()='Ngày bắt đầu']/following::input[1])[2]")).sendKeys(dateSchedule);

        // Phòng học
        driver.findElement(By.xpath("//label[text()='Phòng học']/following-sibling::div/input")).click();
        driver.findElement(By.xpath("//ul[@id='asynchronous-listbox']/li[@id='asynchronous-option-0']")).click();

        // Giáo viên VN
        driver.findElement(By.xpath("//label[text()='Giáo viên VN']/following-sibling::div/input")).click();
        driver.findElement(By.xpath("//ul[@id='asynchronous-listbox']/li[@id='asynchronous-option-0']")).click();
    }

    public void saveButton(){
        driver.findElement(By.xpath("//button[text()='Lưu']")).click();
    }

    public void closeButton(){
        driver.findElement(By.xpath("//button[text()='Huỷ bỏ']")).click();
    }
}
