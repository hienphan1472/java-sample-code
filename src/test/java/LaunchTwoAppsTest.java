import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LaunchTwoAppsTest {
    private IOSDriver driver;
    private AppiumServer appiumServer;

    public DesiredCapabilities getDefaultDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Hulk-phone");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.3");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.UDID, "d2eaf15d6eb6009c2576dd63add4ae4b495347dd");

        return capabilities;
    }

    public void launchSettingsApp() {
        try {
            DesiredCapabilities capabilities = getDefaultDesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.APP, "Settings");
            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void launchNotesApp() {
        try {
            DesiredCapabilities capabilities = getDefaultDesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.APP, "com.apple.mobilenotes");
            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        appiumServer = AppiumServer.getInstance();
        appiumServer.startServer();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        appiumServer.stopServer();
    }

    @Test
    public void testLaunch2Apps() {
        launchSettingsApp();
        launchNotesApp();
        launchSettingsApp();
    }
}
