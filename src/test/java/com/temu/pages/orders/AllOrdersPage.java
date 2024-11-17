package com.temu.pages.orders;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class AllOrdersPage extends BasePage {
  public static String title = "Temu | Orders";

  private By viewOrderDetailsLinks = By.xpath("//span[@role='link' and text()='View order details']//parent::div//preceding-sibling::div/div[1][not(contains(text(), 'Refunded'))]");
  private By viewMoreButton = By.xpath("//span[text()='View more']/parent::div[@role='button']");
  private By viewMoreLoadingStatus = By.xpath("//span[text()='Loading...']");

  public AllOrdersPage(WebDriver driver) {
    super(driver, title);
  }

  public void clickViewMoreButton() {
    utility.click(viewMoreButton);
    utility.waitInvisibilityOfElementLocated(viewMoreLoadingStatus);
  }

  public List<WebElement> getViewOrderDetailsLinks() {
    // Excludes 'Refunded' items
    return utility.findElements(viewOrderDetailsLinks);
  }

  public OrderDetailsPage clickOrderDetailLink(WebElement element) {
    utility.click(element);
    return new OrderDetailsPage(driver);
  }

  public boolean isViewMoreButtonPresent() {
    return utility.findElements(viewMoreButton).size() > 0;
  }
}