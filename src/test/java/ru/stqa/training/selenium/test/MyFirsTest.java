package ru.stqa.training.selenium.test;

import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MyFirsTest  {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    //  driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver = new ChromeDriver();
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

  @Test
  public void getBrowserLogs() {
    driver.navigate().to("http://selenium2.ru");
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
  }

  /*@Test ()
  public void getBrowserLogsProxy() {
    proxy.newHar();
    driver.navigate().to("http://selenium2.ru");
    Har har = proxy.endHar();
    har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
  } */

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}
