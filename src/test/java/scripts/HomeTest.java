package scripts;

import POMs.ClassMngPage;
import POMs.HomePage;
import POMs.LoginPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private ClassMngPage classMngPage;
    private Actions actions; // Đối tượng Actions để thực hiện các tương tác chuột nâng cao

    @BeforeMethod
    public void setupHomePage() {
        logger.info("Bắt đầu thiết lập trang HomePage.");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        classMngPage = new ClassMngPage(driver);
        actions = new Actions(driver);

        loginPage.login("testadmin", "test1234");
        logger.info("Đã đăng nhập thành công với username 'testadmin'.");

        Assert.assertTrue(homePage.getPageTitleText().contains("OLMS Admin"), "Không phải trang HomePage sau khi đăng nhập.");
        logger.info("Đã xác nhận đang ở trang HomePage.");
    }


    @Test
    public void testOpenTrainingMenuAndObserveSubmenus() {
        logger.info("Bắt đầu test case: testOpenTrainingMenuAndObserveSubmenus");

        homePage.hoverTrainingMenu();
        logger.info("Đã di chuột đến menu 'Đào tạo'.");

        homePage.openTrainingMenu();
        logger.info("Đã click vào menu 'Đào tạo'.");
        try {
            Thread.sleep(3000); // Chờ 3000 milliseconds (3 giây)
            logger.info("Đã chờ 3 giây để quan sát các submenu.");
        } catch (InterruptedException e) {
            // Xử lý ngoại lệ nếu thread bị gián đoạn trong quá trình chờ
            Thread.currentThread().interrupt();
            logger.error("Quá trình chờ bị gián đoạn.", e);
        }

        logger.info("Kết thúc test case: testOpenTrainingMenuAndObserveSubmenus. Trình duyệt sẽ đóng sau đó.");
    }

    @Test
    public void testNavigateToClassListPage() {
        logger.info("Bắt đầu test case: testNavigateToClassListPage");

        classMngPage = homePage.navigateToClass();
        logger.info("Đã gọi phương thức navigateToClass() để chuyển đến trang Lớp học.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/classes"));

        Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Danh sách lớp học. URL hiện tại là: " + driver.getCurrentUrl());
        logger.info("Đã xác nhận điều hướng thành công đến trang Danh sách lớp học. URL hiện tại là: " + driver.getCurrentUrl());

    }

    @Test
    public void testNavigateToClassListPage2() {
        logger.info("Bắt đầu test case: testNavigateToClassListPage 2");

        Object result = homePage.navigateToSubMenuofTraining("Lớp học");

        if (result instanceof ClassMngPage) {
            classMngPage = (ClassMngPage) result;
            logger.info("Đã gọi phương thức navigateToSubMenuofTraining('Lớp học') và nhận được instance của ClassMngPage.");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe("https://olms.codedao.io.vn/academic/classes"));

            // Assert kiểm tra xem URL hiện tại có đúng là URL của trang Danh sách lớp học hay không
            Assert.assertTrue(isUrlCorrect, "Không điều hướng được đến trang Danh sách lớp học. URL hiện tại là: " + driver.getCurrentUrl());
            logger.info("Đã xác nhận điều hướng thành công đến trang Danh sách lớp học. URL hiện tại là: " + driver.getCurrentUrl());

        } else {
            Assert.fail("Phương thức navigateToSubMenuofTraining('Lớp học') không trả về instance của ClassMngPage.");
            logger.error("Phương thức navigateToSubMenuofTraining('Lớp học') không trả về instance của ClassMngPage. Kết quả trả về: " + result);
        }
    }
}
