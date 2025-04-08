package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Utility {
  // Sections:
  //  - Generic
  //  - Wait
  //  - Elements
  //  - Javascript
  //  - Get
  //  - Action

  protected WebDriver driver;

  public Utility(WebDriver driver) {
    this.driver = driver;
  }

  // ===================
  // ===== Generic =====
  // ===================
  public void goBack() {
    driver.navigate().back();
  }
  
  public void delay(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // ================
  // ===== Wait =====
  // ================
  private static int defaultSeconds = 3;
  private WebDriverWait webdriver_wait(int seconds) {
    return new WebDriverWait(driver, Duration.ofSeconds(seconds));
  }

  public WebElement waitVisibilityOfElementLocated(int seconds, By locator) {
    return webdriver_wait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public WebElement waitVisibilityOfElementLocated(By locator) {
    return waitVisibilityOfElementLocated(defaultSeconds, locator);
  }

  public List<WebElement> waitVisibilityOfAllElementsLocatedBy(int seconds, By locator) {
    return webdriver_wait(seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  public List<WebElement> waitVisibilityOfAllElementsLocatedBy(By locator) {
    return waitVisibilityOfAllElementsLocatedBy(defaultSeconds, locator);
  }

  public WebElement waitElementToBeClickable(int seconds, By locator) {
    return webdriver_wait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
  }

  public WebElement waitElementToBeClickable(By locator) {
    return waitElementToBeClickable(defaultSeconds, locator);
  }

  public WebElement waitElementToBeClickable(int seconds, WebElement element) {
    return webdriver_wait(seconds).until(ExpectedConditions.elementToBeClickable(element));
  }

  public WebElement waitElementToBeClickable(WebElement element) {
    return waitElementToBeClickable(defaultSeconds, element);
  }

  public Boolean waitTitleContains(int seconds, String title) {
    return webdriver_wait(seconds).until(ExpectedConditions.titleContains(title));
  }

  public Boolean waitTitleContains(String title) {
    return waitTitleContains(defaultSeconds, title);
  }

  public void waitInvisibilityOfElementLocated(int seconds, By locator) {
    webdriver_wait(seconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public void waitInvisibilityOfElementLocated(By locator) {
    waitInvisibilityOfElementLocated(defaultSeconds, locator);
  }

  // ====================
  // ===== Elements =====
  // ====================
  public WebElement findElement(By locator) {
    return waitVisibilityOfElementLocated(locator);
  }

  public List<WebElement> findElements(By locator) {
    return waitVisibilityOfAllElementsLocatedBy(locator);
  }

  public void click(By locator) {
    waitElementToBeClickable(locator).click();
  }

  public void click(WebElement element) {
    waitElementToBeClickable(element).click();
  }

  public void type(By locator, String text) {
    findElement(locator).sendKeys(text);
  }

  public void clear(By locator) {
    findElement(locator).clear();
  }

  // ======================
  // ===== Javascript =====
  // ======================
  private void executeJS(String jsScript, Object... elements) {
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript(jsScript, elements);
  }

  private void scroll(WebElement element, boolean alignToTop) {
    String jsScript = "arguments[0].scrollIntoView(" + alignToTop + ");";
    executeJS(jsScript, element);
  }

  public void scrollToTop() {
    String jsScript = "window.scrollTo(0, 0)";
    executeJS(jsScript);
  }
  
  public void scrollToElementJS(WebElement element) {
    scroll(element, true);
  }

  public void scrollToElementJS(By locator) {
    WebElement element = findElement(locator);
    scroll(element, true);
  }

  // ===============
  // ===== Get =====
  // ===============
  public String getText(By locator) {
    return findElement(locator).getText();
  }

  // ===================
  // ===== Actions =====
  // ===================
  private Actions actions() {
    return new Actions(driver);
  }

  public void moveToElement(WebElement element) {
    actions().moveToElement(element);
  }
}
