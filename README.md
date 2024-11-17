# Temu Price Adjustment

## About
- Automatically checks if any items have dropped in price (30 day price guarantee)
- If so, request a price adjustment in the form of temu credit
  - NOTE: Could not test if the refund works, due to lack of examples (need items with a price drop to test)

## Setup
- Create login environment variables: `TEMU_EMAIL` and `TEMU_PASSWORD`

## Run
- Run `mvn clean test` in command line\
OR
- Run anything in `com.temu.tests`

## Workflow
- Login to Temu.com
  - Note: Must manually solve captcha
- Go to the main orders page, which lists all orders
- Go into each individual order's page (skipping any that were entirely refunded)
  - Click on the price adjustment button
    - Possible paths:
      1.  Price did not drop (Modal with "sorry" message)
          - Action: Move on to next item
      2.  Price dropped (New price adjustment page)
          - Action: Click request price adjustment button and select temu credit as the refund method.  Log item to console.
- Go back to the main order page, and go to the next item.  Click 'View More' button as necessary
  - Note: Will need to get the list of items again everytime.  The orders are not an `<a>` link, but a `<div>` to click on, and everytime you navigate back, the DOM changes, and Selenium will need the updated version
- Stop when any are true:
  1.  Price adjustment button is missing (past 30 day price guarantee).  Log item to console.
  2.  There are no more orders