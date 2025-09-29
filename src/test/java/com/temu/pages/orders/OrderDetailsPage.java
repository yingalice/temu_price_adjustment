package com.temu.pages.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class OrderDetailsPage extends BasePage {
  public static String title = "Temu | Order Detail";

  private By priceMatchAdjustmentButton = By.xpath("//span[text()='Price match/adjustment']//parent::div[@role='button']");
  private By orderItemName = By.xpath("//div[starts-with(@aria-label, 'item picture')]/following-sibling::div/span");

  public OrderDetailsPage(WebDriver driver) {
    super(driver, title);
  }

  public SelectARequestModal clickPriceMatchAdjustmentButton() {
    utility.click(priceMatchAdjustmentButton);
    return new SelectARequestModal(driver);
  }

  public String getOrderItemName() {
    return utility.getText(orderItemName);
  }
}
