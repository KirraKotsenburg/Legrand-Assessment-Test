package assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


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
		FirefoxDriver driver = new FirefoxDriver();
		
		// Opens website page
		driver.get("https://www.demoblaze.com/index.html");
		
		// Find sign up and click button
		driver.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver.findElement(By.id("sign-username")).sendKeys(username);
		driver.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks sign up button to finalize
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		//Get pop up alert's message
		String message = alert.getText();
		//Accept alert
		alert.accept();
		//Check that the messsage matches expected
		Assert.assertEquals(message, "This user already exist.");
		driver.close();	
	}
	
	/**
	 * Fails if the username/password has already been used
	 */
	@Test
	void createAccount_Account_Does_Not_Exist_Yet() {
		Random random = new Random();
		
		String username = random.ints(0, 100000).toString();
		String password = random.ints(0, 100000).toString();
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		// Opens website page
		driver.get("https://www.demoblaze.com/index.html");
		
		
		// Find sign up and click button
		driver.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver.findElement(By.id("sign-username")).sendKeys(username);
		driver.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks sign up button to finalize
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver.switchTo().alert();
		//Get alert message
		String message = alert.getText();
		//Accept alert
		alert.accept();
		//Check that pop up message says successful
		Assert.assertEquals(message, "Sign up successful." );
		
		driver.close();	
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
		FirefoxDriver driver = new FirefoxDriver();
		
		// Opens website page
		driver.get("https://www.demoblaze.com/index.html");
		
		// Find sign up and click button
		driver.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver.findElement(By.id("sign-username")).sendKeys(username);
		driver.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks cancel button to exit sign up
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
		
		// Wait for
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Check that homepage has not been updated with "Welcome 'user'" icon in top right
		Assert.assertTrue(driver.findElement(By.id("signin2")).isDisplayed());
		
		driver.close();
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
		
		//driver_1.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//LogOut click
		driver.findElement(By.id("logout2")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		Assert.assertEquals(expected, driver.findElement(By.id("signin2")).getText());
		driver.close();
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	void add_Two_Items_to_Cart() {
		int itemsAreIn = 0;
		List<String> expectedItems = new ArrayList<String>();
		expectedItems.add("Samsung galaxy s6");
		expectedItems.add("Nokia lumia 1520");
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("https://www.demoblaze.com/index.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		// In Galaxy s6 page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Creating an explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver.switchTo().alert();
		alert.accept();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver.findElement(By.id("cartur")).click();

		
		List<WebElement> inCart = driver.findElements(By.className("success"));
		
		//Iterate and check that the specific items wanted are in the cart, store count into itemsAreIn
		for(int i = 0; i < inCart.size(); i++) {
			for(int j =0; j < expectedItems.size(); j++) {
				if(inCart.get(i).getText().contains(expectedItems.get(j))) {
					itemsAreIn++;
				}
			}
		}
		
		Assert.assertEquals(inCart.size(), 2);
		Assert.assertEquals(itemsAreIn, expectedItems.size());
		driver.close();
	}
	

	/**
	 * Test failure 2/3, will sometimes assert that there are still elements in
	 * cart though there are none by manual inspection and inspecting the website html
	 * Type of error thrown by asserts: size does not match expected and list
	 * not empty
	 * 
	 */
	@Test
	void delete_Two_Items_from_Cart() {

		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("https://www.demoblaze.com/index.html");
		//driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		
		// In Galaxy s6 page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Creating a wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		// Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver.switchTo().alert();
		alert.accept();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver.findElement(By.id("cartur")).click();
		
		// Find delete button
		driver.findElement(By.cssSelector("tr.success:nth-child(1) > td:nth-child(4) > a:nth-child(1)")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Find second item's delete button
		driver.findElement(By.cssSelector("tr.success:nth-child(2) > td:nth-child(4) > a:nth-child(1)")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Returns the elements of class "success" in a List, if there are no elements it's empty
		List<WebElement> currentCart = driver.findElements(By.className("success"));
		//Check empty
		Assert.assertTrue(currentCart.isEmpty());
		//Check size
		Assert.assertEquals(currentCart.size(), 0);
		//Close window
		driver.close();
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	void delete_One_Item_from_Cart_With_Two_Items() {
		String itemToDelete = "Samsung galaxy s6";
		int added = 0;

		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.demoblaze.com/index.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart button
		driver.findElement(By.cssSelector("a.btn")).click();

		// Creating a wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		//Press okay on alert "Product added"
		alert.accept();
		added++;
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		// Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
				
		// In Galaxy s6 page I want to click add to cart button
		driver.findElement(By.cssSelector("a.btn")).click();

		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		//Press okay on alert "Product added"
		alert.accept();
		added++;
		
		//Wait for a period
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver.findElement(By.id("cartur")).click();
		
		//First item's text to check if it is the one I want to delete
		String item1 = driver.findElement(By.cssSelector("tr.success:nth-child(1)")).getText();
		
		if(item1.contains(itemToDelete)) {
			// Find delete button for first node in "success"
			driver.findElement(By.cssSelector("tr.success:nth-child(1) > td:nth-child(4) > a:nth-child(1)")).click();
			added--;
		}else {
			// Find delete button for second node in "success"
			driver.findElement(By.cssSelector("tr.success:nth-child(2) > td:nth-child(4) > a:nth-child(1)")).click();
			added--;
		}
		
		//Wait for a period after deleting
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Returns the elements of class "success", which is the cart's table of products, in a List. If there are no elements it's empty
		List<WebElement> currentCart = driver.findElements(By.className("success"));
		
		//Check that the cart isn't empty
		Assert.assertFalse(currentCart.isEmpty());
		
		//Check that the item I still want is in cart
		Assert.assertTrue(currentCart.get(0).getText().contains("Nokia lumia 1520"));
		
		Assert.assertEquals(currentCart.size(), added);

		//Check size
		//Error occurs here because even after deleting an item from the cart I still get two elements in the cart, not one
		Assert.assertEquals(currentCart.size(), 1);
		
		//Close window
		driver.close();
	}
	
	/**
	 * Test fails 1/4 of the time, producing an "Expected 2 got 1" error
	 * 
	 */
	@Test
	void number_Of_Items_In_Cart() {
		int numOfItems = 0;
		int expected = 2;
		
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("https://www.demoblaze.com/index.html");
		//driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		
		// In Galaxy s6 page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Creating a wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		// Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		alert = driver.switchTo().alert();
		alert.accept();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver.findElement(By.id("cartur")).click();
		
		List<WebElement> cart = driver.findElements(By.className("success"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		numOfItems = cart.size();
		
		Assert.assertEquals(numOfItems, expected);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		driver.close();
	}
	
}
