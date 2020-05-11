import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoadedClassTest {

	
	private static WebDriver driver;
	private LoadedClass page;
	
	@BeforeAll
	public static void bef() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
		driver = new ChromeDriver(options);
		
	}
	
	@BeforeEach
	public void setup() {
		//Funkcja get() przechodzi na odpowiednia strone i sprawdza bledy i zwraca obiekt strony.
		page = new LoadedClass(driver).get();
	}
	
	@Test
	public void loadedGet() {
		LoadedClass a = page.get();
		assertEquals(a, page);
	}
	
	@Test
	public void loginNoPassword() {
		page.login("Lma", "");
		assertEquals("Epic sadface: Password is required", page.getError());
	}
	
	@Test
	public void loginNoPasswordNoLogin() {
		page.login("", "");
		assertEquals("Epic sadface: Username is required", page.getError());
	}
	
	@Test
	public void loginNoLogin() {
		page.login("", "dasdsa");
		assertEquals("Epic sadface: Username is required", page.getError());
	}
	
	@Test
	public void loginNothing() {
		page.login("", "");
		assertEquals("Epic sadface: Username is required", page.getError());
	}
	
	@Test
	public void bannedUser() {
		page.login("locked_out_user", "secret_sauce");
		assertEquals("Epic sadface: Sorry, this user has been locked out.", page.getError());
	}
	
	@Test
	public void redirectTest() {
		page.login("standard_user", "secret_sauce");
		assertEquals("https://www.saucedemo.com/inventory.html", page.getAddress());
	}
	
}
