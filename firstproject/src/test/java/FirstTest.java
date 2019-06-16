
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(FirstTest.class);

    @BeforeClass
    public static void wdSetup(){
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void instantiateDriver(){
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void openSite(){
        driver.get("https://otus.ru/");
        String pageTitle = driver.getTitle();
        logger.info("Page otus is open");
        logger.info("INFO log");
        logger.debug("DEBUG log");
        logger.error("ERROR log");
        logger.fatal("FATAL log");
        logger.warn("WARN log");
        assert pageTitle.equals("OTUS - Онлайн-образование");
    }
}
