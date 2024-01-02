package space.bum.junit.pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginPage {
  private WebDriver webDriver;

  public LoginPage loginWith(String username, String password) {
    webDriver.findElement(By.id("username")).sendKeys(username);
    webDriver.findElement(By.id("password")).sendKeys(password);
    webDriver.findElement(By.cssSelector("#login button")).click();

    return this;
  }

  public void thenLoginSuccessful() {
    assertTrue(
        webDriver.findElement(By.cssSelector("#flash.success")).isDisplayed());
    assertTrue(webDriver.findElement(By.cssSelector("[href=\"/logout\"]"))
        .isDisplayed());
  }

  public void thenLoginUnsuccessful() {
    assertTrue(webDriver.findElement(By.id("username")).isDisplayed());
    assertTrue(webDriver.findElement(By.id("password")).isDisplayed());
  }
}
