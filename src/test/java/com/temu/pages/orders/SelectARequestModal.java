package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectARequestModal extends OrderDetailsPage {
  private By requestAPriceAdjustmentButton = By.xpath("//span[text()='Request a price adjustment']//parent::div[@role='button']");

  public SelectARequestModal(WebDriver driver) {
    super(driver);
  }

  public void clickRequestAPriceAdjustmentButton() {
    utility.click(requestAPriceAdjustmentButton);
    // We could get a modal saying sorry (no price drop) 
    // or a new page to submit the price adjustment (price drop)
    // We will check for this at the test level and redirect appropriately
  }
}