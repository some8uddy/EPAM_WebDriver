package hurt_me_plenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PageTest {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";
    private static final String NUMBER_OF_INSTANCES = "4";
    private static final String OPERATING_SYSTEM = "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";
    private static final String VM_CLASS = "Regular";
    private static final String INSTANCE_TYPE = "n1-standard-8";
    private static final String NUMBER_OF_GPU = "1";
    private static final String GPU_TYPE = "NVIDIA Tesla V100";
    private static final String LOCAL_SSD = "2x375 GB";
    private static final String DATACENTER_LOCATION = "Frankfurt";
    private static final String COMMITED_USAGE = "1 Year";
    private static final String EXPECTED_ESTIMATE_HEADER_NAME = "Compute Engine";
    private static final String ESTIMATED_COST = "USD 1,082.77 per 1 month";

    private WebDriver driver;
    private SearchResultsPage searchResultsPage;
    private CalculatorPage calculatorPage;
    private ResultPage resultPage;

    @BeforeTest(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void testSubmitSearchQuery() {
        searchResultsPage = new HomePage(driver)
            .openPage(HOMEPAGE_URL)
            .pasteSearchQuery(SEARCH_QUERY)
            .submitQuery();
        String actualSearchMessage = searchResultsPage.getSearchMessage();
        Assert.assertTrue(actualSearchMessage.contains(SEARCH_QUERY));
    }

    @Test(priority = 2)
    public void testOpenCalculatorPage() {
        calculatorPage = searchResultsPage.clickLink();
        String actualFormName = calculatorPage.getFrameName();
        Assert.assertTrue(actualFormName.contains(SEARCH_QUERY));
    }

    @Test(priority = 3)
    public void testSetData() {
        resultPage = calculatorPage
            .setNumberOfInstances(NUMBER_OF_INSTANCES)
            .selectOperatingSystem(OPERATING_SYSTEM)
            .selectVmClass(VM_CLASS)
            .selectInstanceType(INSTANCE_TYPE)
            .checkAddGpusBox()
            .selectNumberOfGpu(NUMBER_OF_GPU)
            .selectGpuType(GPU_TYPE)
            .selectLocalSsd(LOCAL_SSD)
            .selectDatacentrLocation(DATACENTER_LOCATION)
            .selectCommitedUsage(COMMITED_USAGE)
            .submit();
        Assert.assertTrue(resultPage.isEstimateHeaderTextEqualTo(EXPECTED_ESTIMATE_HEADER_NAME));
    }

    @Test(priority = 4)
    public void testVmClass() {
        String actualVmClass = resultPage.getActualVmClass();
        Assert.assertTrue(areEqual(actualVmClass, VM_CLASS));
    }

    @Test(priority = 4)
    public void testInstanceType() {
        String actualInstanceType = resultPage.getActualInstanceType();
        Assert.assertTrue(areEqual(actualInstanceType, INSTANCE_TYPE));
    }

    @Test(priority = 4)
    public void testDatacenterLoocation() {
        String actualDatacenterLoocation = resultPage.getActualDatacenterLoocation();
        Assert.assertTrue(areEqual(actualDatacenterLoocation, DATACENTER_LOCATION));
    }

    @Test(priority = 4)
    public void testSsd() {
        String actualSsd = resultPage.getActualSsd();
        Assert.assertTrue(areEqual(actualSsd, LOCAL_SSD));
    }

    @Test(priority = 4)
    public void testCommittedUsage() {
        String actualCommittedUsage = resultPage.getActualCommittedUsage();
        Assert.assertTrue(areEqual(actualCommittedUsage, COMMITED_USAGE));
    }

    @Test(priority = 4)
    public void testEstimatedCost() {
        String actualEstimatedCost = resultPage.getActualEstimatedCost();
        Assert.assertTrue(areEqual(actualEstimatedCost, ESTIMATED_COST));
    }

    private boolean areEqual(String actual, String expected) {
        return actual.toLowerCase().contains(expected.toLowerCase());
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}