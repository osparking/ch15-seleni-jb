package space.bum.junit.browse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WikipediaAccessTest {

  private RemoteWebDriver driver;

  @AfterEach
  void tearDown() {
    driver.quit();
  }

  @Test
  @DisplayName("한글 위키사전 토론 링크 방문 시험")
  void testWikiLinkAccess() {
    driver.get("https://ko.wikipedia.org/");
    assertThat(driver.getTitle(), is("위키백과, 우리 모두의 백과사전"));

    WebElement wikinews = driver.findElement(By.linkText("토론"));
    assertTrue(wikinews.isDisplayed());

    wikinews.click();
    assertThat(driver.getTitle(), is("위키백과토론:대문 - 위키백과, 우리 모두의 백과사전"));
  }

  @BeforeEach
  void setUp() {
    driver = new FirefoxDriver();
  }

}
