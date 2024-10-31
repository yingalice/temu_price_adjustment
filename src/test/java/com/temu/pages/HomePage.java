package com.temu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.temu.pages.login.LoginModalPage;
import com.temu.pages.orders.AllOrdersPage;

import base.BasePage;

public class HomePage extends BasePage {
  private String title = "Temu | Explore the Latest Clothing, Beauty, Home, Jewelry & More";
  
  private By signInRegisterButton = By.xpath("//div[text()='Sign in / Register']//ancestor::div[@role='button']");
  private By ordersAndAccountsButton = By.xpath("//div[text()='Orders & Account']//ancestor::div[@role='button']");

  public HomePage(WebDriver driver) {
    super(driver);
    verifyPageTitle(title);
  }

  public LoginModalPage goToSignInRegister() {
    utility.click(signInRegisterButton);
    return new LoginModalPage(driver);
  }

  public AllOrdersPage goToOrdersAndAccounts() {
    utility.click(ordersAndAccountsButton);
    return new AllOrdersPage(driver);
  }
}
