import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AboutRent;
import pages.CustomerInformation;
import pages.FAQPage;
import pages.MainPage;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    CustomerInformation customerInformation;
    AboutRent aboutRent;
    FAQPage faqPage;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, 5);
        //    String browser = System.getProperty("browser", "chrome");
        //    if (browser.equals("chrome")) {
        //        startBrowserChrome();
        //    } else if (browser.equals("firefox")) {
        //        startBrowserFirefox();
        //    }
        mainPage = new MainPage(driver);

        customerInformation = new CustomerInformation(driver);

        aboutRent = new AboutRent(driver);

        faqPage = new FAQPage(driver);
        //}

        // МЕТОД ДЛЯ ХРОМА
        // public void startBrowserChrome() {
        //   WebDriverManager.chromedriver().setup();

        //}
        //МЕТОД ДЛЯ ФАЙРФОКС: Я НЕ СТАЛА ДОБАВЛЯТЬ ФАЙРФОКС ПОТОМУ ЧТО ОН НЕ РАБОТАЛ, В ЗАДАНИИ СКАЗАНО ЧТО СДАВАТЬ НУЖНО В CHROME
        //public void startBrowserFirefox() {
        //  WebDriverManager.firefoxdriver().setup();

        // FirefoxOptions options = new FirefoxOptions();
        // options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");

        // driver = new FirefoxDriver(options);
        // wait = new WebDriverWait(driver, 5);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}