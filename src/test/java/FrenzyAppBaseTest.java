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
    private static By creditCardText = By.id("com.google.android.gms:id/credit_card_number");
    private static By expireDateText = By.id("com.google.android.gms:id/exp_date");
    private static By cvcText = By.id("com.google.android.gms:id/cvc");
    private static By billingAddressText = By.id("com.google.android.gms:id/summary_text_view_text");
    private static By streetAddressText = By.id("com.google.android.gms:id/address_field_address_line_1");
    private static By aptSuiteText = By.id("com.google.android.gms:id/address_field_address_line_2");
    private static By cityText = By.id("com.google.android.gms:id/address_field_locality");
    private static By provinceDropdown = By.id("com.google.android.gms:id/description");
    private static By postalCodeText = By.id("com.google.android.gms:id/address_field_postal_code");
    private static By recipientAddressLabel = By.id("com.google.android.gms:id/recipient_name");

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

        wait.until(ExpectedConditions.presenceOfElementLocated(creditCardText)).sendKeys("4242424242424242");
        wait.until(ExpectedConditions.presenceOfElementLocated(expireDateText)).sendKeys("12/21");
        wait.until(ExpectedConditions.presenceOfElementLocated(cvcText)).sendKeys("123");
        wait.until(ExpectedConditions.presenceOfElementLocated(billingAddressText)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(streetAddressText)).sendKeys("Cam Bac");
        wait.until(ExpectedConditions.presenceOfElementLocated(cityText)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(cityText)).sendKeys("Danang");
        wait.until(ExpectedConditions.presenceOfElementLocated(postalCodeText)).sendKeys("50000");
        wait.until(ExpectedConditions.presenceOfElementLocated(recipientAddressLabel)).click();
        System.out.println(driver.getContextHandles());
        System.out.println("=======================");
        System.out.println(driver.getPageSource());
    }
}
