package frameworklearn.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworklearn.pageobjects.CartItems;
import frameworklearn.pageobjects.Orderspage;

public class AbstractComponent {
	
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
	  this.driver=driver;
	  PageFactory.initElements(driver, driver);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;

	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public CartItems goToCartpage() {
		cartHeader.click();
		CartItems cartpage=new CartItems(driver);
		return cartpage;
	}
	
	public Orderspage goToOrderspage() {
		ordersHeader.click();
		Orderspage orderpage=new Orderspage(driver);
		return orderpage;
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToClickable(WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
