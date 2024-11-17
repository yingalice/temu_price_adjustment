package base;

import org.openqa.selenium.WebDriver;

import utilities.Utility;

public class BasePage {
  protected WebDriver driver;
  protected Utility utility;

  public BasePage(WebDriver driver, String title) {
    this.driver = driver;
    utility = new Utility(driver);
    utility.waitTitleContains(title);
  }

  public WebDriver getDriver() {
    return driver;
  }
}