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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddCategory {
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
  public void testAddCategory() throws InterruptedException {
    goToPage();
    Login();

    //General:
    dw.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
    List<WebElement> rows = dw.findElements(By.xpath("//tr[@class='row']"));
    List<String> before = new ArrayList();

    for (WebElement row:rows) {
      String products = row.findElement(By.xpath("./td[3]")).getText();
      before.add(products);
    }

    dw.findElement(By.xpath("//a[contains(@href,'?app=catalog&doc=edit_category&parent_id=0')]")).click();
    dw.findElement(By.xpath("//input[@value=1]")).click();
    String name = "Category";
    dw.findElement(By.name("name[en]")).sendKeys(name);
    long now = System.currentTimeMillis();
    dw.findElement(By.name("code")).sendKeys(String.format("%s",now));
    dw.findElement(By.xpath("//select[@name='parent_id']")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='parent_id']/option[@value='7']"))).click();
    dw.findElement(By.xpath("//select[@name='parent_id']/option[@value='7']")).click();
    dw.findElement(By.name("google_taxonomy_id")).sendKeys(Keys.HOME + "5002" + Keys.DELETE);
    dw.findElement(By.xpath("//input[@type='checkbox']")).click();
    dw.findElement(By.xpath("//select[@name='parent_id']")).click();
    dw.findElement(By.xpath("//select[@name='list_style']/option[@value='rows']")).click();
    dw.findElement(By.name("keywords")).sendKeys("description");
    File photo = new File("src/test/resources/test1.png");
    dw.findElement(By.name("image")).sendKeys(photo.getAbsolutePath());

    //Information
    dw.findElement(By.xpath("//a[contains(text(),'Information')]")).click();
    dw.findElement(By.name("h1_title[en]")).sendKeys("Title");
    dw.findElement(By.name("short_description[en]")).sendKeys("Short description");
    dw.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys
            ("Description");
    dw.findElement(By.name("head_title[en]")).sendKeys("Head Title");
    dw.findElement(By.name("meta_description[en]")).sendKeys("Meta description");

    //Save:
    dw.findElement(By.name("save")).click();

    //check:
    dw.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
    List<WebElement> rows1 = dw.findElements(By.xpath("//tr[@class='row']"));
    List<String> after = new ArrayList();

    for (WebElement row:rows1) {
      String products = row.findElement(By.xpath("./td[3]")).getText();
      after.add(products);
    }

    before.add(name);

    assertThat(before.stream().sorted().collect(Collectors.toList()), equalTo(after.stream().sorted().collect(Collectors
            .toList())));










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
