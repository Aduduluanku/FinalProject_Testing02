package scripts;

import POMs.AttendanceMngPage;
import POMs.HomePage;
import POMs.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AttendanceMngTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private AttendanceMngPage attendancePage; // Page Object cho trang Điểm danh

    @BeforeMethod
    public void setUpAttendanceTest() {
        logger.info("Bắt đầu thiết lập cho AttendanceTest.");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.enterUsername("testadmin");
        logger.info("Đã nhập username: testadmin");
        loginPage.enterPassword("test1234");
        logger.info("Đã nhập password: test1234");
        loginPage.clickLoginButton();
        logger.info("Đã click nút đăng nhập.");

        homePage.openTrainingMenu();
        logger.info("Đã mở menu 'Đào tạo'.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }
    }

    @Test
    public void navigateToAttendanceSubMenu() {
        logger.info("Bắt đầu test case: navigateToAttendanceSubMenu.");
        attendancePage = (AttendanceMngPage) homePage.navigateToSubMenuofTraining("Điểm danh");
        logger.info("Đã cố gắng điều hướng đến submenu 'Điểm danh' của menu 'Đào tạo'.");

        Assert.assertNotNull(attendancePage, "Không thể điều hướng đến trang Điểm danh.");
        logger.info("Đã kiểm tra: Điều hướng đến trang Điểm danh không bị lỗi.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/attendance"));
        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Điểm danh. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận đang ở trang Điểm danh. URL hiện tại: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }

        logger.info("Hoàn thành test case: navigateToAttendanceSubMenu.");
    }

    @Test
    public void filterAttendanceByFacility() {
        logger.info("Bắt đầu test case: navigateToAttendanceSubMenu.");

        attendancePage = (AttendanceMngPage) homePage.navigateToSubMenuofTraining("Điểm danh");
        logger.info("Đã cố gắng điều hướng đến submenu 'Điểm danh' của menu 'Đào tạo'.");

        Assert.assertNotNull(attendancePage, "Không thể điều hướng đến trang Điểm danh.");
        logger.info("Đã kiểm tra: Điều hướng đến trang Điểm danh không bị lỗi.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/attendance"));
        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Điểm danh. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận đang ở trang Điểm danh. URL hiện tại: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }
        logger.info("Hoàn thành test case: navigateToAttendanceSubMenu.");

        logger.info("Bắt đầu test case: filterAttendanceByFacility.");
        String facilityToSelect = "OLMS 2";
        attendancePage.selectFacilityInFilter(facilityToSelect);
        logger.info("Đã chọn cơ sở: " + facilityToSelect);

        // Kiểm tra giá trị đã chọn trong dropdown Cơ sở
        String selectedFacility = attendancePage.getSelectedFacility();
        Assert.assertEquals(selectedFacility, facilityToSelect, "Giá trị cơ sở đã chọn không đúng.");
        logger.info("Đã kiểm tra giá trị cơ sở đã chọn: " + selectedFacility);
        logger.info("Hoàn thành test case: filterAttendanceByFacility.");
    }

    @Test
    public void filterAttendanceByClassStatus() {
        logger.info("Bắt đầu test case: navigateToAttendanceSubMenu.");
        attendancePage = (AttendanceMngPage) homePage.navigateToSubMenuofTraining("Điểm danh");
        logger.info("Đã cố gắng điều hướng đến submenu 'Điểm danh' của menu 'Đào tạo'.");

        Assert.assertNotNull(attendancePage, "Không thể điều hướng đến trang Điểm danh.");
        logger.info("Đã kiểm tra: Điều hướng đến trang Điểm danh không bị lỗi.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/attendance"));
        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Điểm danh. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận đang ở trang Điểm danh. URL hiện tại: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }

        logger.info("Hoàn thành test case: navigateToAttendanceSubMenu.");

        logger.info("Bắt đầu test case: filterAttendanceByClassStatus.");

        String selectedStatus = "Đã học (tất cả)";
        attendancePage.selectClassStatus(selectedStatus);
        logger.info("Đã chọn trạng thái lớp: " + selectedStatus);
        logger.info("Hoàn thành test case: filterAttendanceByClassStatus.");
    }

    @Test
    public void filterAttendanceByClassName() {
        logger.info("Bắt đầu test case: navigateToAttendanceSubMenu.");
        attendancePage = (AttendanceMngPage) homePage.navigateToSubMenuofTraining("Điểm danh");
        logger.info("Đã cố gắng điều hướng đến submenu 'Điểm danh' của menu 'Đào tạo'.");

        Assert.assertNotNull(attendancePage, "Không thể điều hướng đến trang Điểm danh.");
        logger.info("Đã kiểm tra: Điều hướng đến trang Điểm danh không bị lỗi.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/attendance"));
        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Điểm danh. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận đang ở trang Điểm danh. URL hiện tại: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }

        logger.info("Hoàn thành test case: navigateToAttendanceSubMenu.");

        logger.info("Bắt đầu test case: filterAttendanceByClassName.");

        String classNameToFilter = "[ON]-ClassWithFullInfo2";
        attendancePage.selectClassFromAutocomplete(classNameToFilter);
        logger.info("Đã chọn lớp học: " + classNameToFilter);
        logger.info("Hoàn thành test case: filterAttendanceByClassName.");
    }
}
