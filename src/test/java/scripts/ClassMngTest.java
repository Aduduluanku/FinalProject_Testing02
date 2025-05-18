package scripts;

import POMs.ClassMngPage;
import POMs.HomePage;
import POMs.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ClassMngTest extends BaseTest  {
    private LoginPage loginPage;
    private HomePage homePage;
    private ClassMngPage classMngPage;


    @BeforeMethod
    public void setUpClassMngTest() {
        logger.info("Bắt đầu thiết lập cho ClassMngTest.");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        classMngPage = new ClassMngPage(driver);

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlContains("/academic/classes"));
        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Danh sách lớp học. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận đang ở trang Danh sách lớp học. URL hiện tại: " + driver.getCurrentUrl());

        try {
            Thread.sleep(3000);
            logger.debug("Đã chờ 3 giây để quan sát trang (mục đích demo).");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }
    }


    @Test
    public void testFilterClassesByFacilityAndTeacher() {
        logger.info("Bắt đầu test case: testFilterClassesByFacilityAndTeacher.");

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
            logger.debug("Đã chờ 1 giây sau khi nhập tên giáo viên (mục đích demo).");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }


        String classNameToClick = "Kindy 11";
        classMngPage.clickClassByName(classNameToClick);
        logger.info("Đã click vào lớp học có tên: " + classNameToClick);

        try {
            Thread.sleep(3000);
            logger.debug("Đã chờ 3 giây để quan sát trang chi tiết lớp học (mục đích demo).");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn trong khi chờ quan sát.", e);
        }

        logger.info("Kết thúc test case: testFilterClassesByFacilityAndTeacher.");
    }
}
