package frameworklearn.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import frameworklearn.AbstractComponents.AbstractComponent;

public class CartItems extends AbstractComponent{
	
	WebDriver driver;
	public CartItems(WebDriver driver) { //Constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //PageFactory has method initElements which initialize all the webelements
				
	}
	
  @FindBy(css=".cartSection h3")	
  List<WebElement> cartproducts;
  
  @FindBy(css=".totalRow button")
  WebElement checkoutbutton;
  
  public List<WebElement> getCartItemList() {
	  
	  return cartproducts;
  }
  
  public Boolean itemPresent(String productName) {
	  
	  Boolean match=getCartItemList().stream().anyMatch(carproduct->carproduct.getText().equalsIgnoreCase(productName));
      return match;
  }
	
  public CheckoutPage checkoutclick()
  {
	  checkoutbutton.click();
	  CheckoutPage checkoutpage=new CheckoutPage(driver);
	  return checkoutpage;
	  
  }

}
