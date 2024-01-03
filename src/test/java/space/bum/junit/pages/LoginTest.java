package space.bum.junit.pages;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class LoginTest {

  private WebDriver webDriver;

  private static Collection<WebDriver> getDriverBrands() {
    return Arrays.asList(new WebDriver[] { new ChromeDriver(),
        new FirefoxDriver(), new EdgeDriver() });
  }

  @ParameterizedTest
  @MethodSource("getDriverBrands")
  void loginSuccessTest(WebDriver webDriver) {
    this.webDriver = webDriver;
    Homepage homepage = new Homepage(webDriver);
    homepage.openFormAithentication()
        .loginWith("tomsmith", "SuperSecretPassword!").thenLoginSuccessful();
  }

  @ParameterizedTest
  @MethodSource("getDriverBrands")
  void loginFailureTest(WebDriver webDriver) {
    this.webDriver = webDriver;
    Homepage homepage = new Homepage(webDriver);
    homepage.openFormAithentication().loginWith("mary", "easypassword.")
        .thenLoginUnsuccessful();
  }

  @AfterEach
  void tearDown() {
    webDriver.quit();
  }
}
