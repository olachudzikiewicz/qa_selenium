package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductCart {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 2);
    driver.navigate().to("http://localhost/litecart/");
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void testProductAddRemove() throws InterruptedException {
    for (int i=0; i<3; i++) {
      List<WebElement> products = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']"));
      products.iterator().next().click();
      boolean spr = isElementPresent(By.name("options[Size])"));
      if (spr == true)
      {
        driver.findElement(By.xpath("//select[@name='options[Size]']")).click();
        driver.findElement(By.xpath("//select[@name='options[Size]']/option[@value='Small']")).click();
      }
      WebElement cart = driver.findElement(By.xpath("//a[@class='content']//span[@class='quantity'][contains" +
              "(text(),'"+i+"')]"));
      driver.findElement(By.name("add_cart_product")).click();
      //wait.until(ExpectedConditions.stalenessOf(cart));
      Thread.sleep(3000);
      driver.findElement(By.className("content")).click();
      driver.findElement(By.id("logotype-wrapper")).click();
    }
    driver.findElement(By.className("content")).click();

    for (int i=0; i<3; i++) {
      WebElement row = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']//strong[contains(text(),'Subtotal:')]"));
     // WebElement row = rows.get(6);
      driver.findElement(By.xpath("//button[@value='Remove']")).click();
      wait.until(ExpectedConditions.stalenessOf(row));
    }

  }

  private boolean isElementPresent(By element) {
    int il = driver.findElements(element).size();
    if (il>0) {
      return true;
    }
    else {
      return false;
    }
  }
}
