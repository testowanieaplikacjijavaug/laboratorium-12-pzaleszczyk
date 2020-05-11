

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementPresentDuckTest {

	private static WebDriver driver;
	private ElementPresentDuck page;


	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
		driver = new ChromeDriver(options);
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void findFirst() throws Exception {
		page = new ElementPresentDuck(driver);
		page.search("mfi.ug.edu.pl");
		String url = page.getResult(0);
		assertEquals("https://mfi.ug.edu.pl/", url);
	}

	@Test
	public void findThird() throws Exception {
		page = new ElementPresentDuck(driver);
		page.search("mfi.ug.edu.pl");
		String url = page.getResult(2);
		assertEquals("https://pe.ug.edu.pl/", url);
	}


	@Test
	public void noResult() {
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			page = new ElementPresentDuck(driver);
			page.search("//!?@!?#!?");
			String url = page.getResult(0);
			assertEquals("https://mfi.ug.edu.pl/", url);
		});
	}
	

	@Test
	public void byClassName() throws Exception {
		page = new ElementPresentDuck(driver);
		page.search("afsddfs");
		String name = driver.findElement(By.className("result__url__full")).getText();
		
		assertEquals("/profile/20707/",name);
	}
	
	@Test
	public void elementPresentTest() {
		page = new ElementPresentDuck(driver);
		boolean resulttrue = page.isElementPresent(By.id("search_form_input_homepage"));
		boolean resultfalse = page.isElementPresent(By.id("search_form_input_homepage_false"));
		assertAll(
			() -> assertTrue(resulttrue),
			() ->assertTrue(!resultfalse)
		);
	}
	
	
}
