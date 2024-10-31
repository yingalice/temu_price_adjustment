package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class PriceAdjustmentModal extends BasePage {
  private By priceAdjustmentHeaderText = By.xpath("//div[@data-flag='newUi']/div/div[1]");

  public PriceAdjustmentModal(WebDriver driver) {
    super(driver);
  }

  public String getPriceAdjustmentHeaderText() {
    return utility.getText(priceAdjustmentHeaderText);
  }
}