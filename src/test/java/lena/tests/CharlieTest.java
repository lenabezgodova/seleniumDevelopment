package lena.tests;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import lena.pages.CharlieLoginPage;
import lena.pages.CharlieMainPage;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
class CharlieTest {
    private static final String CORRECT_EMAIL = "vania+elena@pluspeter.com";
    private static final String CORRECT_PASSWORD = "pluspeter";
    private static final String NAME_OF_THE_PRIVATE_STUDENT_PAGE = "Charly.education";

    private CharlieMainPage charlieMainPage;
    private WebDriver webDriver;

    @BeforeEach
    void init() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\lenab\\IdeaProjects\\seleniumverimi\\drivers\\geckodriver.exe");
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        charlieMainPage = new CharlieMainPage(webDriver);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    void shouldLoginSuccessfully() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        charlieMainPage.navigate();

        CharlieLoginPage loginPage = openLoginPage();
        loginPage.waitTillOpen();

        loginPage.fillEmail(CORRECT_EMAIL);
        loginPage.fillPassword(CORRECT_PASSWORD);
        loginPage.login();

        wait.until(ExpectedConditions.titleIs(NAME_OF_THE_PRIVATE_STUDENT_PAGE));
        Assertions.assertThat(webDriver.getTitle()).isEqualTo(NAME_OF_THE_PRIVATE_STUDENT_PAGE);

    }

    private CharlieLoginPage openLoginPage() {
        Set<String> originalTabs = webDriver.getWindowHandles();

        charlieMainPage.openLoginPage();
        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> webDriver.getWindowHandles().size() == originalTabs.size() + 1);

        Set<String> currentTabs = webDriver.getWindowHandles();
        currentTabs.removeAll(originalTabs);
        String loginTabHandle = currentTabs.iterator().next();
        log.info("Switching to login tab handle {}", loginTabHandle);
        webDriver.switchTo().window(loginTabHandle);

        return new CharlieLoginPage(webDriver);
    }
}
