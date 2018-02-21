package ru.stqa.training.selenium;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static com.google.common.io.Files.*;

public class TestBase {

  public static ThreadLocal<EventFiringWebDriver> t1Driver = new ThreadLocal<>();
  public EventFiringWebDriver driver;
  public WebDriverWait wait;
  public BrowserMobProxy proxy;

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by + "found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      File tempFile =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File ("screen-" + System.currentTimeMillis() + ".png" );
      try {
        copy(tempFile,screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(screen);
    }
  }

  @Before
  public void start() {
    if (t1Driver.get() != null) {
      driver = t1Driver.get();
      wait = new WebDriverWait(driver,10);
      return;
    }

    // start the proxy
    proxy = new BrowserMobProxyServer();
    proxy.start(0);

    // get the Selenium proxy object
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

    // configure it as a desired capability
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

   /* DesiredCapabilities cap = DesiredCapabilities.chrome();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs); */

    driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
    driver.register(new MyListener());
    t1Driver.set(driver);
    wait = new WebDriverWait(driver,10);

   // Runtime.getRuntime().addShutdownHook( new Thread(() -> {driver.quit(); driver = null; }));
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}
