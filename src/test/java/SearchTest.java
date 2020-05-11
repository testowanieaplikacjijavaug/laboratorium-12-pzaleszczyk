import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

public class SearchTest {
	Search page;
    private static WebDriver driver;
    
    @BeforeEach
    public void setUpBeforeClass() throws Exception {
    	WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void duckduckTest() throws Exception {
        page = new Search(driver, "https://duckduckgo.com/");
        
        page.search("lmao");
        page.waitForTitleChange("lmao");
        assertTrue(page.titleContains("lmao"));
    }

    @Test
    public void googleTest() throws Exception {
        page = new Search(driver, "https://google.com/");
        
        page.search("lmao");
        page.waitForTitleChange("lmao");
        assertTrue(page.titleContains("lmao"));
    }

}