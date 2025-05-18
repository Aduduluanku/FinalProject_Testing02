package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClassMngPage extends BasePage {

    private final String expectedUrl = "https://olms.codedao.io.vn/academic/classes";

    @FindBy(xpath = "//h4[text()='Cơ sở: ']/following-sibling::div/div[@role='combobox']")
    private WebElement coSoDropdown;

    @FindBy(xpath = "//input[contains(@class,'css-b52kj1')]")
    private WebElement searchTeacherInput;

    @FindBy(xpath = "//button[text()='Tìm kiếm lớp học']")
    private WebElement searchClassButton;

    @FindBy(xpath = "//button[contains(text(),'Tạo mới')]")
    private WebElement createNewButton;



    public ClassMngPage(WebDriver driver) {
        super(driver);
    }

    public boolean isClassListPageUrlCorrect() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra URL trang quản lý lớp học: " + e.getMessage());
            return false;
        }
    }

    public void openCoSoDropdown() {
        click(coSoDropdown);
    }

    public void selectFacilityInFilter(String facilityName) {
        openCoSoDropdown();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By facilityOptionLocator = By.xpath("//ul[@role='listbox']//li[@role='option' and text()='" + facilityName + "']");
        System.out.println("Locator OF Dropdown Cơ sở: "+facilityOptionLocator);
        try {
            WebElement facilityOption = wait.until(ExpectedConditions.elementToBeClickable(facilityOptionLocator));
            click(facilityOption);
            wait.until(ExpectedConditions.textToBePresentInElement(coSoDropdown, facilityName));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("Không tìm thấy hoặc không chọn được tùy chọn cơ sở: " + facilityName);
            e.printStackTrace();
        }
    }

    public String getSelectedFacility() {
        return getText(coSoDropdown);
    }

    public void enterTeacherNameInSearch(String teacherName) {
        sendKeys(searchTeacherInput, teacherName);
        WebDriverWait waitAutocomplete = new WebDriverWait(driver, Duration.ofSeconds(5));
        By autocompleteOptionLocator = By.xpath("//ul[@role='listbox']//li[contains(text(), '" + teacherName + "')]");
        try {
            WebElement firstAutocompleteOption = waitAutocomplete.until(ExpectedConditions.visibilityOfElementLocated(autocompleteOptionLocator));
            click(firstAutocompleteOption);
            System.out.println("Đã chọn giáo viên từ autocomplete: " + teacherName);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Không thấy gợi ý autocomplete cho: " + teacherName + ". Thử gửi TAB.");
            searchTeacherInput.sendKeys(Keys.TAB);
        }

        WebDriverWait waitTableLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
        By tableRowLocator = By.xpath("//table[contains(@class, 'MuiTable-root')]//tbody//tr[not(contains(@class, 'ant-table-placeholder'))]");
        waitTableLoad.until(ExpectedConditions.presenceOfElementLocated(tableRowLocator));
    }


    public void clickSearchClassButton() {
        click(searchClassButton);
    }


    public void clickClassByName(String className) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By classLocator = By.xpath("//a[contains(@href, '/academic/classes/details/') and .//p[text()='" + className + "']]");
        WebElement classElement = wait.until(ExpectedConditions.elementToBeClickable(classLocator));
        classElement.click();
    }


    public void clickCreateNewButton() {
        click(createNewButton);
    }



}