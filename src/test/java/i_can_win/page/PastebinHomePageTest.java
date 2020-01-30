package i_can_win.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PastebinHomePageTest {

    private WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void pasteWasCreated() {
        boolean isPasteCreated = new PastebinHomePage(driver)
            .openPage()
            .pasteMessage("Hello from WebDriver")
            .pasteExpiration("10 Minutes")
            .pasteTitle("helloweb")
            .submit();

        Assert.assertTrue(isPasteCreated, "Paste was not created!!!");
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}