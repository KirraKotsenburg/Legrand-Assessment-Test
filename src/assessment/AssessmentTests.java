package assessment;

import java.time.Duration;
import org.openqa.selenium.Alert;
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
	
	@Test
	public static void createAccount_Account_Already_Exists() {
		String username = "fake_name";
		String password = "12345";
		SoftAssert softAssert = new SoftAssert();
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_2 = new FirefoxDriver();
		
		// Opens website page
		driver_2.get("https://www.demoblaze.com/index.html");
		
		// Find sign up and click button
		driver_2.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver_2.findElement(By.id("sign-username")).sendKeys(username);
		driver_2.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks sign up button to finalize
		driver_2.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver_2, Duration.ofSeconds(2));
		
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver_2.switchTo().alert();

		String message = alert.getText();
		alert.accept();
		System.out.println(message);
		Assert.assertEquals(message, "This user already exist.");
		driver_2.close();
		// "This user already exist."
		// "Sign up successful."
		
	}
	
	/**
	 * Fails if the username/password has already been used
	 */
	@Test
	public static void createAccount_Account_Does_Not_Exist_Yet() {
//		Random random = new Random();
		String username = "Fuzzy_Wuzzy";
		String password = "wasABear";
		SoftAssert softAssert = new SoftAssert();
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_2 = new FirefoxDriver();
		
		// Opens website page
		driver_2.get("https://www.demoblaze.com/index.html");
		
		
		// Find sign up and click button
		driver_2.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver_2.findElement(By.id("sign-username")).sendKeys(username);
		driver_2.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks sign up button to finalize
		driver_2.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver_2, Duration.ofSeconds(2));
		
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver_2.switchTo().alert();

		String message = alert.getText();
		alert.accept();
		System.out.println(message);
		Assert.assertEquals(message, "Sign up successful." );
		
		driver_2.manage().deleteAllCookies();

		
		driver_2.close();
		// "This user already exist."
		// "Sign up successful."	
	}
	
	
	/**
	 * Fails because wait time isn't long enough sometimes
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
		
		//Add a wait here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		
		WebElement greeting = driver.findElement(By.id("nameofuser"));
		//String loggedInUser = "Welcome fake_name";
		
		Assert.assertEquals(greeting.getText(),"Welcome fake_name");
		driver.close();
	}
	
	/**
	 * Fails because wait time isn't long enough sometimes
	 * 
	 */
	@Test
	public static void logOut() {
		String username = "fake_name";
		String password = "12345";
		SoftAssert softAssert = new SoftAssert();
		String expected = "Sign up";
		
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
		WebDriverWait wait = new WebDriverWait(driver_1, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
		
		//LogOut click
		driver_1.findElement(By.id("logout2")).click();
		
		Assert.assertEquals(expected, driver_1.findElement(By.id("signin2")).getText());
		driver_1.close();
	}
	

}
