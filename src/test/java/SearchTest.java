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
    public void testSearchDuckDuck() throws Exception {
        Search page = new Search(driver, "https://duckduckgo.com/");
        page.search("lmao");
        page.waitUntilTitle("at DuckDuckGo");
        assertTrue(page.assertTitleContains("lmao"));
    }

    @Test
    public void testSearchGoogle() throws Exception {
        Search page = new Search(driver, "https://google.com/");
        page.search("lmao");
        page.waitUntilTitle("Szukaj w Google");
        assertTrue(page.assertTitleContains("lmao"));
    }

}