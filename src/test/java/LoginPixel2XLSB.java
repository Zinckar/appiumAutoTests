import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by ${bohdan.missurenko} on 11/12/2018.
 */
public class LoginPixel2XLSB extends LoginSBAndroid {

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initSettings();
        caps.setCapability("deviceName", "Pixel 2 XL");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        startApp();
    }

    @Test
    public void loginWithoutNameAndPass() {
        checkCorrectEmail("", "", NOTIFICATION_ENTER_YOUR_NAME, "");
    }

    @Test
    public void loginWithoutPass() {
        checkCorrectEmail(DEACTIVATE_USER_NAME, "", NOTIFICATION_ENTER_YOUR_PASS, ASSERT_MESSAGE);

    }

    @Test
    public void loginDeactivateUser() {// must be deactivate notification
        checkCorrectEmail(DEACTIVATE_USER_NAME, DEACTIVATE_USER_PASS, MESSAGE_FROM_NOTIFICATION, ASSERT_MESSAGE);

    }

    @Test
    public void loginWrong() {
        checkCorrectEmail(USER_NAME_WITHOUT_AT_SIGN, PASS_FOR_USER_WITHOUT_AT_SIGN, MESSAGE_FROM_NOTIFICATION, ASSERT_MESSAGE);
    }

    @Test
    public void loginWithoutAtSign() {// must be "not had '@' " --> notification
        checkCorrectEmail(USER_NAME_WITHOUT_AT_SIGN, PASS_FOR_USER_WITHOUT_AT_SIGN, MESSAGE_FROM_NOTIFICATION, ASSERT_MESSAGE);
    }
}
