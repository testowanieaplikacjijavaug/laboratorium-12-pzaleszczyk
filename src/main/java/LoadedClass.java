import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadedClass extends LoadableComponent<LoadedClass> {

	private final WebDriver driver;

	@FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/h3")
	private WebElement error;
	@FindBy(id = "user-name")
	private WebElement user;
	@FindBy(id = "password")
	private WebElement pass;
	@FindBy(className = "btn_action")
	private WebElement button;

	public LoadedClass(WebDriver driver) {
		this.driver = driver;
		this.driver.get("https://www.saucedemo.com/index.html");
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get("https://www.saucedemo.com/index.html");
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		if(!url.contains("saucedemo")) {
			throw new Error("Error");
		}
	}

	public void login(String name, String password) {
		user.sendKeys(name);
		pass.sendKeys(password);
		button.click();
	}

	
	public String getError() {
		return error.getText();
	}

	public Object getAddress() {
		return driver.getCurrentUrl();
	}
	
}