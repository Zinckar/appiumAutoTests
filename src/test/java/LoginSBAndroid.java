import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by ${bohdan.missurenko} on 11/9/2018.
 */
public class LoginSBAndroid {

    String MESSAGE_FROM_NOTIFICATION = "Some Error Occurred! Please try again later.";
    String ASSERT_MESSAGE = "Notification message not correct.";
    String MY_POINT_USER_NAME = "mypointsuser@sbxmail.com";
    String MY_POINT_USER_PASS = "mypointspass";
    String DEACTIVATE_USER_NAME = "deactivated@sbxmail.com";
    String DEACTIVATE_USER_PASS = "deactivateduser";
    String NOTIFICATION_ENTER_YOUR_PASS = "Please enter a password.";
    String NOTIFICATION_ENTER_YOUR_NAME = "Please enter an email address or swagname.";
    String USER_NAME_WITHOUT_AT_SIGN = "mypointsuser";
    String PASS_FOR_USER_WITHOUT_AT_SIGN = "mypointspass";

    DesiredCapabilities caps = new DesiredCapabilities();
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    void initSettings() {
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.prodege.swagbucksmobile");
        caps.setCapability("appActivity", "com.prodege.swagbucksmobile.sb.SplashSwagbucks");
        caps.setCapability("noReset", "false");
        caps.setCapability("newCommandTimeout", "600");
    }

    void startApp() throws MalformedURLException {
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 100);
        String giveFirstAgree = "com.android.packageinstaller:id/permission_allow_button";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(giveFirstAgree))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(giveFirstAgree))).click();
        String buttonLogin = "com.prodege.swagbucksmobile:id/welcome_footer_textViewBottomLeft";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(buttonLogin))).click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    void checkCorrectEmail(String userName, String userPass, String messageMustBe, String exeptionMessage) {
        String loginInput = "com.prodege.swagbucksmobile:id/activity_login_signup_editTextUserName";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loginInput)));
        driver.findElements(By.id(loginInput)).get(0).clear();
        driver.findElements(By.id(loginInput)).get(0).sendKeys(userName);
        String passInput = "com.prodege.swagbucksmobile:id/activity_login_signup_editTextPassword";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passInput)));
        driver.findElements(By.id(passInput)).get(0).clear();
        driver.findElements(By.id(passInput)).get(0).sendKeys(userPass);
        String submit = "com.prodege.swagbucksmobile:id/login_Submit";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(submit))).click();
        String message = "android:id/message";
        String notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(message))).getText();
        boolean isDeactivateMessageCorrect = messageMustBe.equals(notification);
        assertTrue(exeptionMessage, isDeactivateMessageCorrect);
        String okButton = "android:id/button1";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(okButton))).click();
    }
}


