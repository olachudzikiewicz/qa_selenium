package ru.stqa.training.selenium;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsTest {


  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);
    driver.navigate().to("http://localhost/litecart/");
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

  @Test
  public void testProductsName() {

    String nazwa = driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).getAttribute("title");


    driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click();

    assertThat(nazwa, equalTo(driver.findElement(By.xpath("//h1[@class='title']")).getText()));



  }

  @Test
  public void testProductsPrice() {
    String regularPrice = driver.findElement(By.xpath
            ("//div[@id='box-campaigns']//s[@class='regular-price']"))
            .getText();
    String campaignPrice = driver.findElement(By.xpath
            ("//div[@id='box-campaigns']//strong[@class='campaign-price']"))
            .getText();

    driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click();

    assertThat(regularPrice, equalTo(driver.findElement(By.xpath("//div[@class='information']//s[@class='regular-price']")).getText()));
    assertThat(campaignPrice, equalTo(driver.findElement(By.xpath
            ("//div[@class='information']//strong[@class='campaign-price']"))
            .getText()));
  }

  @Test
  public void testProductsColorRP() {
    String textColorRP = driver.findElement(By.xpath
            ("//div[@id='box-campaigns']//s[@class='regular-price']")).getCssValue("color");

    driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click();
    //Color:
    assertThat(textColorRP, equalTo(driver.findElement(By.xpath
            ("//div[@class='information']//s[@class='regular-price']"))
            .getCssValue("color")));


  }

  @Test
  public void testProductsColorCP() {

    String textColorCP = driver.findElement(By.xpath
            ("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getCssValue("color");

    driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click();

    //Color:
    assertThat(textColorCP, equalTo(driver.findElement(By.xpath
            ("//div[@class='information']//strong[@class='campaign-price']"))
            .getCssValue("color")));

  }

  @Test
  public void testProductsAttributeRP() {

    String textAttributeRP = driver.findElement(By.xpath
            ("//div[@id='box-campaigns']//s[@class='regular-price']")).getCssValue("text-decoration");


    driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click();

    // Attributes:
    assertThat(textAttributeRP, equalTo(driver.findElement(By.xpath
            ("//div[@class='information']//s[@class='regular-price']"))
            .getCssValue("text-decoration")));
  }

  @Test
  public void testProductsAttributeCP() {

    String textAttributeCP = driver.findElement(By.xpath
            ("//div[@id='box-campaigns']//strong[@class='campaign-price']")).getCssValue("font-weight");

    driver.findElement(By.xpath("//div[@id='box-campaigns']//a[@class='link']")).click();

    // Attributes:
    assertThat(textAttributeCP, equalTo(driver.findElement(By.xpath
            ("//div[@class='information']//strong[@class='campaign-price']"))
            .getCssValue("font-weight")));
  }
}



