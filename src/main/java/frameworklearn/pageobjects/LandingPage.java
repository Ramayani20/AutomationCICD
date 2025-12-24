package frameworklearn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworklearn.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) { //Constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //PageFactory has method initElements which initialize all the webelements
				
	}
	
	//WebElement useremail=driver.findElement(By.id("userEmail"));
	//PageFactory
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(name="login")
	WebElement submit;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]")
	WebElement errormessage;
	
	
	public ProductCatalogue loginApplication(String email, String password) {
		
		useremail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public String geterrormessage() {
		waitForWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}

}
