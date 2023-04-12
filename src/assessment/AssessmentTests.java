package assessment;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AssessmentTests {
	
	
	/**
	 * Creates new account for user
	 * 
	 * @param username - User's inputed name for https://www.demoblaze.com/
	 * @param password - User's inputed password for https://www.demoblaze.com/
	 * @param driver - WebDriver of user's choice
	 * @return void
	 */
	
//	@Test
//	public static void createAccount(String username, String password, String url, WebDriver driver) {
//		// Opens website page
//		driver.get(url);
//		
//		// Find sign up and click button
//		driver.findElement(By.id("signin2")).click();
//		
//		// types in username and password
//		driver.findElement(By.id("sign-username")).sendKeys(username);
//		driver.findElement(By.id("sign-password")).sendKeys(password);
//		//clicks sign up button to finalize
//		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
//		
//		
//	}
	
	
	/**
	 * Signs user into account
	 * 
	 * @param username - User's inputed name for https://www.demoblaze.com/
	 * @param password - User's inputed password for https://www.demoblaze.com/
	 * @param driver - WebDriver of user's choice
	 */
	@Test
	public static void logIn() {
		String username = "fake_name";
		String password = "12345";
		SoftAssert softAssert = new SoftAssert();
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("https://www.demoblaze.com/index.html");
		
		//Find log in button and click it
		driver.findElement(By.id("login2")).click();
		
		// Enter's user's information
		driver.findElement(By.id("loginusername")).sendKeys(username);
		driver.findElement(By.id("loginpassword")).sendKeys(password);
		
		// Click Log in button to complete log in
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
		
		WebElement greeting = driver.findElement(By.id("nameofuser"));
		//String loggedInUser = "Welcome fake_name";
		
		softAssert.assertEquals(greeting.getText(),"Welcome fake_name");
		driver.close();
	}
	
	/**
	 * Signs user out of account
	 * 
	 * @param driver - WebDriver of user's choice
	 * 
	 * @return void
	 */
	@Test
	public static void logOut() {
		String username = "fake_name";
		String password = "12345";
		SoftAssert softAssert = new SoftAssert();
		String expected = "Sign Up";
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_1 = new FirefoxDriver();
		
		driver_1.get("https://www.demoblaze.com/index.html");
		
		//Find log in button and click it
		driver_1.findElement(By.id("login2")).click();
		
		// Enter's user's information
		driver_1.findElement(By.id("loginusername")).sendKeys(username);
		driver_1.findElement(By.id("loginpassword")).sendKeys(password);
		
		// Click Log in button to complete log in
		driver_1.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
		
		WebElement loggedIn = driver_1.findElement(By.id("nameofuser"));
		softAssert.assertEquals(loggedIn.getText(),"Welcome fake_name");
		
		//Add a wait here
		WebDriverWait wait = new WebDriverWait(driver_1, Duration.ofSeconds(2));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
		
		//LogOut click
		driver_1.findElement(By.id("logout2")).click();
		
		softAssert.assertEquals(expected, driver_1.findElement(By.id("signin2")).getText());
		driver_1.close();
	}
	

}
