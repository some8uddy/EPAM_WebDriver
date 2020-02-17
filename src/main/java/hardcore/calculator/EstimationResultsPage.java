package hardcore.calculator;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EstimationResultsPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement outerFrame;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement innerFrame;

    @FindBy(xpath = "//*[@id='compute']/md-toolbar/h2")
    private WebElement estimateHeader;

    @FindBy(xpath = "//*[@id=\"resultBlock\"]//h2[contains(.,'Total Estimated Cost')]")
    private WebElement estimatedCostField;

    @FindBy(xpath = "//*[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']//h2")
    private WebElement emailEstimateForm;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    private WebElement targetEmailField;

    @FindBy(xpath = "//form[@name='emailForm']//button[contains(.,'Send')]")
    private WebElement sendEmailButton;


    public EstimationResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isEstimateHeaderTextEqualTo(String expectedHeaderName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerFrame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerFrame));
        wait.until(ExpectedConditions.textToBePresentInElement(estimateHeader, expectedHeaderName));
        switchToDefaultContent();
        return true;
    }

    public String getEstimatedCost() {
        switchToFrame();
        String estimatedCost = estimatedCostField.getText();
        switchToDefaultContent();
        return estimatedCost;
    }

    public EstimationResultsPage openEmailEstimateForm() {
        switchToFrame();
        emailEstimateButton.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.textToBePresentInElement(emailEstimateForm, "Email Your Estimate"));
        switchToDefaultContent();
        return this;
    }

    public void pasteTargetEmail(String targetEmailAddress) {
        switchToFrame();
        targetEmailField.sendKeys(targetEmailAddress);
        switchToDefaultContent();
    }

    public void sendEmail() {
        switchToFrame();
        sendEmailButton.sendKeys(Keys.ENTER);
        switchToDefaultContent();
    }

    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    private void switchToFrame() {
        driver.switchTo().frame(outerFrame).switchTo().frame(innerFrame);
    }
}
