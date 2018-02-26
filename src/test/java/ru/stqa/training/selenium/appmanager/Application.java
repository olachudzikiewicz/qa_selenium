package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Application {

 private WebDriver driver;
 private WebDriverWait wait;
 private CartPage cartPage;
 private ProductPage productPage;
 private MainPage mainPage;

 public Application() {
   driver = new ChromeDriver();
   wait = new WebDriverWait(driver,10);
   cartPage = new CartPage(driver);
   productPage = new ProductPage(driver);
   mainPage = new MainPage(driver);

 }

 public void quit() {
   driver.quit();
   driver = null;
 }





   public MainPage getMainPage() {
    return mainPage;
  }

  public CartPage getCartPage() {
    return cartPage;
  }

  public ProductPage getProductPage() {
    return productPage;
  }

}
