import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

public class LoadedClass extends LoadableComponent<LoadedClass> {

	private final WebDriver driver;
	@FindBy(name = "comment") 
	private WebElement description;

	public LoadedClass(WebDriver driver) {

		this.driver = driver;
		driver.get("http://parabank.parasoft.com");
	}

	@Override
	protected void load() {
		driver.get("https://github.com/SeleniumHQ/selenium/issues/new");
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		if(!url.endsWith("/new")) {
			throw new Error("Error");
		}
	}

	public void enterDescription(String issueDescription) {
		clearAndType(description, issueDescription);
	}

	private void clearAndType(WebElement field, String text) {
		field.clear();
		field.sendKeys(text);
	}
}