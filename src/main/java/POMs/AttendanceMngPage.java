package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AttendanceMngPage extends BasePage {

    @FindBy(xpath = "//h4[text()='Cơ sở: ']/following-sibling::div/div[@role='combobox']")
    private WebElement coSoDropdown;

    @FindBy(xpath = "//div[@id='class-status']")
    private WebElement classStatusDropdownContainer;

    @FindBy(xpath = "//input[@id='asynchronous']")
    private WebElement classAutocompleteInput;

    @FindBy(xpath = "//button[contains(@class, 'MuiButton-containedSecondary') and text()='Điểm danh']")
    private WebElement diemDanhButton;

    public AttendanceMngPage(WebDriver driver) {
        super(driver);
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


    public void openClassStatusDropdown() {
        waitForElementClickable(classStatusDropdownContainer).click();
    }

    public void selectClassStatus(String status) {
        openClassStatusDropdown();
        System.out.println("open status dropdown");
        By optionLocator = By.xpath("//ul[@role='listbox']//li[@data-value='" + convertClassStatusToValue(status) + "']");
        System.out.println("optionLocator:"+ optionLocator);
        WebElement optionElement = driver.findElement(optionLocator);
        waitForElementVisible(optionElement);
        waitForElementClickable(optionElement).click();
        waitForGridReload();
    }

    private String convertClassStatusToValue(String statusText) {
        switch (statusText) {
            case "Đã học (tất cả)":
                return "COMPLETED";
            default:
                return "";
        }
    }

    public void enterClassName(String className) {
        waitForElementVisible(classAutocompleteInput).sendKeys(className);
    }


    public void selectClassFromAutocomplete(String className) {
        enterClassName(className);
        By suggestionLocator = By.xpath("//input[@value='" + className + "']");
        WebElement suggestionElement = driver.findElement(suggestionLocator);
        waitForElementVisible(suggestionElement);
        waitForElementClickable(suggestionElement).click();
        waitForGridReload();
    }

    public String getSelectedClassFromAutocomplete() {
        return waitForElementVisible(classAutocompleteInput).getAttribute("value");
    }


    private void waitForGridReload() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}