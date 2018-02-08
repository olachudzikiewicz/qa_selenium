package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginUser {

  private WebDriver dw;
  private WebDriverWait wait;

  @Before
  public void start() {
    dw = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    dw.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(dw, 10);
  }

  @Test
  public void testLoginUser() {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String password = "password";

    GoToPage();
    FillTheForm(email, password);
    CreateAccount();
    LogoutFromAccount();
    LoginToAccount(email, password);
    LogoutFromAccount();

  }

  private void LoginToAccount(String email, String password) {
    dw.findElement(By.name("email")).sendKeys(email);
    dw.findElement(By.name("password")).sendKeys(password);
    dw.findElement(By.name("login")).click();
  }

  private void LogoutFromAccount() {
    dw.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
  }

  private void CreateAccount() {
    dw.findElement(By.name("create_account")).click();
  }

  private void FillTheForm(String email, String password) {
    dw.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();
    dw.findElement(By.name("firstname")).sendKeys("FirstName");
    dw.findElement(By.name("lastname")).sendKeys("FirstName");
    dw.findElement(By.name("address1")).sendKeys("Adres");
    dw.findElement(By.name("postcode")).sendKeys("40-879");
    dw.findElement(By.name("city")).sendKeys("Katowice");
    dw.findElement(By.name("phone")).sendKeys("222333444");
    dw.findElement(By.name("email")).sendKeys(email);
    dw.findElement(By.xpath("//select[@name='country_code']")).click();
    dw.findElement(By.xpath("//select[@name='country_code']/option[text()='Poland']")).click();
    dw.findElement(By.name("password")).sendKeys(password);
    dw.findElement(By.name("confirmed_password")).sendKeys(password);
  }

  private void GoToPage() {
    dw.navigate().to("http://localhost/litecart");
  }

  @After
  public void stop() {
    dw.quit();
    dw = null;
  }


}
