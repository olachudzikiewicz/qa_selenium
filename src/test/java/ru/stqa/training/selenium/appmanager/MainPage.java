package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
  protected WebDriver driver;

  public MainPage(WebDriver driver) {
    this.driver = driver;
  }

  public void CartClick() {
    driver.findElement(By.className("content")).click();
  }

  public void GoToLitecartPage() {
    driver.navigate().to("http://localhost/litecart/");
  }
}
