package space.bum.junit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {
  private WebDriver webDriver;

  public Homepage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }
  
  public LoginPage openFormAithentication() {
    webDriver.get("https://the-internet.herokuapp.com/");
    webDriver.findElement(By.cssSelector("[href=\"/login\"]")).click();
    return new LoginPage(webDriver);
  }
}
