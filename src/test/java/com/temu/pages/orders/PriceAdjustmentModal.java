package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PriceAdjustmentModal extends OrderDetailsPage {

  private By priceAdjustmentHeaderText = By.xpath("//div[@data-flag='newUi']/div/div[1]");

  public PriceAdjustmentModal(WebDriver driver) {
    super(driver);
  }

  public String getPriceAdjustmentHeaderText() {
    return utility.getText(priceAdjustmentHeaderText);
  }
}