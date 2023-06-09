package assessment;

import java.time.Duration;
import java.util.ArrayList;
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
	 * Example of user trying to create an account with a pre-existing account
	 */
	
	@Test
	void createAccount_Account_Already_Exists() {
		//Set up username and password
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
		
		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//Wait till alert appears
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
		//Generate random account information
		Random random = new Random();
		String username = random.ints(0, 100000).toString();
		String password = random.ints(0, 100000).toString();
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Opens website page
		driver.get("https://www.demoblaze.com/index.html");
		
		// Find sign up and click button
		driver.findElement(By.id("signin2")).click();
		
		// types in username and password
		driver.findElement(By.id("sign-username")).sendKeys(username);
		driver.findElement(By.id("sign-password")).sendKeys(password);
		
		//clicks sign up button to finalize
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
		
		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//Wait until alert appears
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
	 * Example of user entering Account information and then canceling
	 */
	
	@Test
	void createAccount_Cancel_Create_Account() {
		//Set up username and password, does not matter if it exists or not
		String username = "fake_name";
		String password = "12345";
		
		//Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Opens website page
		driver.get("https://www.demoblaze.com/index.html");
		
		//Find sign up and click button
		driver.findElement(By.id("signin2")).click();
		
		//Types in username and password
		driver.findElement(By.id("sign-username")).sendKeys(username);
		driver.findElement(By.id("sign-password")).sendKeys(password);
		
		//Clicks cancel button to exit sign up
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[1]")).click();
		
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Check that homepage has not been updated with "Welcome 'user'" icon in top right
		Assert.assertTrue(driver.findElement(By.id("signin2")).isDisplayed());
		
		driver.close();
	}
	
	
	/**
	 * User logging in to existing account
	 */
	@Test
	void logIn() {
		
		//Set up username and password
		String username = "fake_name";
		String password = "12345";
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Go to website
		driver.get("https://www.demoblaze.com/index.html");
		
		//Find log in button and click it
		driver.findElement(By.id("login2")).click();
		
		// Enter's user's information
		driver.findElement(By.id("loginusername")).sendKeys(username);
		driver.findElement(By.id("loginpassword")).sendKeys(password);
		
		// Click Log in button to complete log in
		driver.findElement(By.cssSelector("#logInModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
		
		//Add a wait here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		
		//Wait until welcome 'user' appears on display
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		
		//Get element at welcome 'user'
		WebElement greeting = driver.findElement(By.id("nameofuser"));
		
		//Check that greeting to user matches name
		Assert.assertEquals(greeting.getText(),"Welcome fake_name");
		
		driver.close();
	}
	
	/**
	 * User logging in with nonexistent account 
	 */
	@Test
	void logIn_Without_Account() {
		//Generate random username and password
		Random random = new Random();
		String username = random.ints(0, 1000000).toString();
		String password = random.ints(0, 100000).toString();
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Go to website
		driver.get("https://www.demoblaze.com/index.html");
		
		//Find log in button and click it
		driver.findElement(By.id("login2")).click();
		
		// Enter's user's information
		driver.findElement(By.id("loginusername")).sendKeys(username);
		driver.findElement(By.id("loginpassword")).sendKeys(password);
		
		// Click Log in button to complete log in
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
		
		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//Wait until alert appears
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		//Get alert's message
		String message = alert.getText();
		
		//Check that the pop up alert says user does not exist
		Assert.assertEquals(message, "User does not exist.");
		//Accept alert
		alert.accept();
		
		//Find and click close button
		driver.findElement(By.cssSelector("#logInModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)")).click();
		
		//Check that home screen hasn't changed to welcome an account
		Assert.assertTrue(driver.findElement(By.id("signin2")).isDisplayed());
		
		driver.close();
	}
	
	
	/**
	 * User logging out of account
	 * 
	 */
	@Test
	void logOut() {
		//Set up username and password
		String username = "fake_name";
		String password = "12345";
		//Expected message to appear when logged out
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
		
		//Wait until logout button appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
		
		//LogOut click
		driver.findElement(By.id("logout2")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//Check that "Welcome 'user'" icon is Sign up button again
		Assert.assertEquals(expected, driver.findElement(By.id("signin2")).getText());
		
		driver.close();
	}
	
	/**
	 * User is adding two items to their cart
	 */
	@Test
	void add_Two_Items_to_Cart() {
		//Int to track if specific items are in cart
		int itemsAreIn = 0;
		
		//List to hold what items user expects in cart
		List<String> expectedItems = new ArrayList<String>();
		expectedItems.add("Samsung galaxy s6");
		expectedItems.add("Nokia lumia 1520");
		
		// Set up Webdriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Go to website
		driver.get("https://www.demoblaze.com/index.html");
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		// In Galaxy s6 page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		
		// Accept product added alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Click home button
		driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		// In Nokia Lumia page click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		// Waiting until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		//Accept alert
		alert.accept();
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver.findElement(By.id("cartur")).click();
		//List of Items in cart
		List<WebElement> inCart = driver.findElements(By.className("success"));
		
		//Iterate and check that the specific items wanted are in the cart, store count into itemsAreIn
		for(int i = 0; i < inCart.size(); i++) {
			for(int j =0; j < expectedItems.size(); j++) {
				//Checks to see if current item contains the text for specific items user wants
				if(inCart.get(i).getText().contains(expectedItems.get(j))) {
					itemsAreIn++;
				}
			}
		}
		//Check that cart size matches expected
		Assert.assertEquals(inCart.size(), 2);
		//Check that specific items counted in cart matches the expected item list's size
		Assert.assertEquals(itemsAreIn, expectedItems.size());
		
		driver.close();
	}
	

	/**
	 * User is trying to delete two items that are in cart
	 * 
	 * Test failure 2/3, will sometimes assert that there are still elements in
	 * cart though there are none by manual inspection and inspecting the website html
	 * Type of error thrown by asserts: size does not match expected and list
	 * not empty
	 * 
	 */
	@Test
	void delete_Two_Items_from_Cart() {
		//Set up WebDriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Go to website
		driver.get("https://www.demoblaze.com/index.html");
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		//In Galaxy s6 page click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		//Wait until alert appears
		wait.until(ExpectedConditions.alertIsPresent());
		
		//Accept product added alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Click home button
		driver.findElement(By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")).click();

		//Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		//In Nokia Lumia page I want to click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		//Wait until alert appears
		wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		//Accept alert
		alert.accept();
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//Click cart button
		driver.findElement(By.id("cartur")).click();
		
		//Find first item's delete button
		driver.findElement(By.cssSelector("tr.success:nth-child(1) > td:nth-child(4) > a:nth-child(1)")).click();
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Find second item's delete button
		driver.findElement(By.cssSelector("tr.success:nth-child(2) > td:nth-child(4) > a:nth-child(1)")).click();
		//Wait
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
	 * User wants to delete one item and keep the other
	 * 
	 */
	@Test
	void delete_One_Item_from_Cart_With_Two_Items() {
		//Chosen item to delete
		String itemToDelete = "Samsung galaxy s6";
		//Int to track added items
		int added = 0;
		//Set up WebDriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		//Go to website
		driver.get("https://www.demoblaze.com/index.html");
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//Add Nokia Lumia 1520 to cart
		driver.findElement(By.linkText("Nokia lumia 1520")).click();
		//In Nokia Lumia page click add to cart button
		driver.findElement(By.cssSelector("a.btn")).click();

		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		// Wait until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		
		//Press okay on alert "Product added"
		alert.accept();
		//Increment added
		added++;
		//Wait
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
		//Increment added
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
			//Decrement added
			added--;
		}else {
			// Find delete button for second node in "success"
			driver.findElement(By.cssSelector("tr.success:nth-child(2) > td:nth-child(4) > a:nth-child(1)")).click();
			//Decrement added
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
		//Error occurs here because even after deleting an item from the cart still asserts two elements in the cart, not one like expected
		Assert.assertEquals(currentCart.size(), 1);
		
		driver.close();
	}
	
	/**
	 * User wants to know items how many items are in cart
	 * 
	 * Test fails 1/4 of the time, producing an "Expected 2 got 1" error
	 */
	@Test
	void number_Of_Items_In_Cart() {
		//Ints to track expected amount and number of items
		int numOfItems = 0;
		int expected = 2;
		
		//Set up WebDriver
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
		//Go to Website
		driver.get("https://www.demoblaze.com/index.html");
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Add Samsung Galaxy s6 to cart
		driver.findElement(By.linkText("Samsung galaxy s6")).click();
		// In Galaxy s6 page click add to cart
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		//Add wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		// Wait until alert pops up
		wait.until(ExpectedConditions.alertIsPresent());
		// Accept product added alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//Wait
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
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Click cart button
		driver.findElement(By.id("cartur")).click();
		
		//Generate List of items in cart
		List<WebElement> cart = driver.findElements(By.className("success"));
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		numOfItems = cart.size();
		
		//Check that cart size matched expected size
		Assert.assertEquals(numOfItems, expected);
		//Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		driver.close();
	}
	
}
