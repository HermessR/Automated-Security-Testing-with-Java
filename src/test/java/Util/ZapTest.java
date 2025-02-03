package Util;
import com.google.common.annotations.VisibleForTesting ;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.zaproxy.clientapi.core.ClientApiException;
import static Util.ZapUtil.*;
import java.lang.reflect.Method;
public class ZapTest {

    WebDriver driver;
    private final String urlToTest = "https://ginandjuice.shop/";

    @BeforeMethod
    public void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setProxy(proxy);
        chromeOptions.setAcceptInsecureCerts(true);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void zapTest(){
        driver.get(urlToTest);
        waitTillPassiveScanCompleted();
    }

    @AfterMethod
    public void tearDown() throws ClientApiException {
        generateZapReport(urlToTest);

    }



}
