package utilitys;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.Baselibrary;

public class ExtentReportManager implements ITestListener
{
	private static ExtentReports extent;
		private static ExtentTest test;
		public WebDriver driver;
		String reportName ;

		// ================= onStart =====================
		@Override
		public void onStart(ITestContext context) {
			
			// Generate unique report name with time stamp
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			 reportName = "TestReport_" + timeStamp + ".html";
			// Path where report will be generated
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);

			// Customize Report
			spark.config().setDocumentTitle("Automation Test Report");
			spark.config().setReportName("Functional Testing");
			spark.config().setTheme(Theme.DARK);
			spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			// Add environment info
			extent.setSystemInfo("Open Cart", "Demo Automation");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("Tester", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");

			String os = context.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", os);

			String browser = context.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);

			List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
			if (!includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includedGroups.toString());
			}

			System.out.println("====== Test Execution Started ======");
		}

		// ================= onTestStart =====================
		@Override
		public void onTestStart(ITestResult result) {
			test = extent.createTest(result.getClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.INFO, "Test Started: " + result.getName());
		}

		// ================= onTestSuccess =====================
		@Override
		public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS, result.getName()+"got successfully executed");
		}

		// ================= onTestFailure =====================
		@Override
		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, result.getName()+"got failed");
			test.log(Status.FAIL, "Reason: " + result.getThrowable().getMessage());
			

			// TODO: You can capture screenshot here and attach
			// Example:
			try {
		         String screenshotPath = new Baselibrary().captureScreen(result.getMethod().getMethodName());
		         test.addScreenCaptureFromPath(screenshotPath);
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}

		// ================= onTestSkipped =====================
		@Override
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
		}

		// ================= onFinish =====================
		@Override
		public void onFinish(ITestContext context) { 
			
			extent.flush(); // Write all data to report
			String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+reportName;
			File extentReport=new File(pathOfExtentReport);
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*try {
				URL url=new
						URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
				//Create the email message
				ImageHtmlEmail email=new ImageHtmlEmail();
				email.setDataSourceResolver(new DataSourceUrlResolver(url));
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("Sender email from which you want to send","Password of that email"));
				email.setSSLOnConnect(true);
				email.setFrom("from which email want to send");//Sender
				email.setSubject("Test Results");
				email.setMsg("Please find the Attached Report....");
				email.addTo("put the email where you want to share the email");//Receiver
				email.attach(url,"extent report","Please Check report...");
				email.send();//send the email
				
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
			System.out.println("====== Test Execution Finished ======");
		}
	}

