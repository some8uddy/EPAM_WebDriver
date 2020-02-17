package bring_it_on;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinResultPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='code_buttons']/span[2]/a")
    private WebElement syntaxType;

    @FindBy(id = "paste_code")
    private WebElement textArea;

    public PastebinResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        new WebDriverWait(driver, 20)
            .until(ExpectedConditions.not(ExpectedConditions.titleContains("Spam")));
        return driver.getTitle();
    }

    public String getSyntaxType() {
        return syntaxType.getText();
    }

    public String getDisplayedCode() {
        return textArea.getText();
    }
}
