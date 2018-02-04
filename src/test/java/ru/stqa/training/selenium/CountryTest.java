package ru.stqa.training.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountryTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);

    //Login:
    driver.navigate().to("http://localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void testCountry() {

    driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();

    List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='row']"));
    List<String> countryList = new ArrayList();

    for (WebElement row:rows) {
      String countryName = row.findElement(By.xpath("./td[5]")).getText();
      countryList.add(countryName);
    }
    assertThat(countryList, equalTo(countryList.stream().sorted().collect(Collectors.toList())));


    for (int i=0; i<rows.size(); i++) {
      List<String> countryListZone = new ArrayList();
      List<WebElement> rows2 = driver.findElements(By.xpath("//tr[@class='row']"));

     if (Integer.parseInt(rows2.get(i).findElement(By.xpath("./td[6]")).getText()) > 0 ) {
          rows2.get(i).findElement(By.xpath("./td[5]//a[contains(@href," +
                  "'http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code')]")).click();
       List<WebElement> rows1 = driver.findElements(By.xpath("//table[@id='table-zones']//td[descendant::*[contains(@name,'name')]]"));
       for (WebElement row1:rows1) {
         countryListZone.add(row1.getText());
       }
       countryListZone.remove(countryListZone.size()-1);
       assertThat(countryListZone, equalTo(countryListZone.stream().sorted().collect(Collectors.toList())));
       driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
     }
    }
  }

  @Test
  public void testGeoZones() {
    driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();

    List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='row']"));
    for (int i=0; i<rows.size(); i++) {
      List<WebElement> rows2 = driver.findElements(By.xpath("//tr[@class='row']"));
        rows2.get(i).findElement(By.xpath("./td[3]//a[contains(@href," +
                "'http://localhost/litecart/admin/?app=geo_zones&doc=edit_geo_zone&page=1&geo_zone_id')]")).click();
      List<WebElement> rows1 = driver.findElements(By.xpath("//table[@id='table-zones']//select[contains(@name,'zone_code')]/option[@selected='selected']"));
      List<String> countryZone1 = new ArrayList();
      for (WebElement row1:rows1) {
        countryZone1.add(row1.getText());
      }
      assertThat(countryZone1, equalTo(countryZone1.stream().sorted().collect(Collectors.toList())));
      driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
    }
  }
  }


