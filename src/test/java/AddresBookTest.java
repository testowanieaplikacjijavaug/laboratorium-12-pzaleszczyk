import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddresBookTest {

	
	private static WebDriver driver;
	private AddresBook page;
	
	@BeforeEach
	public void setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//Funkcja get() przechodzi na odpowiednia strone i sprawdza bledy i zwraca obiekt strony.
		page = new AddresBook(driver).get();
	}
	
	@Test
	public void loginNoPassword() {
		page.loginPage();
		page.login("Lma@a", "");
		assertEquals("Bad email or password.", page.getError());
	}
	
	@Test
	public void loginNoPasswordNoLogin() {
		page.loginPage();
		page.login("", "");
		assertEquals("Bad email or password.", page.getError());
	}
	
	@Test
	public void loginNoLogin() {
		page.loginPage();
		page.login("", "dasdsa");
		assertEquals("Bad email or password.", page.getError());
	}
	
	@Test
	public void loginNothing() {
		page.loginPage();
		page.login("", "");
		assertEquals("Bad email or password.", page.getError());
	}
	
	@Test
	public void registerRedirectTest() {
		page.registerPage();
		page.register("Test@wp.pla", "Test123!");
		page.loginPage();
		page.login("Test@wp.pla", "Test123!");
		page.addressPage();
		
		assertTrue(driver.getCurrentUrl().endsWith("addresses"));
	}
	
	@Test
	public void AddAdressTest() {
		page.registerPage();
		page.register("Test@wp.pla", "Test123!");
		page.loginPage();
		page.login("Test@wp.pla", "Test123!");
		
		page.addressPage();
		page.newAddress();
		page.commit();
		assertEquals("5", page.getErrorCount());
	}


	
}
