

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementPresentDuck {

	public WebDriver driver;
	private final Wait<WebDriver> wait;

	public ElementPresentDuck(WebDriver driver){
		this.driver = driver;
		driver.get("https://duckduckgo.com/");
		wait = new WebDriverWait(driver,10);
	}
	
	public void search(String search) throws Exception{
		driver.findElement(By.id("search_form_input_homepage")).sendKeys(search);
		driver.findElement(By.id("search_button_homepage")).click();
	}
	
	public String getResult(int id) {
		driver.findElement(By.id("r1-"+id)).click();
		return driver.getCurrentUrl();
	}
	
	public boolean isElementPresent(By by) {
        try{
            driver.findElement(by);
            return true;
        }catch(Exception e) {
        	return false;
        }
    }
}
