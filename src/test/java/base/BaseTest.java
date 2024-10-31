package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.temu.pages.HomePage;
import utilities.Utility;

public class BaseTest {
  protected WebDriver driver;
  protected HomePage homePage;
  protected Utility utility;
  private String url = "http://www.temu.com";
  public SoftAssert softAssert;

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }
  
  @BeforeMethod
  public void openTemu() {
    driver.get(url);
    utility = new Utility(driver);
    softAssert = new SoftAssert();
  }

  @AfterClass
  public void tearDown() {
    // utility.delay(1000);
    driver.quit();
  }
}
