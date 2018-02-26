package ru.stqa.training.selenium.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductCart extends TestBase {

 /* private WebDriver driver;
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
  } */

  @Test
  public void testProductAddRemove() throws InterruptedException {
    app.getMainPage().GoToLitecartPage();
    app.getProductPage().AddItem(3);
    app.getMainPage().CartClick();
    app.getCartPage().RemoveItem(3);
  }


}
