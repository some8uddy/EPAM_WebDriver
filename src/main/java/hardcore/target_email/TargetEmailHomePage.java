package hardcore.target_email;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TargetEmailHomePage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='mailAddress']")
    private WebElement emailAddress;

    @FindBy(xpath = "//*[@id='ui-id-1']")
    private WebElement googleEmailHeader;

    @FindBy(xpath = "//*[@id='mobilepadding']//td[2]//h3[1]")
    private WebElement estimatedCostField;

    public TargetEmailHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getEmailAddress() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailAddress));
        return emailAddress.getAttribute("value");
    }

    public String getEstimatedCost() {
        new WebDriverWait(driver, 600, 1000).until(ExpectedConditions.visibilityOf(googleEmailHeader));
        googleEmailHeader.sendKeys(Keys.ENTER);
        return estimatedCostField.getText();
    }
}
