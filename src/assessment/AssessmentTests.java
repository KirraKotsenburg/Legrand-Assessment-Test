package assessment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class AssessmentTests {
	
	
	/**
	 * Creates new account for user
	 * 
	 * @param username - User's inputed name for https://www.demoblaze.com/
	 * @param password - User's inputed password for https://www.demoblaze.com/
	 * @param driver - WebDriver of user's choice
	 * @return void
	 */
	
	@Test
	public static void createAccount(String username, String password, String url, WebDriver driver) {
		// Opens website page
		driver.get(url);
		
		// Find sign up and click button
		driver.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver.findElement(By.id("sign-username")).sendKeys(username);
		driver.findElement(By.id("sign-password")).sendKeys(password);
		//clicks sign up button to finalize
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		
	}
	
	
	/**
	 * Signs user into account
	 * 
	 * @param username - User's inputed name for https://www.demoblaze.com/
	 * @param password - User's inputed password for https://www.demoblaze.com/
	 * @param driver - WebDriver of user's choice
	 * 
	 * @return void
	 */
	@Test
	public static String logIn(String username, String password, WebDriver driver) {
		//Find log in button and click it
		driver.findElement(By.id("login2")).click();
		
		// Enter's user's information
		driver.findElement(By.id("loginusername")).sendKeys(username);
		driver.findElement(By.id("loginpassword")).sendKeys(password);
		
		// Click Log in button to complete log in
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
		
		driver.findElement(By.id("nameofuser")).click();
		
		return driver.getCurrentUrl();

	}
	
	/**
	 * Signs user out of account
	 * 
	 * @param driver - WebDriver of user's choice
	 * 
	 * @return void
	 */
	@Test
	public static void logOut(FirefoxDriver driver) {
		driver.findElement(By.id("logout2")).click();
	}
	

}
