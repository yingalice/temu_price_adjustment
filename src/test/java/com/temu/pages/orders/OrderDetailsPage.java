package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class OrderDetailsPage extends BasePage {
  public static String title = "Temu | Order Detail";

  private By priceAdjustmentButton = By.xpath("//span[text()='Price adjustment']//parent::div[@role='button']");
  private By orderItemName = By.xpath("//img[@aria-label='goods banner']/../following-sibling::div/span[@role='button']//span");

  public OrderDetailsPage(WebDriver driver) {
    super(driver, title);
  }

  public void clickPriceAdjustmentButton() {
    utility.click(priceAdjustmentButton);
    // We could get a modal saying sorry (no price drop) 
    // or a new page to submit the price adjustment (price drop)
    // We will check for this at the test level and redirect appropriately
  }

  public String getOrderItemName() {
    return utility.getText(orderItemName);
  }
}
