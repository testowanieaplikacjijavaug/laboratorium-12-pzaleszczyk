import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddresBook extends LoadableComponent<AddresBook> {

	private final WebDriver driver;

	@FindBy(xpath = "/html/body/div[1]/div[1]")
	private WebElement validator_error;
	
	@FindBy(xpath = "/html/body/div[1]/div[1]")
	private WebElement error;
	
	@FindBy(id = "sign-in")
	private WebElement loginPage;
	
	@FindBy(id = "session_email")
	private WebElement _user;
	@FindBy(id = "session_password")
	private WebElement _pass;
	
	@FindBy(id = "user_email")
	private WebElement user;
	@FindBy(id = "user_password")
	private WebElement pass;
	
	@FindBy(name = "commit")
	private WebElement commit;
	
	

	public AddresBook(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://a.testaddressbook.com/");
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get("http://a.testaddressbook.com/");
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		if(!url.contains("addressbook")) {
			throw new Error("Error");
		}
	}

	public void login(String name, String password) {
		_user.sendKeys(name);
		_pass.sendKeys(password);
		commit.click();
	}
	
	public void register(String name, String password) {
		user.sendKeys(name);
		pass.sendKeys(password);
		commit.click();
	}
	
	public void commit() {
		driver.findElement(By.xpath("//*[@id=\"new_address\"]/div[17]/input"));
		commit.submit();
	}

	public void loginPage() {
		driver.get("http://a.testaddressbook.com/sign_in");
	}
	
	public void addressPage() {
		driver.get(driver.getCurrentUrl()+"/addresses");
	}
	
	public void registerPage() {
		driver.get("http://a.testaddressbook.com/sign_up");
	}
	
	public void newAddress() {
		driver.get(driver.getCurrentUrl()+"/new");
		System.out.println(driver.getCurrentUrl());
	}
	
	public String getError() {
		return error.getText();
	}

	public Object getAddress() {
		return driver.getCurrentUrl();
	}
	
	public String getErrorCount() {
		return driver.findElement(By.xpath("//*[@id='error_explanation']/h4")).getText().substring(0,1);
	}
	
}