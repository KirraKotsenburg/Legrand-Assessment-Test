package assessment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class AssessmentTest {

//	@BeforeEach
//	void setUp() throws Exception {
//	}

	
//	@Test
//	void createAccount_Test() {
//		WebDriver driver = new FirefoxDriver();
//		String username = "dangarang";
//		String password = "blamalamb";
//		String url = "https://www.demoblaze.com";
//		
//		Assessment.createAccount(username, password, url, driver);
//		
//	}
	
	@Test
	void logIn_Premade_Account() {
		String username = "Smokey";
		String password = "theBear";		
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://www.demoblaze.com");
		
		String actual = Assessment.logIn(username, password, driver);
		System.out.println(actual);

		assertEquals("https://www.demoblaze.com/index.html#", actual);

		driver.close();
	}
	
	@Test
	void addTwoItemsToCart() {
		System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
		
		FirefoxDriver driver = new FirefoxDriver();
		
		
		//Add first item
		driver.get("https://www.demoblaze.com/prod.html?idp_=1");
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
		
		driver.get("https://www.demoblaze.com/prod.html?idp_=4");
		driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();

		
		//Go to cart
		driver.get("https://www.demoblaze.com/cart.html");
		
		String item1 = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr[1]/td[2]")).getText();
		String item2 = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr[2]/td[2]")).getText();
		//System.out.println("Item 1 " + item1 + "Item 2 " +item2);
		
		
	}
	

}
