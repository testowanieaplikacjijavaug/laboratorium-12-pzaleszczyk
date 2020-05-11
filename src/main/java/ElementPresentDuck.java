

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		 (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return !d.getCurrentUrl().contains("duck");
	            }
	        });
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
