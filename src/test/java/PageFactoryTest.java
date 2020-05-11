import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.extension.ExtendWith;
import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class PageFactoryTest {



	private WebDriver driver;

	public PageFactoryTest(ChromeDriver driver) {
		this.driver = driver;
	}

	@Test
	public void test9Gag() throws Exception {
		PageFactoryLol page = PageFactory.initElements(driver, PageFactoryLol.class);
		driver.navigate().to("https://9gag.com/login");
		page.login("Test", "Test");
		driver.findElement(By.xpath("//*[@id=\"login-email\"]/div[3]/input")).click();

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//*[@id=\"login-email\"]/div[2]/p")).getText().length() != 0;
			}
		});
		WebElement error = driver.findElement(By.xpath("//*[@id=\"login-email\"]/div[2]/p"));
		assertEquals("Your email or password were incorrect.", error.getText());
	}

	@Test
	public void testReddit() throws Exception {
		PageFactoryLol page = PageFactory.initElements(driver, PageFactoryLol.class);
		driver.get("https://www.reddit.com/login/");
		page.login("Test1", "Test");
		driver.findElement(By.className("AnimatedForm__submitButton")).click();

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[2]/div")).getText().length() != 0;
			}
		});
		WebElement error = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/fieldset[2]/div"));
		assertEquals("Incorrect Password", error.getText());

	}

}
