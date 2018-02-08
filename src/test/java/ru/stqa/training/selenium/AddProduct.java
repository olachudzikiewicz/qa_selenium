package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddProduct {
  private WebDriver dw;
  private WebDriverWait wait;

  @Before
  public void start() {
    dw = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    dw.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(dw, 10);
  }

  @After
  public void stop() {
    dw.quit();
    dw = null;
  }

  @Test
  public void testAddProduct()  {
    goToPage();
    Login();
    goToCatalog();
    List<WebElement> before = checkAmountOfProducts();
    goToCatalog();
    addNewProduct();
    fillGeneralInformation();
    fillInformation();
    fillPrices();
    saveProduct();
    goToCatalog();
    List<WebElement> after = checkAmountOfProducts();

    assertThat(before.size()+1, equalTo(after.size()));

  }

  private List<WebElement> checkAmountOfProducts() {
    dw.findElement(By.xpath("//tr[@class='row']//td//a[contains(text(),'Category')]")).click();
    return dw.findElements
            (By.xpath("//td//img[contains(@src,'/litecart/cache/')]"));
  }

  private void saveProduct() {
    dw.findElement(By.name("save")).click();
  }

  private void fillPrices() {
    dw.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();
    dw.findElement(By.name("purchase_price")).sendKeys(Keys.HOME + "10" + Keys.DELETE);
    dw.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).click();
    dw.findElement(By.xpath("//select[@name='purchase_price_currency_code']/option[@value='EUR']")).click();
    dw.findElement(By.name("prices[USD]")).sendKeys("22");
    dw.findElement(By.name("prices[EUR]")).sendKeys("32");
  }

  private void fillInformation() {
    dw.findElement(By.xpath("//a[contains(text(),'Information')]")).click();
    dw.findElement(By.xpath("//select[@name='manufacturer_id']")).click();
    dw.findElement(By.xpath("//select[@name='manufacturer_id']/option[@value='1']")).click();
    dw.findElement(By.name("keywords")).sendKeys("keywords");
    dw.findElement(By.name("short_description[en]")).sendKeys("Short description");
    dw.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys
            ("Description");
    dw.findElement(By.name("head_title[en]")).sendKeys("Head Title");
    dw.findElement(By.name("meta_description[en]")).sendKeys("Meta description");
  }

  private void fillGeneralInformation() {
    dw.findElement(By.xpath("//input[@value=1]")).click();
    String name = "Products_new";
    dw.findElement(By.name("name[en]")).sendKeys(name);
    long now = System.currentTimeMillis();
    dw.findElement(By.name("code")).sendKeys(String.format("%s",now));
    dw.findElement(By.xpath("//table//td//input[@data-priority=0]")).click();
    dw.findElement(By.xpath("//table//td//input[@data-name='Category']")).click();
    dw.findElement(By.xpath("//table//td//input[@value='1-3']")).click();
    dw.findElement(By.name("quantity")).sendKeys(Keys.HOME + "5002" + Keys.DELETE);
    File photo = new File("src/test/resources/test1.png");
    dw.findElement(By.name("new_images[]")).sendKeys(photo.getAbsolutePath());
    dw.findElement(By.name("date_valid_from")).sendKeys("2016-01-01");
    dw.findElement(By.name("date_valid_to")).sendKeys("2025-01-01");
  }

  private void addNewProduct() {
    dw.findElement(By.xpath("//a[contains(@href,'?category_id=0&app=catalog&doc=edit_product')]")).click();
  }

  private void goToCatalog() {
    dw.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
  }

  private void Login() {
    dw.findElement(By.name("username")).sendKeys("admin");
    dw.findElement(By.name("password")).sendKeys("admin");
    dw.findElement(By.name("login")).click();
  }

  private void goToPage() {
    dw.navigate().to("http://localhost/litecart/admin/login.php");
  }
}
