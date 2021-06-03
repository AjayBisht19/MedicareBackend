package com.medicare.testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class TestNG {

	@Test
	public void chrome() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.simplilearn.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	@Test
	public void edge() {
		System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
		WebDriver driver1 = new EdgeDriver();
		driver1.get("https://www.google.com/");

		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
}
