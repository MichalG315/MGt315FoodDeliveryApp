# MGt315FoodDeliveryApp


The aim of the project was to create an application in the MVC architecture that would meet the following business requirements:

- The owner of a restaurant can create an account on a food ordering portal to offer his services.
- The owner of a restaurant can define his menu, i.e. specify the food category (appetizers, soups, courses, desserts) and enter a description of the item along with the price.
- For each of the given items, the owner of the restaurant can upload a photo to the application to make it easier for the customer to choose.
- The owner of a restaurant can provide a list of streets to which he delivers food.
- The customer can create an account.
- The customer can provide a street address and the application will display a list of restaurants offering the possibility of ordering food delivered.
- The list of premises is to be paginated and sorted.
- The customer can choose a restaurant and see its menu.
- The customer can place an order by selecting how many and what items he wants to order.
- The customer can see the confirmation of the placed order based on the generated order number.
- The customer may cancel the order if less than 20 minutes have passed since it was placed.
- The owner of the restaurant can see the orders, divided into pending and completed.
- The owner of the premises indicates that the order has been completed. 

To run the application you can use docker and the command: _**docker compose up**_

Application is divided to four parts - customer page, restaurant page, customer registration and restaurant registration.

In customer page you can check available restaurants (sort them and filter), check menu of specific restaurant and make an order. You can also
check your order history and cancel your order if the time since order does not exceed 20 minutes.
In order history page you can see random cat fact - from API https://catfact.ninja/#/.

In restaurant page you can check restaurants orders and mark them as delivered, check your menu items, add an item and add delivery address.

Testing credentials: 

Customer login: customer_testowy
Customer password: test

Restaurant login (Pizza center): restaurant_testowy1
Restaurant password: test

**Important! Only Pizza center have exemplary menu items!**

You can also register with default prepared date or provide yours.