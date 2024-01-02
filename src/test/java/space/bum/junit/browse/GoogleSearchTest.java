package space.bum.junit.browse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest {
  private RemoteWebDriver driver;

  // @formatter:off
  public static Collection<RemoteWebDriver> getBrowserBrands() {
    return Arrays.asList(new RemoteWebDriver[] { 
        new ChromeDriver(), new FirefoxDriver(), new EdgeDriver() });
  }

  @ParameterizedTest
  @MethodSource("getBrowserBrands")
  void testGoogleSearch(RemoteWebDriver driver) {
    driver.get("http://www.google.com");
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("en.wikipeida.org");
    element.sendKeys(Keys.ENTER);
    (new WebDriverWait(driver, Duration.ofSeconds(10)))
      .until(ExpectedConditions.presenceOfElementLocated(
          By.id("result-stats")));
    List<WebElement> findElements = driver
        .findElements(By.xpath("//*[@id='rso']//a/h3"));

    findElements.get(0).click();

    assertEquals("https://en.wikipedia.org/wiki/Main_Page",
        driver.getCurrentUrl());
    assertThat(driver.getTitle(), is("Wikipedia, the free encyclopedia"));

    WebElement contents = driver.findElement(By.linkText("Wikipedia"));
    assertTrue(contents.isDisplayed());

    contents.click();
    assertThat(driver.getTitle(), is("Wikipedia - Wikipedia"));
    this.driver = driver;
  }
  // @formatter:on
  
  @AfterEach
  void tearDown() {
    driver.quit();
  }
}
