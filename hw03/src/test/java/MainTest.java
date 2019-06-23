
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainTest {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MainTest.class);

    @BeforeClass
    public static void wdSetup(){
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void instantiateDriver(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        driver.get("http://localhost/Testlink");
        driver.findElement(By.id("tl_login")).sendKeys("tester");
        driver.findElement(By.id("tl_password")).sendKeys("tester");
        driver.findElement(By.cssSelector("input[type='submit'")).click();

        switchToTitleBar();
        driver.findElement(By.cssSelector("img[title='Тесты']")).click();

        switchToWorkFrame();
        driver.findElement(By.cssSelector("img[title='Действия'")).click();
        driver.findElement(By.id("new_testsuite")).click();
        driver.findElement(By.id("name")).sendKeys("Test suite name");
        driver.findElement(By.cssSelector("input[name='add_testsuite_button']")).click();
        createTestCase("First test case");
        createTestCase("Second test case");
    }


    public static void createTestCase(String TestCaseName) {
        switchToTreeFrame();
        driver.findElement(By.xpath("//*[@id='extdd-6']/span")).click();
        switchToWorkFrame();
        driver.findElement(By.cssSelector("img[title='Действия'")).click();
        driver.findElement(By.id("create_tc")).click();
        driver.findElement(By.id("testcase_name")).sendKeys(TestCaseName);
        driver.findElement(By.id("do_create_button")).click();
        driver.findElement(By.cssSelector("input[name='create_step'")).click();

        driver.findElement(By.id("do_update_step")).click();
        driver.findElement(By.id("do_update_step")).click();
        driver.findElement(By.id("do_update_step")).click();
        driver.findElement(By.id("do_update_step_and_exit")).click();
    }

    public static void switchToTitleBar() {
        WebElement titleBar =  driver.findElement(By.cssSelector("frame[name='titlebar']"));
        driver.switchTo().frame(titleBar);
    }

    public static void switchToMainFrame() {
        driver.switchTo().defaultContent();
        WebElement mainFrame =  driver.findElement(By.cssSelector("frame[name='mainframe']"));
        driver.switchTo().frame(mainFrame);
    }

    public static void switchToWorkFrame() {
        switchToMainFrame();
        WebElement workFrame =  driver.findElement(By.cssSelector("frame[name='workframe']"));
        driver.switchTo().frame(workFrame);
    }

    public static void switchToTreeFrame() {
        switchToMainFrame();
        WebElement treeFrame =  driver.findElement(By.cssSelector("frame[name='treeframe']"));
        driver.switchTo().frame(treeFrame);
    }

}

