package hurt_me_plenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement outerFrame;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement innerFrame;

    @FindBy(xpath = "//*[@id=\"compute\"]/md-toolbar/h2")
    private WebElement estimateHeader;

    @FindBy(xpath = "//md-list-item//div[contains(.,'VM class')]")
    private WebElement vmClassField;

    @FindBy(xpath = "//md-list-item//div[contains(.,'Instance type')]")
    private WebElement instanceTypeField;

    @FindBy(xpath = "//md-list-item//div[contains(.,'Region')]")
    private WebElement regionField;

    @FindBy(xpath = "//md-list-item//div[contains(.,'local SSD')]")
    private WebElement ssdField;

    @FindBy(xpath = "//md-list-item//div[contains(.,'Commitment term')]")
    private WebElement committedUsageField;

    @FindBy(xpath = "//*[@id='resultBlock']//h2[contains(.,'Estimated Cost')]")
    private WebElement estimatedCostField;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isEstimateHeaderTextEqualTo(String expectedHeaderName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerFrame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerFrame));
        wait.until(ExpectedConditions.textToBePresentInElement(estimateHeader, expectedHeaderName));
        driver.switchTo().defaultContent();
        return true;
    }

    public String getActualVmClass() {
        return getActualText(vmClassField);
    }

    public String getActualInstanceType() {
        return getActualText(instanceTypeField);
    }

    public String getActualDatacenterLoocation() {
        return getActualText(regionField);
    }

    public String getActualSsd() {
        return getActualText(ssdField);
    }

    public String getActualCommittedUsage() {
        return getActualText(committedUsageField);
    }

    public String getActualEstimatedCost() {
        return getActualText(estimatedCostField);
    }

    private String getActualText(WebElement webElement) {
        driver.switchTo().frame(outerFrame).switchTo().frame(innerFrame);
        String actualText = webElement.getText();
        driver.switchTo().defaultContent();
        return actualText;
    }
}
