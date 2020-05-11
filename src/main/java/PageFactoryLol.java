import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactoryLol {

	public WebDriver driver;
    private final Wait<WebDriver> wait;
	   
	@FindBy(name = "password")
	private WebElement pass;
	
	@FindBy(name = "username")
	private WebElement email;
	
	
//	PageFactoryLol(){
//		
//	}
	
	public PageFactoryLol(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
	}
	
	void login(String login, String password) {
		email.sendKeys(login);
		pass.sendKeys(password);	
	}
	
}
