package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


    //Menu:
    driver.findElement(By.xpath("//span[contains(text(),'Appearence')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Template')]")).click();
    Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Template')]")).getText(),
            driver.findElement(By.xpath("//h1[contains(@style,'margin-top')]")).getText());
    driver.findElement(By.xpath("//span[contains(text(),'Logotype')]")).click();


    driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
    driver.findElement(By.xpath("//li[@id='doc-catalog']//span[contains(text(),'Catalog')]")).click(); //!!!
    driver.findElement(By.xpath("//span[contains(text(),'Product Groups')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Option Groups')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Manufacturers')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Suppliers')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Delivery Statuses')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Sold Out Statuses')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Quantity Units')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'CSV Import/Export')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Currencies')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();
    driver.findElement(By.xpath("//li[@id='doc-customers']//span[contains(text(),'Customers')]")).click();
    driver.findElement(By.xpath("//li[@id='doc-csv']//span[contains(text(),'CSV Import/Export')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Newsletter')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Languages')]")).click();
    driver.findElement(By.xpath("//li[@id='doc-languages']//span[contains(text(),'Languages')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Storage Encoding')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Modules')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Background Jobs')]")).click();
    driver.findElement(By.xpath("//li[@id='doc-customer']//span[contains(text(),'Customer')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Shipping')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Payment')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Order Total')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Order Success')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Order Action')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();
    driver.findElement(By.xpath("//li[@id='doc-orders']//span[contains(text(),'Orders')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Order Statuses')]")).click();

    driver.findElement(By.xpath("//a[contains(@href,'pages&doc=pages')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Reports')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Monthly Sales')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Most Sold Products')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Most Shopping Customers')]")).click();

     driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Store Info')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Defaults')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'General')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Listings')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Images')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Checkout')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Advanced')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Security')]")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Slides')]")).click();

     driver.findElement(By.xpath("//a[contains(@href,'tax_classes')]")).click();
     driver.findElement(By.xpath("//li[@id='doc-tax_classes']//span[contains(text(),'Tax Classes')]")).click();
     driver.findElement(By.xpath("//li[@id='doc-tax_rates']//span[contains(text(),'Tax Rates')]")).click();

     driver.findElement(By.xpath("//span[contains(text(),'Translations')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Search Translations')]")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Scan Files')]")).click();
     driver.findElement(By.xpath("//li[@id='doc-csv']//span[contains(text(),'CSV Import/Export')]")).click();

     driver.findElement(By.xpath("//span[contains(text(),'Users')]")).click();

     driver.findElement(By.xpath("//span[contains(text(),'vQmods')]")).click();
    Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'vQmods')]")).getText(),
            driver.findElement(By.xpath("//h1[contains(@style,'margin-top')]")).getText());
     driver.findElement(By.xpath("//li[@id='doc-vqmods']//span[contains(text(),'vQmods')]")).click();

  }
}
