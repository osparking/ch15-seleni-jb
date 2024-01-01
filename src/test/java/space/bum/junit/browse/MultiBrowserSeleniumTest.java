package space.bum.junit.browse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultiBrowserSeleniumTest {
  private WebDriver driver;

  private static Collection<WebDriver> getBrowserBrands() {
    return Arrays.asList(new WebDriver[] { new ChromeDriver(),
        new FirefoxDriver(), new EdgeDriver() });
  }

  @ParameterizedTest
  @MethodSource("getBrowserBrands")
  void testManning(WebDriver driver) {
    this.driver = driver;
    driver.get("https://www.manning.com/");
    assertThat(driver.getTitle(), is("Manning"));
  }

  @ParameterizedTest
  @MethodSource("getBrowserBrands")
  void testGoogle(WebDriver driver) {
    this.driver = driver;
    driver.get("https://www.google.com/");
    assertThat(driver.getTitle(), is("Google"));
  }

  @AfterEach
  void tearDown() {
    driver.quit();
  }
}
