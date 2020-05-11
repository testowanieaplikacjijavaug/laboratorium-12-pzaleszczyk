import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageFactoryLol {

	public WebDriver driver;
	   
	@FindBy(name = "password")
	private WebElement pass;
	
	@FindBy(name = "username")
	private WebElement email;
	
	
//	PageFactoryLol(){
//		
//	}
	
	public PageFactoryLol(WebDriver driver){
		this.driver = driver;
	}
	
	void login(String login, String password) {
		email.sendKeys(login);
		pass.sendKeys(password);	
	}
	
}
