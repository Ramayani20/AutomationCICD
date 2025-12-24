package frameworklearn.tests;

import java.io.IOException;
import frameworklearn.TestComponents.Retry;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import frameworklearn.TestComponents.BaseTest;
import frameworklearn.pageobjects.CartItems;
import frameworklearn.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

  @Test(groups={"Errorhandling"},retryAnalyzer=Retry.class)
		public void LoginErrorValidation() throws IOException {
	    //String productName="ZARA COAT 3";
		//String country="India";
	    landingpage.loginApplication("Shriya@gmail.com", "Shriya20");
		Assert.assertEquals("Incorrect email or password.", landingpage.geterrormessage());
	}
  @Test
  public void productErrorValidation() {
  String productName="ZARA COAT 3";
  ProductCatalogue productcatalogue=landingpage.loginApplication("Shriya@gmail.com", "Shriya@20");
	
	List<WebElement> products=productcatalogue.getproductlist();
	productcatalogue.addProductToCart(productName);
	CartItems cartpage=productcatalogue.goToCartpage();
	Boolean match=cartpage.itemPresent("ZARA COAT 33");
	Assert.assertFalse(match);
  }


}
