package scripts;

import POMs.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @BeforeMethod
    public void setupLoginPage() {
        loginPage = new LoginPage(driver); // Khởi tạo một instance của LoginPage
        logger.info("Đã khởi tạo đối tượng LoginPage thành công.");
    }

    @Test
    public void testLoginWithValidCredentials() {
        logger.info("Bắt đầu test case: testLoginWithValidCredentials");
        loginPage.enterUsername("testadmin");
        logger.info("Đã nhập username: testadmin");
        loginPage.enterPassword("test1234");
        logger.info("Đã nhập password: test1234");
        loginPage.clickLoginButton();
        logger.info("Đã click nút đăng nhập.");
        String pageTitle = driver.getTitle();
        logger.info("Title của trang hiện tại là:: " + pageTitle);
        Assert.assertEquals(driver.getTitle(), "OLMS Admin", "Đăng nhập thất bại: Title trang sau khi đăng nhập không đúng.");
        logger.info("Test case testLoginWithValidCredentials đã hoàn thành thành công.");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        logger.info("Bắt đầu test case: testLoginWithInvalidUsername");
        loginPage.enterUsername("testadmin");
        logger.info("Đã nhập username: testadmin");
        loginPage.enterPassword("test1234");
        logger.info("Đã nhập password: test1234");
        loginPage.clickLoginButton();
        logger.info("Đã click nút đăng nhập.");
        String errorMessage = loginPage.getErrorMessage();
        logger.info("Thông báo lỗi hiển thị là: " + errorMessage);
        Assert.assertEquals(errorMessage, "Đã có lỗi xảy ra trong quá trình đăng nhập. Xin vui lòng thử lại vào lần sau!", "Thông báo lỗi không hiển thị hoặc không đúng cho username không hợp lệ.");
        logger.info("Test case testLoginWithInvalidUsername đã hoàn thành thành công.");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        logger.info("Bắt đầu test case: testLoginWithInvalidPassword");
        loginPage.enterUsername("testadmin");
        logger.info("Đã nhập username: testadmin");
        loginPage.enterPassword("test1234");
        logger.info("Đã nhập password: test1234");
        loginPage.clickLoginButton();
        logger.info("Đã click nút đăng nhập.");
        String errorMessage = loginPage.getErrorMessage();
        logger.info("Thông báo lỗi hiển thị là: " + errorMessage);
        Assert.assertEquals(errorMessage, "Đã có lỗi xảy ra trong quá trình đăng nhập. Xin vui lòng thử lại vào lần sau!", "Thông báo lỗi không hiển thị hoặc không đúng cho password không hợp lệ.");
        logger.info("Test case testLoginWithInvalidPassword đã hoàn thành thành công.");
    }

    /**
     * Test case kiểm tra chức năng đăng nhập khi không nhập thông tin đăng nhập (username và password rỗng).
     * Thực hiện bước click trực tiếp vào nút đăng nhập và kiểm tra xem thông báo lỗi có hiển thị hay không.
     */
    @Test
    public void testLoginWithEmptyCredentials() {
        logger.info("Bắt đầu test case: testLoginWithEmptyCredentials");
        loginPage.clickLoginButton();
        logger.warn("Thực hiện đăng nhập với thông tin đăng nhập trống.");
        String errorMessage = loginPage.getErrorMessage();
        logger.info("Thông báo lỗi hiển thị là: " + errorMessage);
        Assert.assertEquals(errorMessage, "Đã có lỗi xảy ra trong quá trình đăng nhập. Xin vui lòng thử lại vào lần sau!", "Thông báo lỗi không hiển thị hoặc không đúng cho thông tin đăng nhập trống.");
        logger.info("Test case testLoginWithEmptyCredentials đã hoàn thành thành công.");
    }
}
