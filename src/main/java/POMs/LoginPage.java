package POMs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(),'OLMS Admin')]")
    private WebElement pageTitleText;

    @FindBy(id = "emailOrUsername")
    private WebElement usernameInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[@type='submit' and text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//h5[contains(text(),'Đã có lỗi')]")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        clear(usernameInputField);
        sendKeys(usernameInputField, username);
    }

    public void enterPassword(String password) {
        clear(passwordInputField);
        sendKeys(passwordInputField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        clear(usernameInputField);
        clear(passwordInputField);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        clear(loginErrorMessage);
        if (isElementDisplayed(loginErrorMessage)) {
            return getText(loginErrorMessage);
        }
        return null;
    }


}
