package hurt_me_plenty;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement outerFrame;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement innerFrame;

    @FindBy(xpath = "//md-toolbar//h2")
    private WebElement frameHeader;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement instancesField;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement osDropDown;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.class']")
    private WebElement mashineClassDropDown;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']")
    private WebElement instanceTypeDropDown;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpusCheckBox;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement gpuCountDropDown;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuTypeDropDown;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement ssdDropDown;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocationDropDown;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement commitedUsageDropDown;

    @FindBy(xpath = "//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple']")
    private WebElement addToEstimationButton;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFrameName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerFrame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerFrame));
        String frameName = frameHeader.getText();
        driver.switchTo().defaultContent();
        return frameName;
    }

    public CalculatorPage setNumberOfInstances(String numberOfInstances) {
        driver.switchTo().frame(outerFrame).switchTo().frame(innerFrame);
        instancesField.sendKeys(numberOfInstances);
        driver.switchTo().defaultContent();
        return this;
    }

    public CalculatorPage selectOperatingSystem(String operatingSystem) {
        selectFromDropList(osDropDown, operatingSystem);
        return this;
    }

    public CalculatorPage selectVmClass(String vmClass) {
        selectFromDropList(mashineClassDropDown, vmClass);
        return this;
    }

    public CalculatorPage selectInstanceType(String instanceType) {
        selectFromDropList(instanceTypeDropDown, instanceType);
        return this;
    }

    public CalculatorPage checkAddGpusBox() {
        driver.switchTo().frame(outerFrame).switchTo().frame(innerFrame);
        addGpusCheckBox.sendKeys(Keys.ENTER);
        driver.switchTo().defaultContent();
        return this;
    }

    public CalculatorPage selectNumberOfGpu(String numberOfGpu) {
        selectFromDropList(gpuCountDropDown, numberOfGpu);
        return this;
    }

    public CalculatorPage selectGpuType(String gpuType) {
        selectFromDropList(gpuTypeDropDown, gpuType);
        return this;
    }

    public CalculatorPage selectLocalSsd(String ssdSetting) {
        selectFromDropList(ssdDropDown, ssdSetting);
        return this;
    }

    public CalculatorPage selectDatacentrLocation(String datacenterLocation) {
        selectFromDropList(datacenterLocationDropDown, datacenterLocation);
        return this;
    }

    public CalculatorPage selectCommitedUsage(String commitedTime) {
        selectFromDropList(commitedUsageDropDown, commitedTime);
        return this;
    }

    public ResultPage submit() {
        driver.switchTo().frame(outerFrame).switchTo().frame(innerFrame);
        addToEstimationButton.sendKeys(Keys.ENTER);
        driver.switchTo().defaultContent();
        return new ResultPage(driver);
    }

    private void selectFromDropList(WebElement webElement, String selection) {
        driver.switchTo().frame(outerFrame).switchTo().frame(innerFrame);
        webElement.sendKeys(Keys.ENTER);
        String xpathLocator = String.format("//div[@class='md-select-menu-container md-active md-clickable']" +
            "//md-option[contains(.,'%s')]", selection);
        WebElement option = new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
        option.sendKeys(Keys.ENTER);
        driver.switchTo().defaultContent();
    }
}
