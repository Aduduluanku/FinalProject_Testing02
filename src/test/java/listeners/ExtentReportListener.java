package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ultilities.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    public static ExtentReports extent = ExtentManager.createInstance(); public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName()); test.set(extentTest);
    }
    public void onTestSuccess(ITestResult result) { test.get().log(Status.PASS, "Test Passed");
    }
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            String screenshotPath = "./screenshots/" + result.getMethod().getMethodName() + ".png";
            extentTest.addScreenCaptureFromPath(screenshotPath);
        } else {
            System.err.println("ExtentTest instance is null for test: " + result.getMethod().getMethodName());
        }
    }
    public void onTestSkipped(ITestResult result) { test.get().log(Status.SKIP, "Test Skipped");
    }
    public void onFinish(ITestContext context) { extent.flush();
    }
    public static ExtentTest getTest() { return test.get();
    }
}