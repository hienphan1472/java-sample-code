import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FrenzyAppBaseTest {
    protected AndroidDriver driver;
    static String HUB_URL = "http://127.0.0.1:4444/wd/hub";

    private static By agreeButton = By.id("com.shopify.frenzy.app:id/onboarding_button");
    private static By setupButton = By.id("com.shopify.frenzy.app:id/setup_button");
    private static By countdownText = By.id("com.shopify.frenzy.app:id/sale_details_state");
    private static By flashSaleEnterText = By.id("com.shopify.frenzy.app:id/feed_flashsale_enter_text");
    private static By sizeButton = By.id("com.shopify.frenzy.app:id/variant_text");
    private static By payWithGoogleButton = By.id("com.shopify.frenzy.app:id/pay_with_google_button");

    @Before
    public void setUp() {
        System.out.println("Do some setup stuff");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void scrollAndClick(String visibleText) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))").click();
    }

    private void sleep(Integer second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void performTest(AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        sleep(10);

        List<WebElement> sizeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(sizeButton));
        Assert.assertTrue(sizeList.size() > 0);
        for (WebElement e : sizeList) {
            if (e.getAttribute("enabled").equals("true")) {
                e.click();
                break;
            }
        }

        driver.findElement(payWithGoogleButton).click();

        sleep(10);

        // System.out.println("=======================");
        // System.out.println(driver.getPageSource());
    }
}
