package i_can_win.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

public class PastebinHomePage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    @FindBy(id = "paste_code")
    private WebElement pasteField;

    @FindBy(name = "paste_expire_date")
    private WebElement expirationInput;

    @FindBy(name = "paste_name")
    private WebElement titleInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "success")
    private WebElement note;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
            .until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public PastebinHomePage pasteMessage(String message) {
        pasteField.sendKeys(message);
        return this;
    }

    public PastebinHomePage pasteExpiration(String expirationTerm) {
        Select droplist = new Select(expirationInput);
        droplist.selectByVisibleText(expirationTerm);
        return this;
    }

    public PastebinHomePage pasteTitle(String title) {
        titleInput.sendKeys(title);
        return this;
    }

    public boolean submit() {
        submitButton.submit();
        new WebDriverWait(driver, 20)
            .until(ExpectedConditions.not(ExpectedConditions.titleContains("Spam")));
        return note.getText().contains("Your guest paste has been posted.");
    }
}
