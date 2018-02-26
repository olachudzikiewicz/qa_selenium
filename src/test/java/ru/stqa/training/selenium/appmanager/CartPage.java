package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage {
  protected WebDriver driver;
  private WebDriverWait wait;

  public CartPage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 10);
  }

  public void RemoveItem(int ilosc) {
    for (int i=0; i<ilosc; i++) {
      WebElement row = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']//strong[contains(text(),'Subtotal:')]"));
      driver.findElement(By.xpath("//button[@value='Remove']")).click();
      if (i<ilosc-1) {
        wait.until(ExpectedConditions.stalenessOf(row));
      } else {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='checkout-cart-wrapper']//a[contains(text(),'<< Back')]")));
        driver.findElement(By.xpath("//div[@id='checkout-cart-wrapper']//a[contains(text(),'<< Back')]")).click();
      }
    }
  }

}
