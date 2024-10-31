# Temu Price Adjustment

## About
- Automatically checks if any items have dropped in price (30 day price guarantee)
- If so, log item to console for manual action
  - Limitation: Could not automate the price adjustment due to lack of examples (none of my items had a price drop)
- Purpose: Brush up on Selenium skills

## Setup
- Create login environment variables: `TEMU_EMAIL` and `TEMU_PASSWORD`

## Run
- Run `mvn clean test` in command line\
OR
- Run anything in `com.temu.tests`

## Workflow
- Login to Temu.com
  - Note: Must manually solve captcha
- Go to main orders page, where it lists all orders
- Go into each individual order's page (skipping any that were entirely refunded)
  - Click on the price adjustment button
    - If the message says:
      - 'Sorry' --> Move on
      - Anything else --> Log item to console, to manually request a price adjustment
- Go back to the main order page, and go to the next item.  Click 'View More' button as necessary
  - Note: Will need to get the list of items again everytime.  The orders are not an `<a>` link, but a `<div>` to click on, and everytime you navigate back, the DOM changes, and Selenium will need the updated version
- Stop when:
  - Price adjustment button is missing (past 30 day price guarantee)
  - There are no more orders