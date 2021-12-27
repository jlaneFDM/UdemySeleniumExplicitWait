package testcases;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		driver.get("https://www.yahoo.ca");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.id("ybarMailLink")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("/html/body/div[1]/a")).click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 5); //explicit wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //fluent wait type of explicit wait
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("Timed out after 10 seconds")
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("login-username")));
		driver.findElement(By.id("login-username")).sendKeys("jlane7590");
		driver.findElement(By.id("login-signin")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-passwd")));
		driver.findElement(By.id("login-passwd")).sendKeys("adfasd");
		driver.findElement(By.id("login-signin")).click();
		
		System.out.println(driver.findElement(By.className("error-msg")).getText());
		
		//note: implicit waits are global, explicit waits are element specific.
	}

}
