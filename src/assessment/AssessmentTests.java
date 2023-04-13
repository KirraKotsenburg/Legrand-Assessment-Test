package assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	 * Example of user already existing
	 */
	
	@Test
	void createAccount_Account_Already_Exists() {
		String username = "fake_name";
		String password = "12345";
		
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
	void createAccount_Account_Does_Not_Exist_Yet() {
//		Random random = new Random();
		String username = "Fuzzy_Wuzzy";
		String password = "wasABear";
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_3 = new FirefoxDriver();
		
		// Opens website page
		driver_3.get("https://www.demoblaze.com/index.html");
		
		
		// Find sign up and click button
		driver_3.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver_3.findElement(By.id("sign-username")).sendKeys(username);
		driver_3.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks sign up button to finalize
		driver_3.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver_3, Duration.ofSeconds(2));
		
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver_3.switchTo().alert();

		String message = alert.getText();
		alert.accept();
		System.out.println(message);
		Assert.assertEquals(message, "Sign up successful." );
		
		//driver_2.manage().deleteAllCookies();

		driver_3.close();
		// "This user already exist."
		// "Sign up successful."	
	}
	
	/**
	 * Example of user entering information and then canceling
	 */
	
	@Test
	void createAccount_Cancel_Create_Account() {
		String username = "fake_name";
		String password = "12345";
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_4 = new FirefoxDriver();
		
		// Opens website page
		driver_4.get("https://www.demoblaze.com/index.html");
		
		// Find sign up and click button
		driver_4.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver_4.findElement(By.id("sign-username")).sendKeys(username);
		driver_4.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks cancel button to exit sign up
		driver_4.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
		
		// Wait for
		driver_4.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Assert.assertTrue(driver_4.findElement(By.id("signin2")).isDisplayed());
		
		driver_4.close();
		// "This user already exist."
		// "Sign up successful."
		
	}
	
	
	/**
	 * Fails because wait time isn't long enough sometimes
	 */
	@Test
	void logIn() {
		String username = "fake_name";
		String password = "12345";
		
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
	void logOut() {
		String username = "fake_name";
		String password = "12345";
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
		
		//Add a wait here
		WebDriverWait wait = new WebDriverWait(driver_1, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
		
		//driver_1.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//LogOut click
		driver_1.findElement(By.id("logout2")).click();
		
		driver_1.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		Assert.assertEquals(expected, driver_1.findElement(By.id("signin2")).getText());
		driver_1.close();
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	void add_Two_Items_to_Cart() {
		//int addedItems = 0;
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_5 = new FirefoxDriver();
		
		
		driver_5.get("https://www.demoblaze.com/index.html");
		driver_5.manage().window().maximize();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver_5.findElement(By.linkText("Samsung galaxy s6")).click();
		// In Galaxy s6 page I want to click add to cart
		driver_5.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Creating a wait
		WebDriverWait wait = new WebDriverWait(driver_5, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver_5.switchTo().alert();
		alert.accept();
			
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver_5.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();


		
		//driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Add Nokia Lumia 1520 to cart
		driver_5.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart
		driver_5.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver_5.switchTo().alert();
		alert.accept();
		//Check that items are in cart
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver_5.findElement(By.id("cartur")).click();
		
		// Check that the items are in cart
		Assert.assertEquals(driver_5.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr[1]/td[2]")).getText(), "Nokia lumia 1520");
		Assert.assertEquals(driver_5.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr[2]/td[2]")).getText(), "Samsung galaxy s6");
		driver_5.close();
	}

	/**
	 * 
	 * 
	 */
	@Test
	void delete_Two_Items_from_Cart() {

		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_5 = new FirefoxDriver();
		
		driver_5.get("https://www.demoblaze.com/index.html");
		driver_5.manage().window().maximize();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver_5.findElement(By.linkText("Samsung galaxy s6")).click();
		
		// In Galaxy s6 page I want to click add to cart
		driver_5.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Creating a wait
		WebDriverWait wait = new WebDriverWait(driver_5, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver_5.switchTo().alert();
		alert.accept();
			
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver_5.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		// Add Nokia Lumia 1520 to cart
		driver_5.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart
		driver_5.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver_5.switchTo().alert();
		alert.accept();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver_5.findElement(By.id("cartur")).click();
		

		// Find delete button
		driver_5.findElement(By.linkText("Delete")).click();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Find second items delete button (had to use different By.)
		driver_5.findElement(By.cssSelector("tr.success:nth-child(2) > td:nth-child(4) > a:nth-child(1)")).click();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		List<WebElement> empty = Collections.<WebElement>emptyList();
		
		//Probably not actually proving anything with the link text thing, considering these aren't link texts
		Assert.assertEquals(driver_5.findElements(By.linkText("Samsung galaxy s6")), empty);
		Assert.assertEquals(driver_5.findElements(By.linkText("Nokia lumia 1520")), empty);
		driver_5.close();
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	void number_Of_Items_In_Cart() {
		int numOfItems = 0;
		int expected = 2;
		// Make a method called getProducts
		List<String> products = new ArrayList<String>();
		products.add("Samsung galaxy s6");
		products.add("Nokia lumia 1520");
		products.add("Nexus 6");
		products.add("Samsung galaxy s7");
		products.add("Iphone 6 32gb");
		products.add("Sony xperia z5");
		products.add("HTC One M9");
		products.add("Sony vaio i5");
		products.add("Sony vaio i7");
		products.add("Apple monitor 24");
		products.add("MacBook air");
		products.add("Dell i7 8gb");
		products.add("2017 Dell 15.6 Inch");
		products.add("ASUS Full HD");
		products.add("MacBook Pro");
		
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver_5 = new FirefoxDriver();
		
		driver_5.get("https://www.demoblaze.com/index.html");
		driver_5.manage().window().maximize();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver_5.findElement(By.linkText("Samsung galaxy s6")).click();
		
		// In Galaxy s6 page I want to click add to cart
		driver_5.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Creating a wait
		WebDriverWait wait = new WebDriverWait(driver_5, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver_5.switchTo().alert();
		alert.accept();
			
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver_5.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		// Add Nokia Lumia 1520 to cart
		driver_5.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart
		driver_5.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver_5.switchTo().alert();
		alert.accept();
		
		driver_5.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver_5.findElement(By.id("cartur")).click();
		
		List<WebElement> cart = driver_5.findElements(By.className("success"));
		
		for(WebElement item : cart) {
			if(products.contains(item.getText()));
				numOfItems++;
		}
		
		Assert.assertEquals(numOfItems, expected);
		driver_5.close();
	}

}
