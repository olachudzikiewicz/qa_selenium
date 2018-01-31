package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirsTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
   // WebDriver chromeDriver = new ChromeDriver();
   // WebDriver ieDriver = new InternetExplorerDriver();
  //  WebDriver firefoxDriver = new FirefoxDriver();
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //selenium będzie czekać max 10 sek aż się
    // jakiś element pojawi
    wait = new WebDriverWait(driver, 10);

    ///CAPABILITIES///
   /* DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
    WebDriver driver = new ChromeDriver(caps);
    System.out.println(((HasCapabilities) driver).getCapabilities()); */
  }

  @Test
  public void myFirstTest() {

    driver.navigate().to("http://www.google.com"); //driver.get("http://www.google.com");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnK")).click();
  //  wait.until(titleIs("webdriver - Поиск в Google"));
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}
