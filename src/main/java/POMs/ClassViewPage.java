package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ClassViewPage extends BasePage {

    @FindBy(xpath = "//label[text()='Cơ sở']/following-sibling::div/input")
    private WebElement coSoInput;

    @FindBy(xpath = "//label[text()='Ngày bắt đầu']/following-sibling::div/input")
    private WebElement startDateInput;

    @FindBy(xpath = "//label[text()='Trạng thái']/following-sibling::div/input")
    private WebElement statusInput;

    @FindBy(xpath = "//h4[text()='Lịch sử đào tạo']")
    private WebElement trainingHistoryTitle;

    @FindBy(xpath = "//div[contains(@class, 'MuiAccordion-root') and .//h4[text()='Lịch sử đào tạo']]//table[contains(@class, 'MuiTable-root')]")
    private WebElement trainingHistoryTable;

    @FindBy(xpath = "//h4[text()='Danh sách học viên']")
    private WebElement studentListTitle;

    @FindBy(xpath = "//div[contains(@class, 'MuiAccordion-root') and .//h4[text()='Danh sách học viên']]//table[contains(@class, 'MuiTable-root')]")
    private WebElement studentTable;

    @FindBy(id = "class-status")
    private WebElement statusDropdown;

    @FindBy(xpath = "//li[@data-value='STUDENT_LEARNING']")
    private WebElement trangThaiDangHoc;
    @FindBy(xpath = "//li[@data-value='STUDENT_TRIAL']")
    private WebElement trangThaiHocThu;
    @FindBy(xpath = "//li[@data-value='STUDENT_IN_DEBT']")
    private WebElement trangThaiNoPhi;
    @FindBy(xpath = "//li[@data-value='STUDENT_LEAVE_OF_ABSENCE']")
    private WebElement trangThaiBaoLuu;
    @FindBy(xpath = "//li[@data-value='STUDENT_COMPLETED']")
    private WebElement trangThaiDaHocHetPhi;
    @FindBy(xpath = "//li[@data-value='STUDENT_INACTIVE']")
    private WebElement trangThaiNghiHoc;


    @FindBy(xpath = "//a[text()='Danh sách học viên']")
    private WebElement studentListTab;


    @FindBy(xpath = "//label[text()='Đang học']/following-sibling::div/input")
    private WebElement dangHocValue;
    @FindBy(xpath = "//label[text()='Học thử']/following-sibling::div/input")
    private WebElement hocThuValue;
    @FindBy(xpath = "//label[text()='Nợ phí']/following-sibling::div/input")
    private WebElement noPhiValue;
    @FindBy(xpath = "//label[text()='Bảo lưu']/following-sibling::div/input")
    private WebElement baoLuuValue;

    public ClassViewPage(WebDriver driver) {
        super(driver);
    }

    public String getDangHocValue() {
        return dangHocValue.getAttribute("value");
    }

    public String getHocThuValue() {
        return hocThuValue.getAttribute("value");
    }

    public String getNoPhiValue() {
        return noPhiValue.getAttribute("value");
    }

    public String getBaoLuuValue() {
        return baoLuuValue.getAttribute("value");
    }


    public String getCoSoName() {
        return coSoInput.getAttribute("value");
    }

    public String getStartDate() {
        return startDateInput.getAttribute("value");
    }

    public String getStatus() {
        return statusInput.getAttribute("value");
    }

    public List<String> getStudentList() {
        return getTableColumnValues(studentTable, "Tên học sinh");
    }

    public void selectStatus(String status) {
        statusDropdown.click();
        WebElement statusOption = null;

        switch (status) {
            case "Đang học":
                statusOption = trangThaiDangHoc;
                break;
            case "Học thử":
                statusOption = trangThaiHocThu;
                break;
            case "Nợ phí":
                statusOption = trangThaiNoPhi;
                break;
            case "Bảo lưu học phí":
                statusOption = trangThaiBaoLuu;
                break;
            case "Đã học hết phí":
                statusOption = trangThaiDaHocHetPhi;
                break;
            case "Nghỉ học":
                statusOption = trangThaiNghiHoc;
                break;
            default:
                throw new IllegalArgumentException("Trạng thái không hợp lệ: " + status);
        }

        statusOption.click();
    }

    private List<String> getTableColumnValues(WebElement table, String columnName) {
        List<String> columnValues = new ArrayList<>();
        int columnIndex = -1;
        List<WebElement> headerCells = table.findElements(By.xpath(".//thead//th"));
        for (int i = 0; i < headerCells.size(); i++) {
            if (headerCells.get(i).getText().trim().equals(columnName)) {
                columnIndex = i + 1;
                break;
            }
        }

        if (columnIndex > 0) {
            List<WebElement> rowCells = table.findElements(By.xpath(".//tbody//tr/td[" + columnIndex + "]"));
            for (WebElement cell : rowCells) {
                columnValues.add(cell.getText().trim());
            }
        }
        return columnValues;
    }

    public WebElement getStudentTable() {
        return studentTable;
    }
}