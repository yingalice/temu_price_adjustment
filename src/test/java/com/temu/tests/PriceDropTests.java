package com.temu.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;

import com.temu.pages.HomePage;
import com.temu.pages.login.LoginModalPage;
import com.temu.pages.orders.AllOrdersPage;
import com.temu.pages.orders.OrderDetailsPage;
import com.temu.pages.orders.PriceAdjustmentModal;
import base.BaseTest;

public class PriceDropTests extends BaseTest {
  @Test
  public void checkForPriceDrop() {
    String email = System.getenv("TEMU_EMAIL");
    String password = System.getenv("TEMU_PASSWORD");
    
    // Login and go to the main Orders page
    LoginModalPage loginModal = new LoginModalPage(driver);
    HomePage homePage = loginModal.loginIntoApplication(email, password);
    AllOrdersPage allOrdersPage = homePage.goToOrdersAndAccounts();

    // Click into each individual order, and attempt to get a price adjustment
    // Back out into the main Orders page, to get the next order
    // Stop when the price adjustment button is missing from the order (past 30 day price guarantee)
    // OR there are no more orders to check
    int i = 0;
    OrderDetailsPage orderDetailsPage;
    PriceAdjustmentModal priceAdjustmentModal;
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
      // Print a message if the price has dropped (handle manually afterwards, as I don't have any examples yet on how to claim the refund)
      // Click the browser's "Back" button to go to the main Orders page
      orderDetailsPage = allOrdersPage.clickOrderDetailLink(links.get(i));
      try {
        priceAdjustmentModal = orderDetailsPage.clickPriceAdjustmentButton();
      } catch (TimeoutException e) {
        System.out.println("- [PRICE ADJUSTMENT BUTTON MISSING (30 days)]: \n" +
                            orderDetailsPage.getOrderItemDescription() + "\n");
        break;
      }
      String actualText = priceAdjustmentModal.getPriceAdjustmentHeaderText();
      String expectedText = "Sorry";
      softAssert.assertTrue(actualText.contains(expectedText), 
                            "- [PRICE DROP]: \n" + 
                            orderDetailsPage.getOrderItemDescription() + "\n");
      utility.goBack();
      i++;
    }
    softAssert.assertAll();
  }
}