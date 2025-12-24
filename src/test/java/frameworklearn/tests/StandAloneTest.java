package frameworklearn.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameworklearn.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		String productName="ZARA COAT 3";
		String country="India";
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("Shriya@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shriya@20");
		driver.findElement(By.name("login")).click();
		LandingPage landingpage= new LandingPage(driver);
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector("div.mb-3"));
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//ng-animating
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
		List<WebElement> cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartproducts.stream().anyMatch(carproduct->carproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//Actions a= new Actions(driver);
		//a.sendKeys(driver.findElement(By.xpath("//input[@fdprocessedid='edznd']")), "india").build().perform();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".list-group-item")));
		
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
		

		List<WebElement> countries=driver.findElements(By.cssSelector(".list-group-item"));
		
	    WebElement countrytoselect=countries.stream().filter(countryName->countryName.getText().equals(country)).findFirst().orElse(null);
	    countrytoselect.click();
	   //driver.findElement(By.cssSelector(".action__submit")).click();
	 // Wait for animation/spinner to disappear
	    w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

	    // Scroll into view
	    WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submit);

	    // Click safely
	    w.until(ExpectedConditions.elementToBeClickable(submit)).click();

	    
	    String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    //driver.close();
	
		

	}

}
