package bring_it_on;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PastebinHomePageTest {

    private static final String TITLE = "how to gain dominance among developers";
    private static final String SYNTAX_TYPE = "Bash";
    private static final String MESSAGE =
        "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private WebDriver driver;
    private PastebinResultPage createdPaste;

    @BeforeTest(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @BeforeTest(alwaysRun = true)
    public void createPaste() {
        createdPaste = new PastebinHomePage(driver)
            .openPage()
            .pasteMessage(MESSAGE)
            .setSyntaxType(SYNTAX_TYPE)
            .pasteExpiration(EXPIRATION_TIME)
            .pasteTitle(TITLE)
            .submit();
    }

    @Test
    public void checkPageTitle() {
        String actualTitle = createdPaste.getPageTitle();
        Assert.assertTrue(actualTitle.contains(TITLE), "Page title is wrong!!!");
    }

    @Test
    public void checkSyntaxHighlighting() {
        String actualSyntax = createdPaste.getSyntaxType();
        Assert.assertEquals(SYNTAX_TYPE, actualSyntax, "Syntax highlighting is wrong!!!");
    }

    @Test
    public void checkCodeIsCorrect() {
        String actualCode = createdPaste.getDisplayedCode();
        Assert.assertEquals(MESSAGE, actualCode, "Pasted code is wrong!!!");
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}