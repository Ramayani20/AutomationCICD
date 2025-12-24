package frameworklearn.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import frameworklearn.TestComponents.BaseTest;
import frameworklearn.pageobjects.CartItems;
import frameworklearn.pageobjects.CheckoutPage;
import frameworklearn.pageobjects.ConfirmationPage;
import frameworklearn.pageobjects.LandingPage;
import frameworklearn.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class StepDefinitionImp extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmpage;
	String country="India";
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage=launchApplication();
		
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
	  productcatalogue=landingpage.loginApplication(username,password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products=productcatalogue.getproductlist();
		productcatalogue.addProductToCart(productName);
		
	}
	
	@When("^checkout product (.+) and submit the order$")
	public void checkout_product_and_submit_order(String productName) {
		CartItems cartpage=productcatalogue.goToCartpage();
		Boolean match=cartpage.itemPresent(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.checkoutclick();
		checkoutpage.enterCountryToSelect(country);	
		checkoutpage.selectCountry(country);
	    confirmpage=checkoutpage.ClickPlaceorderButton();
		
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_on_confirmation_page(String string){
		String confirmMessage=confirmpage.ConfirmMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	    driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed(String string1) {
		Assert.assertEquals(string1, landingpage.geterrormessage());
		driver.close();
	}
}
