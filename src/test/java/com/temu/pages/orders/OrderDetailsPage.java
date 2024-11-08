package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class OrderDetailsPage extends BasePage {
  private String title = "Temu | Order Detail";
  private By priceAdjustmentButton = By.xpath("//span[text()='Price adjustment']//parent::div[@role='button']");
  private By orderItemDescription = By.xpath("//img[@aria-label='goods banner']/../following-sibling::div/span[@role='button']//span");
  public OrderDetailsPage(WebDriver driver) {
    super(driver);
    verifyPageTitle(title);
  }

  public PriceAdjustmentModal clickPriceAdjustmentButton() {
    utility.click(priceAdjustmentButton);
    return new PriceAdjustmentModal(driver);
  }

  public String getOrderItemDescription() {
    return utility.getText(orderItemDescription);
  }
}
