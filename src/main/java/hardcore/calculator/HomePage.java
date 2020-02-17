package hardcore.calculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage(String url) {
        driver.get(url);
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.elementToBeClickable(searchField));
        return this;
    }

    public HomePage pasteSearchQuery(String message) {
        searchField.click();
        searchField.sendKeys(message);
        return this;
    }

    public SearchResultsPage submitQuery() {
        searchField.submit();
        return new SearchResultsPage(driver);
    }
}
