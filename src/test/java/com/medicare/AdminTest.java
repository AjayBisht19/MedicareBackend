package com.medicare;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminTest {
	WebDriver driver;

	@BeforeMethod
	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		WebElement login = driver.findElement(By.id("login"));
		login.click();

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("123456");
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("admin123");

		WebElement log = driver.findElement(By.name("login"));
		log.click();
	}

	@Test
	public void loginLogout() throws InterruptedException {

		Thread.sleep(2000);
		WebElement logout = driver.findElement(By.id("logout"));
		logout.click();

	}

	@Test
	public void adminPage() {
		WebElement admin = driver.findElement(By.name("admin"));
		String text = admin.getText();
		if (text.contains("Admin")) {
			assertTrue(true);
		}
	}
	@Test
	public void sortByPrice() throws InterruptedException {
		WebElement manage = driver.findElement(By.id("manage"));
		manage.click();
		Thread.sleep(2000);
		
		WebElement sort = driver.findElement(By.name("sortByPrice"));
		sort.click();	

	}

	@AfterMethod
	public void Exit() throws InterruptedException {
		Thread.sleep(2000);

		driver.quit();
	}
}
