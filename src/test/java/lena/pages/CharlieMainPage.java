package lena.pages;

import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class CharlieMainPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "//*[contains(@class, 'preloader')]")
    private WebElement preloader;

    @FindBy(xpath = "//a[div[contains(string(), 'Login')] and contains(@class, 'login-button test new w-inline-block')]")
    private WebElement loginWithoutRegistrationButton;
    @FindBy(xpath = "//a[div[contains(string(), 'Login')] and contains(@class, 'menu-links w-inline-block')]")
    private WebElement loginWithRegistrationButton;

    private List<WebElement> loginButtons;

    public CharlieMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void navigate() {
        log.info("opening main page");

        webDriver.get("https://www.charly.education");
        WebDriverWait pageLoadWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        pageLoadWait.until(ExpectedConditions.invisibilityOf(preloader));
        log.info("opened main page");
    }

    public void openLoginPage() {
        log.info("requested to click login button, visibility: with = {}; w/o = {}",
                 loginWithRegistrationButton.isDisplayed(),
                 loginWithoutRegistrationButton.isDisplayed());
        if (loginWithRegistrationButton.isDisplayed()) {
            log.info("clicking the one with registration");
            loginWithRegistrationButton.click();
        } else {
            log.info("clicking the one without registration");
            loginWithoutRegistrationButton.click();
        }

    }
}

