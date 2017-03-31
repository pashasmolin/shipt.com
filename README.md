# shipt.com

- This is a project that requires java and maven installed on your Mac or PC.
- The project uses page object pattern to describe each page in a separate class.
- Class EndToEndTests contains following E2E test: Log In, Search an item, Add the item to cart, 
  Verify the item and its price exist in the cart and matches selected item.
- The test clears shopping cart if it is not empty before adding any item to it, asserts the item
  and the price in the card, and takes screenshot if a test fails.
