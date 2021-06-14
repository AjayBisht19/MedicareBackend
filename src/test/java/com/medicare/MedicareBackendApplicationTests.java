package com.medicare;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicareBackendApplicationTests {
	WebDriver driver;

	@Test
	void contextLoads() {

	}

	@BeforeMethod
	public void setDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://medicarebucket123.s3-website-us-east-1.amazonaws.com/login");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		WebElement login = driver.findElement(By.id("login"));
		login.click();

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("user");

		WebElement log = driver.findElement(By.name("login"));
		log.click();
		Thread.sleep(2000);
	}

	@Test
	public void moveToCart() throws InterruptedException {

		WebElement cart = driver.findElement(By.id("cart"));
		cart.click();

		Thread.sleep(2000);
		WebElement cartHeading = driver.findElement(By.name("cart"));
		String actual = cartHeading.getText();
		String expected = "Your Cart";

		assertEquals(expected, actual);
		WebElement logout = driver.findElement(By.id("logout"));
		logout.click();

	}

	@Test
	public void moveToOrders() throws InterruptedException {

		WebElement order = driver.findElement(By.id("orders"));
		order.click();

		Thread.sleep(2000);
		WebElement orderHeading = driver.findElement(By.name("orders"));
		String actual = orderHeading.getText();
		String expected = "Your Orders";

		assertEquals(expected, actual);
		WebElement logout = driver.findElement(By.id("logout"));
		logout.click();

	}

	@AfterMethod
	public void logoutAndExit() throws InterruptedException {

		driver.quit();
	}

}
