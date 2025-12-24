package frameworklearn.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworklearn.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) { //Constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //PageFactory has method initElements which initialize all the webelements
				
	} 
	
	@FindBy(css=".hero-primary")
	WebElement confrimessage;
	
	public String ConfirmMessage() {
		return confrimessage.getText();
		
		
	}
	
}
