package com.temu.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.temu.pages.HomePage;

import base.BasePage;

public class LoginPage extends BasePage {
  public static String title = "Temu | Register & Login";
  
  private By emailField = By.xpath("//div[text()='Please enter your email address']/following-sibling::div//input");
  private By passwordField = By.xpath("//div[text()='Password']/following-sibling::div//input");
  private By continueButton = By.xpath("//button[@id='submit-button']");
  private By submitButton = By.xpath("//button[@id='submit-button']");

  public LoginPage(WebDriver driver) {
    super(driver, title);
  }
  
  public void typeEmail(String email) {
    utility.type(emailField, email);
  }

  public void typePassword(String password) {
    // Longer wait to manually solve captcha
    utility.waitVisibilityOfElementLocated(60, passwordField);  
    utility.type(passwordField, password);
  }

  public void clickContinueButton() {
    utility.click(continueButton);
  }

  public HomePage clickSubmitButton() {
    utility.click(submitButton);
    return new HomePage(driver);
  }

  public HomePage loginIntoApplication(String email, String password) {
    typeEmail(email);
    clickContinueButton();
    typePassword(password);
    return clickSubmitButton();
  }
}
