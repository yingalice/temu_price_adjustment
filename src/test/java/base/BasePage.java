package base;

import org.openqa.selenium.WebDriver;

import utilities.Utility;

public class BasePage {
  protected WebDriver driver;
  protected Utility utility;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    utility = new Utility(driver);
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void verifyPageTitle(String title) {
    utility.waitTitleContains(title);
    String actualTitle = driver.getTitle();

    if (!actualTitle.equals(title)) {
      throw new IllegalStateException("Wrong page: " + 
                                      "\n Actual title: " + actualTitle + 
                                      "\n Expected title: " + title + "\n");
    }
  }
}