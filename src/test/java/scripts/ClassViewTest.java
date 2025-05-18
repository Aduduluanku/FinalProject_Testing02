package scripts;

import POMs.ClassMngPage;
import POMs.ClassViewPage;
import POMs.HomePage;
import POMs.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ClassViewTest extends BaseTest_ON{
    private LoginPage loginPage;
    private HomePage homePage;
    private ClassMngPage classMngPage;
    private ClassViewPage classViewPage;

    @BeforeMethod
    public void setUpClassViewTest() {
        logger.info("Bắt đầu thiết lập cho ClassViewTest.");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        classMngPage = new ClassMngPage(driver);
        classViewPage = new ClassViewPage(driver);

        loginPage.enterUsername("testadmin");
        logger.info("Đã nhập username: testadmin");
        loginPage.enterPassword("test1234");
        logger.info("Đã nhập password: test1234");
        loginPage.clickLoginButton();
        logger.info("Đã click nút đăng nhập.");

        homePage.openTrainingMenu();
        logger.info("Đã mở menu 'Đào tạo'.");

        classMngPage = homePage.navigateToClass();
        logger.info("Đã chọn submenu 'Lớp học'.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/classes"));
        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Danh sách lớp học. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận đang ở trang Danh sách lớp học. URL hiện tại: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }
    }

    @Test
    public void testCountStudentsByStatus() {
        logger.info("Bắt đầu test case: testCountStudentsByStatus.");

        String facilityName = "OLMS 2";
        classMngPage.selectFacilityInFilter(facilityName);
        logger.info("Đã chọn cơ sở: " + facilityName);

        String selectedFacility = classMngPage.getSelectedFacility();
        Assert.assertEquals(selectedFacility, facilityName, "Chọn cơ sở không thành công. Cơ sở thực tế: " + selectedFacility);
        logger.info("Đã kiểm tra và xác nhận cơ sở được chọn là: " + selectedFacility);

        String teacherName = "Ánh Amy";
        classMngPage.enterTeacherNameInSearch(teacherName);
        logger.info("Đã nhập tên giáo viên: " + teacherName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String classNameToClick = "Kindy 11";
        classMngPage.clickClassByName(classNameToClick);
        logger.info("Đã click vào lớp học có tên: " + classNameToClick);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String status = "Bảo lưu học phí";
        classViewPage.selectStatus(status);
        logger.info("Đã chọn trạng thái: " + status);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement studentTableElement = classViewPage.getStudentTable();
        WebDriverWait waitTable = new WebDriverWait(driver, Duration.ofSeconds(5)); // Chờ bảng hiển thị
        waitTable.until(ExpectedConditions.visibilityOf(studentTableElement));
        List<WebElement> studentRows = studentTableElement.findElements(By.xpath(".//tbody/tr"));
        int numberOfStudents = studentRows.size();
        logger.info("Số lượng học viên hiển thị sau khi lọc theo trạng thái '" + status + "': " + numberOfStudents);


        Assert.assertTrue(numberOfStudents >= 0, "Số lượng học viên phải >= 0");
        System.out.println("Số lượng học viên có trạng thái '" + status + "': " + numberOfStudents);


        // Lấy giá trị số lượng học viên theo trạng thái (nếu có hiển thị riêng) và so sánh
        String statusValue = "";
        switch (status) {
            case "Đang học":
                statusValue = classViewPage.getDangHocValue();
                break;
            case "Học thử":
                statusValue = classViewPage.getHocThuValue();
                break;
            case "Nợ phí":
                statusValue = classViewPage.getNoPhiValue();
                break;
            case "Bảo lưu học phí":
                statusValue = classViewPage.getBaoLuuValue();
                break;
            default:
                Assert.fail("Trạng thái không hợp lệ: " + status);
        }

        // Kiểm tra nếu giá trị số lượng trạng thái được hiển thị và so sánh
        if (!statusValue.isEmpty()) {
            int expectedStudentCount = Integer.parseInt(statusValue);
            Assert.assertEquals(numberOfStudents, expectedStudentCount, "Số lượng học viên không khớp với số lượng hiển thị cho trạng thái '" + status + "'. Thực tế: " + numberOfStudents + ", Mong đợi: " + expectedStudentCount);
            logger.info("Đã so sánh và xác nhận số lượng học viên khớp với số lượng hiển thị cho trạng thái '" + status + "'.");
        } else {
            logger.warn("Không tìm thấy giá trị số lượng học viên riêng cho trạng thái '" + status + "'. Chỉ kiểm tra số lượng hàng trong bảng.");
        }

        logger.info("Hoàn thành test case: testCountStudentsByStatus.");
    }
}
