package ru.stqa.training.selenium.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductCheck {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void testProductCheck() {
   driver.navigate().to("http://localhost/litecart");

    List<WebElement> elements = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']"));

        for (WebElement element:elements) {
          int count = element.findElements(By.xpath(".//div[contains(@class, 'sticker')]")).size();
          Assert.assertEquals(count,1);
    }
  }
  }

