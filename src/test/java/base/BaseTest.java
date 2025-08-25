package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.temu.pages.HomePage;
import com.temu.pages.login.LoginModal;

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
  public void setUpTools() {
    utility = new Utility(driver);
    softAssert = new SoftAssert();
  }

  @BeforeMethod
  public void login() {
    driver.get(url);
    String email = System.getenv("TEMU_EMAIL");
    String password = System.getenv("TEMU_PASSWORD");
    
    homePage = new HomePage(driver);
    LoginModal loginModal = homePage.goToSignInRegister();
    homePage = loginModal.loginIntoApplication(email, password);  
  }

  @AfterClass
  public void tearDown() {
    // utility.delay(1000);
    driver.quit();
  }
}
