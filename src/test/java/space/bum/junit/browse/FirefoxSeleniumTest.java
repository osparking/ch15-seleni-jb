package space.bum.junit.browse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxSeleniumTest {
  private WebDriver driver;
  
  @Test
  void testManning() {
    driver.get("https://www.manning.com/");
    assertThat(driver.getTitle(), is("Manning"));
  }
  
  @Test
  void testGoogle() {
    driver.get("https://www.google.com/");
    assertThat(driver.getTitle(), is("Google"));
  }
  
  @AfterEach
  void tearDown() {
    driver.quit();
  }
  
  @BeforeEach
  void setUp() {
    driver = new FirefoxDriver();
  }
}
