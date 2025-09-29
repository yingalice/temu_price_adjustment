package com.temu.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;

import com.temu.pages.orders.AllOrdersPage;
import com.temu.pages.orders.OrderDetailsPage;
import com.temu.pages.orders.PriceAdjustmentModal;
import com.temu.pages.orders.SelectARequestModal;
import com.temu.pages.orders.PriceAdjustmentPage;
import com.temu.pages.orders.SelectRefundMethodModal;

import base.BaseTest;

public class PriceDropTests extends BaseTest {
  @Test
  public void checkForPriceDrop() {
    // Go to main orders page
    AllOrdersPage allOrdersPage = homePage.goToOrdersAndAccounts();

    // Click into each individual order, and attempt to get a price adjustment
    // Back out into the main Orders page, to get the next order
    // Stop when the price adjustment button is missing from the order (past 30 day price guarantee)
    // OR there are no more orders to check
    int i = 0;
    while (true) {
      // Must get each order "link" (<div>, not <a>) again after clicking "Back", 
      // otherwise you get StaleElementReferenceException
      // Click 'View More' to load more orders as necessary
      List<WebElement> links = allOrdersPage.getViewOrderDetailsLinks();
      while (i > links.size() - 1) {
        if (!allOrdersPage.isViewMoreButtonPresent()) {
          break;
        }
        allOrdersPage.clickViewMoreButton();
        links = allOrdersPage.getViewOrderDetailsLinks();
      }

      // Go to each individual order page, and click the "Price adjustment" button
      // If the button is missing, it means it's past the 30-day price guarantee, 
      // so stop checking the remaining older orders
      OrderDetailsPage orderDetailsPage = allOrdersPage.clickOrderDetailLink(links.get(i));
      String orderItemName = orderDetailsPage.getOrderItemName();
      try {
        orderDetailsPage.clickPriceMatchAdjustmentButton();
      } catch (TimeoutException e) {
        System.out.println("- [PRICE MATCH/ADJUSTMENT BUTTON MISSING (30 days)]: \n" +
                            orderItemName + "\n");
        break;
      }

      SelectARequestModal selectARequestModal = new SelectARequestModal(driver);
      selectARequestModal.clickRequestAPriceAdjustmentButton();
      // After clicking the request a price adjustment button, it either leads to
      // 1) modal saying sorry (price did not drop)
      // OR
      // 2) price adjustment page (price dropped)
      // Check which one it is, and take the appropriate action
      try {
        // Price has not dropped (modal) - Confirm text contains "Sorry"
        PriceAdjustmentModal priceAdjustmentModal = new PriceAdjustmentModal(driver);
        String actualText = priceAdjustmentModal.getPriceAdjustmentHeaderText();
        String expectedText = "Sorry";
        softAssert.assertTrue(actualText.contains(expectedText), 
                              "- Expected modal containing " + "'" + expectedText + "'" + " for item: \n" + 
                              orderItemName + ",\n" +
                              "but got " + "'" + actualText + "'\n");
      } catch (TimeoutException e) {
        // Price has dropped (new page) - Request price adjustment and select temu credit as the refund method
        PriceAdjustmentPage priceAdjustmentPage = new PriceAdjustmentPage(driver);
        SelectRefundMethodModal selectRefundMethodModal = priceAdjustmentPage.clickRequestPriceAdjustmentButton();
        priceAdjustmentPage = selectRefundMethodModal.requestTemuCreditRefund();
        System.out.println("- [PRICE DROP!!!]: \n" +
                            orderItemName + "\n" +
                            "Refund amount: " + priceAdjustmentPage.getRefundAmount() + "\n");
        utility.goBack();  // Back to individual order page
      }

      utility.goBack();  // Back to list of all orders
      i++;  // Go to next order
    }
    softAssert.assertAll();
  }
}