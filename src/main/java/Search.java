import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search {
    public WebDriver driver;
    private final Wait<WebDriver> wait;
    
    @FindBy(name = "q")
    private WebElement field;

    public Search(WebDriver driver,String website) {
        this.driver = driver;
        driver.get(website);
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
    
    public void search(String ask){
        field.sendKeys(ask, Keys.ENTER);
    }

    public void waitUntilTitle(String title){
        wait.until(ExpectedConditions.titleContains(title));
    }

    public boolean assertTitleContains(String title) throws Exception{
        Boolean result = driver.getTitle().contains(title);
        System.out.println(driver.getTitle());
        return(result);
    }
}