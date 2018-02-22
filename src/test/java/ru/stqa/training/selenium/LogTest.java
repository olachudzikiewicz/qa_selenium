package ru.stqa.training.selenium;

import net.lightbody.bmp.core.har.Har;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class LogTest extends TestBase {

  @Test
  public void testLogs() {
    login();

    driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();

    List<WebElement> listToSize = driver.findElements((By.xpath("//tr[@class='row']//td[descendant::*[contains(@class,'folder')]]//a")));
    for (int i = 0; i < listToSize.size(); i++) {
      List<WebElement> catalog = driver.findElements((By.xpath("//tr[@class='row']//td[descendant::*[contains(@class,'folder')]]//a")));
      catalog.get(i).click();

      List<WebElement> products = new ArrayList();

      try {
        products = driver.findElements((By.xpath("//tr[@class='row'][descendant::td[descendant::*[contains(@name,'products')]]]/td[descendant::img]/a")));
      } catch (Exception e) {
        System.out.println("There is no products in catalog");
      }

      if (products.size() > 0) {
        for (int j = 0; j < products.size(); j++) {
          List<WebElement> products1 = driver.findElements((By.xpath
                  ("//tr[@class='row'][descendant::td[descendant::*[contains(@name,'products')]]]/td[descendant::img]/a")));
        //  proxy.newHar();
          products1.get(j).click();
         // Har har = proxy.endHar();
         // har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest
        //          ().getUrl()));
          driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
          driver.findElement(By.name("cancel")).click();
        }

      }
    }
  }

  private void login() {
    driver.navigate().to("http://localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }
}
