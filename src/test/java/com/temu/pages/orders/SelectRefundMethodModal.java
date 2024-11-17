package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectRefundMethodModal extends PriceAdjustmentPage {
  private By receiveInSecondsRadioButton = By.xpath("//span[text()='Receive in seconds']/ancestor::div[@role='combobox']/span[@role='radio']");
  private By submitButton = By.xpath("//div[@role='button']/span[text()='Submit']");

  public SelectRefundMethodModal(WebDriver driver) {
    super(driver);
  }

  private void clickReceiveInSecondsRadioButton() {
    utility.click(receiveInSecondsRadioButton);
  }

  private void clickSubmitButton() {
    utility.click(submitButton);
  }

  public PriceAdjustmentPage requestTemuCreditRefund() {
    clickReceiveInSecondsRadioButton();
    clickSubmitButton();
    return new PriceAdjustmentPage(driver);
  }
}
