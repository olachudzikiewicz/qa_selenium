package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage {

  protected WebDriver driver;
  private WebDriverWait wait;

  public ProductPage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 10);
  }

  public void AddItem( int ilosc) {
    for (int i=0; i<ilosc; i++) {
      List<WebElement> products = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']"));
      products.iterator().next().click();
      boolean spr = isElementPresent(By.xpath("//select[@name='options[Size]']"));
      if (spr == true)
      {
        driver.findElement(By.xpath("//select[@name='options[Size]']")).click();
        driver.findElement(By.xpath("//select[@name='options[Size]']//option[@value='Small']")).click();
      }
      WebElement cart = driver.findElement(By.xpath("//a[@class='content']//span[@class='quantity'][contains" +
              "(text(),'"+i+"')]"));
      driver.findElement(By.name("add_cart_product")).click();
      wait.until(textToBePresentInElement(cart,String.format("%s",i+1)));
      driver.findElement(By.className("content")).click();
      driver.findElement(By.id("logotype-wrapper")).click();
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
