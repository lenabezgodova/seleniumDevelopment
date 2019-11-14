package lena.pages;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class CharlieLoginPage {

    private final WebDriver webDriver;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(className = "login-button")
    private WebElement loginButton;

    @FindBy(tagName = "body")
    private WebElement body;

    public CharlieLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void waitTillOpen() {
        log.info("waiting login page to be open");
        WebDriverWait pageLoadWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        try {
            pageLoadWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        } catch (TimeoutException e) {
            log.error("timed out load a login page, sending the ESCAPE command to stop loading the page");
            body.sendKeys(Keys.ESCAPE);
            pageLoadWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        }
        log.info("login page is displayed");
    }

    public void fillEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void fillPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void login() {
        loginButton.submit();
    }
}
