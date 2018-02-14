package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class NewWindowTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 2);
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void testNewWindow() {

    driver.navigate().to("http://localhost/litecart/admin/login.php");
    loginAdmin();

    driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
    driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();
    String mainWindow = driver.getWindowHandle();
    Set<String> oldWindows = driver.getWindowHandles();
    List<WebElement> links = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
    for (WebElement link:links) {
      link.click();
      wait.until(numberOfWindowsToBe(2));
      Set<String> allWindows = driver.getWindowHandles();
      allWindows.removeAll(oldWindows);
      driver.switchTo().window(allWindows.iterator().next());
      driver.close();
      driver.switchTo().window(mainWindow);
      allWindows.clear();
    }

  }

  private void loginAdmin() {
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }
}
