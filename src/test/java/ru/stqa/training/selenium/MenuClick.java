package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MenuClick {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void testMenuClick() {
    //Login:
    driver.navigate().to("http://localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();


    List<WebElement> listToSize = driver.findElements((By.xpath("//ul[@id='box-apps-menu']/li/a")));
    for (int i = 0; i < listToSize.size(); i++) {
      List<WebElement> menuTop = driver.findElements((By.xpath("//ul[@id='box-apps-menu']/li/a")));
      menuTop.get(i).click();

      List<WebElement> nestedMenuToSize = new ArrayList();
      try {
        nestedMenuToSize = driver.findElements((By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul//span[@class='name']")));
      } catch (Exception e) {
        System.out.println("There is no nested menu");
      }

      if (nestedMenuToSize.size() > 0) {
        for (int j = 0; j < nestedMenuToSize.size(); j++) {
          List<WebElement> nestedMenu = driver.findElements((By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul//span[@class='name']")));
          nestedMenu.get(j).click();
          Assert.assertEquals(driver.findElement((By.xpath("//h1"))).getText(), driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul/li[@class='selected']//span[@class='name']")).getText());
        }
      } else {
        Assert.assertEquals(driver.findElement((By.xpath("//h1"))).getText(), driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/a/span[@class='name']")).getText());
      }
    }


  }
}

