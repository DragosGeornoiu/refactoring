# SupermarketReceipt-Refactoring-Kata

A kata created alsoo by Emily Bache:
[SupermarketReceipt-Refactoring-Kata/.](https://github.com/emilybache/SupermarketReceipt-Refactoring-Kata/)
A short video describing the problem is also provided on her youtube
channel: ["Detangle Bad Code Challenge"](https://youtu.be/EWB-VhEUoHE).

## Short description

This kata a variation of a popular kata described
in [codekata.com/kata/kata01-supermarket-pricing.](http://codekata.com/kata/kata01-supermarket-pricing/)
The aim of the exercise is to build automated tests for this code, refactor it, and add a new
feature.

## Requirements

The supermarket has a catalog with different types of products (rice, apples, milk,
toothbrushes,...). Each product has a price, and the total price of the shopping cart is the total
of all the prices of the items. You get a receipt that details the items you've bought, the total
price and any discounts that were applied.

The supermarket runs special deals, e.g.

- Buy two toothbrushes, get one free. Normal toothbrush price is €0.99
- 20% discount on apples, normal price €1.99 per kilo.
- 10% discount on rice, normal price €2.49 per bag
- Five tubes of toothpaste for €7.49, normal price €1.79
- Two boxes of cherry tomatoes for €0.99, normal price €0.69 per box.

These are just examples: the actual special deals change each week.

Create some test cases and aim to get good enough code coverage that you feel confident to do some
refactoring.

When you have good test cases, identify code smells such as Long Method, Feature Envy. Apply
relevant refactorings.

When you're confident you can handle this code, implement the new feature described below

### New feature: discounted bundles

The owner of the system has a new feature request. They want to introduce a new kind of special
offer - bundles. When you buy all the items in a product bundle you get 10% off the total for those
items. For example you could make a bundle offer of one toothbrush and one toothpaste. If you then
you buy one toothbrush and one toothpaste, the discount will be 10% of €0.99 + €1.79. If you instead
buy two toothbrushes and one toothpaste, you get the same discount as if you'd bought only one of
each - ie only complete bundles are discounted.

### New feature: HTML receipt

Currently we print a traditional ticket receipt. Now beeing a modern business we'd like to be able
to print or send a HTML version of the same receipt. All the data and number formatting should be
the same. However the layout should be html. You don't have to worry about the HTML template - a
designer will care of that - but we do need someone to keep duplication between the reports to a
bare minimum.

## Notes during work on solution

## Tests

The first step of Supermarket Receipt Kata needs to look through the statement, the cond and write
tests which will cover as much of the possible scenarios and code as possible. Doing this will allow
us to refactor the code afterwards with the assurance that we have the tests to rely on.

Looking through the classes and statements we can observe that it's not that easy to determine the
flow of the application. I added some notes observed at first glance of strange flows, again at this
point I am not trying to determine places that need to be refactored, I am just trying to understand
the code at a level that allows me to write a good suite of unit tests.

- The Teller seems to be the entry point, it holds a SupermarketCatalog and a mapping of offers from
  each product type. The SupermarketCatalog has to be passed to the Teller instance and the offers
  can be added afterwards using the addSpecialOffer method.
- The Teller class also provide the checksOutArticlesFrom, which receives a ShoppingCart as
  parameter. The main idea here would be probably that the ShoppingCart is per client, but the
  SupermarketCatalog holds all the possible products available. This method does a lot, it adds to
  the receipt each product from the shopping cart (Product p, double quantity, double price, double
  totalPrice) and calls the handleOffers method from the ShoppingCart object, which mutates the
  receipt parameter. An entire mess in these classes.
- The ShoppingCart also is quite hard to gasp. We discussed previously that it has a handleOffers
  method which generates the receipt I would like to say, but it just mutates the received receipt.
  Besides, that it holds the items as ProductQuantity and map of productQuantities representing each
  product and the quantity it has. Each time a product is added, a ProductQuantity is added with the
  product and the number of times it is bought, and at product to quantity mapping is increased,
  this behavior would mean that it would be allowed to add a product "test" with a quantity of 2 and
  afterwards add a second time the product "test" with a quantity of 3. The items will held 2
  entries of the 'test' product with one having quantity 2 and one having quantity 3, but the map
  holding the total quantities will hold the sum of all quantities, in this example 5.
- ....

##
