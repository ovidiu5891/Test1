package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utility.ConfigReader;
import test.Search;
import test.StiriPage;

public abstract class BasePage {

	public WebDriver driver;
	public static String browser;
	public static String environment;
	ConfigReader config;
	Search search;
	StiriPage stiri;

	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest test;

	String className = this.getClass().getSimpleName();

	

	
	public void getResult(ITestResult result) {
		
	}

	@Before
	public void Initialize() {
		
		htmlReporter = new ExtentHtmlReporter("C://Users//Ovidiu//workspace//test//Report//Report.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Automation Testing Report");
		test = report.createTest(className);

		WebDriver initDriver = null;

		if (initDriver == null) {

			if ((browser = System.getProperty("browser")) != null && browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\Ovidiu\\workspace\\test\\Driver\\geckodriver.exe");
				initDriver = new FirefoxDriver();

			} else if ((browser = System.getProperty("browser")) != null && browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\Ovidiu\\workspace\\test\\Driver\\chromedriver.exe");
				initDriver = new ChromeDriver();

			} else if (browser.equalsIgnoreCase("phantom")) {
				System.setProperty("phantomjs.binary.path",
						"C:\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
				initDriver = new PhantomJSDriver();

			} else {
				System.out.println("Error ! Browser could not be initialized !");
			}
			this.driver = initDriver;
		}

		environmentURL();

		search = PageFactory.initElements(driver, Search.class);
		stiri = PageFactory.initElements(driver, StiriPage.class);

	}

	public void environmentURL() {
		config = new ConfigReader();

		if (environment == null) {

			if ((environment = System.getProperty("environment")) != null && environment.equalsIgnoreCase("dev")) {
				driver.get(config.getKey("DEV_URL"));
			} else if ((environment = System.getProperty("environment")) != null
					&& environment.equalsIgnoreCase("acc")) {
				driver.get(config.getKey("ACC_URL"));
			} else {
				System.out.println("Invalid environment URL !");
			}
		}
	}


	@AfterMethod
	public void tearDown(ITestResult result) {
	
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		}
		
		
		driver.close();
		report.flush();
	
	

	}
}
