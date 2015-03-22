package MyTestNG;

import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePage {
	@Test
	public static void f_Login(){
		//System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver_win32/chromedriver.exe");
//		WebDriver driver = new HtmlUnitDriver();//ChromeDriver();//HtmlUnitDriver();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, 
				"C:/phantomjs-2.0.0-windows/bin/phantomjs.exe");
		WebDriver driver = new PhantomJSDriver(caps);
		driver.manage().window().maximize();
		driver.get("https://dev.jetsonsys.com");
		final WebElement v_loginForm = driver.findElement(By.name("loginform"));
		List<WebElement> v_logins = v_loginForm.findElements(By.className("login-input"));
  
		for (WebElement v_login : v_logins){
			if(v_login.getAttribute("ng-model").equals("username")){
				v_login.sendKeys("admin@jetsonsys.com");
			}	
			else if (v_login.getAttribute("ng-model").equals("password")){
				v_login.sendKeys("gra33y");
			}
		}
  
		final WebElement v_button = v_loginForm.findElement(By.className("plm-button"));
		System.out.println("URL 1 " + driver.getCurrentUrl());
		v_button.click();
		sleep(7);
		//try {
		final WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("logged-in-as")));
		System.out.println("URL 2 " + driver.getCurrentUrl());
		final String v_logout = driver.findElement(By.xpath("//a[@ng-click='logout()']")).getText();
		System.out.println("v_logout " + v_logout);
		Assert.assertEquals(v_logout, "Logout");
		//}
		//catch (  NoSuchElementException e) {
		//	System.out.println("No such element");
		//}		
		sleep(4);
		driver.quit();
	}
	
	private static void sleep(int i) {
		try {
	        Thread.sleep(i * 1000);
	    } catch (InterruptedException e) {
	
	    }		
	}
}
