package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class PriceAdjustmentPage extends BasePage {
  public static String title = "Request a price adjustment";

  private By requestPriceAdjustmentButton = By.xpath("//div[@role='button' and text()[contains(., 'Request a price adjustment')]]");
  private By refundAmount = By.xpath("//span[text()='$']/following-sibling::span");

  public PriceAdjustmentPage(WebDriver driver) {
    super(driver, title);
  }

  public SelectRefundMethodModal clickRequestPriceAdjustmentButton() {
    utility.click(requestPriceAdjustmentButton);
    return new SelectRefundMethodModal(driver);
  }

  public String getRefundAmount() {
    return utility.getText(refundAmount);
  }
}
