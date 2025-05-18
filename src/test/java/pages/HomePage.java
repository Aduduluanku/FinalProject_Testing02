package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    //static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void openAddStaff() { //addstaff
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cấu hình']"))).click();
        driver.findElement(By.xpath("//span[text() = \"Cấu hình\"]")).click();
        driver.findElement(By.xpath("//p[text() = \"Quản lý nhân viên\"]")).click();
        driver.findElement(By.xpath("//button[text() = \"Thêm nhân viên\"]")).click();
    }

    public void addStaff(String firstMiddleName, String lastName, String userName, String pwd, String phoneNumber) {
        driver.findElement(By.xpath("//label[text()='Họ và tên đệm']/following-sibling::div//input")).sendKeys(firstMiddleName);
        driver.findElement(By.xpath("//label[text()='Tên']/following-sibling::div//input")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@value=\"OFFICE\"]")).click();
        //driver.findElement(By.xpath("//input[@value=\"ACADEMIC\"]")).click();


        //comment these code if you chose academic
        // **comment to not chose what you want
        driver.findElement(By.xpath("//div[@id='staff-detail-roles']")).click();
        driver.findElement(By.xpath("//li[@data-value='OW_FINANCE']")).click();
        driver.findElement(By.xpath("//li[@data-value='OW_CUSTOMER_SERVICE']")).click();
        driver.findElement(By.xpath("//div[@id='menu-']")).click();

        //comment these code if you chose office
//        driver.findElement(By.xpath("//li[@data-value='T_NATIVE']")).click();
//        driver.findElement(By.xpath("//li[@data-value='T_FOREIGNER']")).click();
//        driver.findElement(By.xpath("//li[@data-value='T_ASSISTANT']")).click();

        driver.findElement(By.xpath("//label[text()='Tên đăng nhập / Tài khoản']/following-sibling::div//input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div//input")).sendKeys(pwd);
        driver.findElement(By.xpath("//label[text()='SĐT']/following-sibling::div//input")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

        //sign out and login account had been created
        Actions actions = new Actions(driver);
        WebElement signOutButton = driver.findElement(By.xpath("//div[h5[text()= \"Đăng xuất\"]]"));
        actions.moveToElement(signOutButton).perform();
        driver.findElement(By.xpath("//div[h5[text()= \"Đăng xuất\"]]")).click();


    }

   //  edit staff
    public void editStaff(String firstMiddleName, String lastName, String userName, String pwd, String phoneNumber) {
        driver.findElement(By.xpath("//span[text() = \"Cấu hình\"]")).click();
        driver.findElement(By.xpath("//p[text() = \"Quản lý nhân viên\"]")).click();
        driver.findElement(By.xpath("(//button//*[name()='svg'][@data-testid='EditIcon'])[3]")).click();


        WebElement inputFirstMiddleName = driver.findElement(By.xpath("//label[text()='Họ và tên đệm']/following-sibling::div//input"));
        inputFirstMiddleName.sendKeys(Keys.CONTROL + "a");
        inputFirstMiddleName.sendKeys(Keys.DELETE);
        inputFirstMiddleName.sendKeys(firstMiddleName);


        WebElement inputLastName = driver.findElement(By.xpath("//label[text()='Tên']/following-sibling::div//input"));
        inputLastName.sendKeys(Keys.CONTROL + "a");
        inputLastName.sendKeys(Keys.DELETE);
        inputLastName.sendKeys(lastName);


        WebElement inputUserName = driver.findElement(By.xpath("//label[text()='Tên đăng nhập / Tài khoản']/following-sibling::div//input"));
        inputUserName.sendKeys(Keys.CONTROL + "a");
        inputUserName.sendKeys(Keys.DELETE);
        inputUserName.sendKeys(userName);


        WebElement inputPwd = driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div//input"));
        inputPwd.sendKeys(Keys.CONTROL + "a");
        inputPwd.sendKeys(Keys.DELETE);
        inputPwd.sendKeys(pwd);


        WebElement inputPhoneNumber = driver.findElement(By.xpath("//label[text()='SĐT']/following-sibling::div//input"));
        inputPhoneNumber.sendKeys(Keys.CONTROL + "a");
        inputPhoneNumber.sendKeys(Keys.DELETE);
        inputPhoneNumber.sendKeys(phoneNumber);
        //driver.findElement(By.xpath("//button[text()='Xác nhận']")).click();

        //sign out and login account had been created
        Actions actions = new Actions(driver);
        WebElement signOutButton = driver.findElement(By.xpath("//div[h5[text()= \"Đăng xuất\"]]"));
        actions.moveToElement(signOutButton).perform();
        driver.findElement(By.xpath("//div[h5[text()= \"Đăng xuất\"]]")).click();



    }

    //check filter work
    public void verifyFilterDisplay(String[] filterNames) throws InterruptedException {

        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//span[text() = \"Đào tạo\"]")).click();
        driver.findElement(By.xpath("//span[p[text()='DS Học viên']]")).click();
        driver.findElement(By.xpath("//div[@id=\"selected-columns\"]")).click();
        action.sendKeys(Keys.ENTER).perform();



        for (String filter : filterNames) {
            By filterList = By.xpath(String.format("//li[text()='%s']", filter));
            driver.findElement(filterList).click();
        }


        action.sendKeys(Keys.ESCAPE).perform();


        Thread.sleep(2000);

        SoftAssert softAssert = new SoftAssert();
        for (String boardTitle : filterNames) {
            By headerLocator = By.xpath(String.format("//th[contains(.,'%s')]", boardTitle.trim()));

            try {
                WebElement headerTitle = driver.findElement(headerLocator);
                String actualText = headerTitle.getText().trim();
                softAssert.assertTrue(
                        actualText.contains(boardTitle.trim()),
                        "Không tìm thấy hoặc sai tiêu đề: Expected = " + boardTitle + ", Actual = " + actualText
                );
            } catch (Exception e) {
                softAssert.fail(" Không tìm thấy thẻ header cho: " + boardTitle);
            }
        }

    }

    //search dropdown
    private By getInputByLabelInSearchFunction(String labelText) {
        return By.xpath(String.format("//div[label[contains(text(),'%s')]]//input", labelText));
    }

    private By getOptionalDropdownInSearchFunction(String choseDropdown) {
        return By.xpath(String.format("//div[label[contains(text(),'%s')]]//div[contains(@class,'select')]", choseDropdown));
    }

    private By getChoseDataInDropdownInSearchFunction(String choseDataInDropdown) {
        return By.xpath(String.format("//li[normalize-space()='%s']", choseDataInDropdown));
    }

    public void VerifySearchTest(String boxName, String value) throws InterruptedException {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("//span[text() = \"Đào tạo\"]")).click();
        driver.findElement(By.xpath("//span[p[text()='DS Học viên']]")).click();
        //action.sendKeys(Keys.ENTER).perform(); //if you just want to search by input pls open this

    }

    public void VerifyDropdownSearchTest(String dropdown, String dropdownData) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement choseDropdown = driver.findElement(getOptionalDropdownInSearchFunction(dropdown));
        choseDropdown.click();

        WebElement choseDataDropdown = driver.findElement(getChoseDataInDropdownInSearchFunction(dropdownData));
        choseDataDropdown.click();



    }



}


