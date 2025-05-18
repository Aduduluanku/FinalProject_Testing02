package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='/']")
    private WebElement homeMenuItem;

    @FindBy(xpath = "//span[text()='Đào tạo']/ancestor::a")
    private WebElement trainingMenuParent;

    @FindBy(xpath = "//a[@href='/academic/classes']")
    private WebElement classSubMenuItem;

    @FindBy(xpath = "//a[@href='/academic/attendance']")
    private WebElement attendanceSubMenuItem;

    @FindBy(xpath = "//span[text()='Tài chính']/ancestor::a")
    private WebElement financeMenuParent;

    @FindBy(xpath = "//span[text()='Báo cáo']/ancestor::a")
    private WebElement reportMenuParent;

    @FindBy(xpath = "//span[text()='Cấu hình']/ancestor::a")
    private WebElement configurationMenuParent;

    @FindBy(xpath = "//h5[text()='Đăng xuất']/ancestor::a")
    private WebElement logoutButtonLink;

    // Locator cho dropdown "Cơ sở"
    @FindBy(xpath = "//div[@role='combobox']")
    private WebElement facilityDropdown;

    @FindBy(xpath = "//a[@href='/academic/classes']")
    private WebElement classSubMenu;
    private final By classSubMenuLocator = By.xpath("//a[@href='/academic/classes']");


    public HomePage(WebDriver driver) {
        super(driver); // Gọi constructor không tham số của BasePage
    }


    public HomePage navigateToHome() {
        click(homeMenuItem);
        return this; // Trả về instance của HomePage để chain methods
    }

    public void hoverTrainingMenu() {
        getActions().moveToElement(trainingMenuParent).perform();
    }

    public void openTrainingMenu() {
        click(trainingMenuParent);
    }

    public ClassMngPage navigateToClass() {
        openTrainingMenu();
        click(classSubMenuItem);
        return new ClassMngPage(driver);
    }


    public void openFacilityDropdown() {
        click(facilityDropdown);
    }

    // Cần thêm phương thức để chọn cơ sở cụ thể từ dropdown
    public void selectFacility(String facilityName) {
        openFacilityDropdown();
        // Locator cho các option trong dropdown, cần điều chỉnh cho phù hợp với DOM thực tế
        String facilityOptionXPath = "//div[contains(@class, 'ant-select-dropdown')]//div[@class='ant-select-item-option-content' and text()='" + facilityName + "']";
        WebElement facilityOption = waitForElementVisible(findByXPath(facilityOptionXPath));
        click(facilityOption);
    }


    public String getPageTitleText() {
        return driver.getTitle();
    }

    // Helper method để tìm element bằng XPath
    private WebElement findByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }



    public Object navigateToSubMenuofTraining(String subMenuName) {
        openTrainingMenu();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String subMenuXPath = "//a[.//p[text()='" + subMenuName + "']]"; // Hoặc XPath phù hợp
        WebElement subMenuElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subMenuXPath)));

        if (subMenuElement != null) {
            click(subMenuElement);
            System.out.println("Đã click vào submenu '" + subMenuName + "' dưới menu 'Đào tạo'. Bắt đầu chờ điều hướng...");

            switch (subMenuName) {
                case "Lớp học":
                    return new ClassMngPage(driver);
                case "Điểm danh":
                    return new AttendanceMngPage(driver);
                default:
                    System.out.println("Không tìm thấy Page Object tương ứng cho submenu '" + subMenuName + "'.");
                    return null;
            }
        } else {
            System.out.println("Không tìm thấy submenu '" + subMenuName + "' dưới menu 'Đào tạo' sau khi chờ.");
            return null;
        }
    }
}