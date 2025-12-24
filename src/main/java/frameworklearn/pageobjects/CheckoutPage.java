package frameworklearn.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworklearn.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) { //Constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //PageFactory has method initElements which initialize all the webelements
				
	} 
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement Selectcountrydropdown;
	
	@FindBy(css=".list-group-item")
	private List<WebElement> countrylist;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	
	private By countryby=By.cssSelector(".list-group-item");

	
	
	public void enterCountryToSelect(String country) {
		Selectcountrydropdown.sendKeys(country);
	}
	
	public List<WebElement> countryList() {
		
		waitForElementToAppear(countryby);
		return countrylist;
	}
	
	public WebElement countryToSelect(String country) {
		 WebElement countrytoselect=countryList().stream().filter(countryName->countryName.getText().equals(country)).findFirst().orElse(null);
		 return countrytoselect;
		 
	}
	
	public void selectCountry(String country) {
		WebElement countrytoselect=countryToSelect(country);
		countrytoselect.click();
	}
	
	public ConfirmationPage ClickPlaceorderButton() {
		waitForElementToDisappear(spinner);
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submit);
	    waitForElementToClickable(submit);
	    submit.click();
	    ConfirmationPage confirmpage=new ConfirmationPage(driver);
	    return confirmpage;

		
	}
	
	


}
