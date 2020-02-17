package hardcore;

import hardcore.calculator.EstimationResultsPage;
import hardcore.target_email.TargetEmailHomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class EmailHandler {
    private WebDriver driver;
    private EstimationResultsPage estimationResultsPage;
    private TargetEmailHomePage targetEmailHomePage;
    private String estimationWindowHandler;
    private String targetEmailWindowHandler;
    private String targetEmailAddress;

    public EmailHandler(WebDriver driver, EstimationResultsPage estimationResultsPage) {
        this.driver = driver;
        this.estimationResultsPage = estimationResultsPage;
    }

    public EmailHandler openTargetEmailPage() {
        estimationWindowHandler = driver.getWindowHandle();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://10minutemail.com','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String handler : tabs) {
            if (!estimationWindowHandler.equals(handler)) {
                targetEmailWindowHandler = handler;
            }
        }
        switchToEmailTab();
        targetEmailHomePage = new TargetEmailHomePage(driver);
        return this;
    }

    public EmailHandler copyEmailAddress() {
        switchToEmailTab();
        targetEmailAddress = targetEmailHomePage.getEmailAddress();
        return this;
    }

    public EmailHandler pasteEmailAddressToEstimationResultsForm() {
        switchToEstimationTab();
        estimationResultsPage.openEmailEstimateForm();
        estimationResultsPage.pasteTargetEmail(targetEmailAddress);
        return this;
    }

    public EmailHandler sendEstimationsToEmail() {
        switchToEstimationTab();
        estimationResultsPage.sendEmail();
        return this;
    }

    public String getEstimatedCostFromEmail() {
        switchToEmailTab();
        return targetEmailHomePage.getEstimatedCost();
    }

    private void switchToEmailTab() {
        driver.switchTo().window(targetEmailWindowHandler);
    }

    private void switchToEstimationTab() {
        driver.switchTo().window(estimationWindowHandler);
    }
}
