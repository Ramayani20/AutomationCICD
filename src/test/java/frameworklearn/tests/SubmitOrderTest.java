package frameworklearn.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworklearn.TestComponents.BaseTest;
import frameworklearn.pageobjects.CartItems;
import frameworklearn.pageobjects.CheckoutPage;
import frameworklearn.pageobjects.ConfirmationPage;
import frameworklearn.pageobjects.Orderspage;
import frameworklearn.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName="ZARA COAT 3";
  @Test(dataProvider= "getData",groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException {
	 
		String country="India";
		//LandingPage landingpage=launchApplication();
		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		
		//ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		List<WebElement> products=productcatalogue.getproductlist();
		productcatalogue.addProductToCart(input.get("product"));
		CartItems cartpage=productcatalogue.goToCartpage();
		Boolean match=cartpage.itemPresent(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.checkoutclick();
		checkoutpage.enterCountryToSelect(country);	
		checkoutpage.selectCountry(country);
		ConfirmationPage confirmpage=checkoutpage.ClickPlaceorderButton();
		
		String confirmMessage=confirmpage.ConfirmMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    //driver.close();
		
	}
  
  @Test(dependsOnMethods= {"submitOrder"})
  public void OrderHistoryTest() {
	  ProductCatalogue productcatalogue=landingpage.loginApplication("Shriya@gmail.com", "Shriya@20");
	  
	  Orderspage orderpage=productcatalogue.goToOrderspage();
	  Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
  }
  
  //to verify ZARA COAT 3 is displaying on Orders page

  
  //Extent Reports--
  @DataProvider
  public Object[][] getData() throws IOException {
	  
//	  HashMap<String,String> map=new HashMap<String,String>();
//	  map.put("email", "Shriya@gmail.com");
//	  map.put("password", "Shriya@20");
//	  map.put("product", "ZARA COAT 3");
//	  
//	  HashMap<String,String> map1=new HashMap<String,String>();
//	  map1.put("email", "Sahil@gmail.com");
//	  map1.put("password", "Password12");
//	  map1.put("product", "ADIDAS ORIGINAL");
	  
	  List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\frameworklearn\\data\\PurchaseOrder.json");
	  return new Object[][] {{data.get(0)},{data.get(1)}};
	  //return new Object[][] {{map},{map1}};
	  
	  //return new Object[][] {{"Shriya@gmail.com","Shriya@20","ZARA COAT 3"},{"Sahil@gmail.com","Password12","ADIDAS ORIGINAL"}};
  }


}
