
# Blackwell task
+ [Overview](#overview)
+ [Requirements](#requirements)
+ [Objectives](#objectives)
+ [Summary](#summary)
+ [Assumptions](#assumptions)
+ [Improvements](#improvements)
    
## Overview
Please spend no more than an hour and list what assumptions you make and what you would do if you had more time.

## Requirements
Refunds can only be created for the orders already completed. Refunds can be created for partial or complete order.
Each order hence may have multiple refunds. Orders may have multiple items. Each item may have multiple quantities.
Each item in an order would have same shipping charge (applicable per individual piece of Orders can be charged
completely or partially. Each charge transaction would have a unique payment reference id. This reference id should
be used for refunds. Each ref id can be used multiple times, but the total of the amount refunded using an id cannot
exceed the actual amount originally charged on that id.

## Objectives
Please design an efficient solution to implement refunds. Feel free to use freehand diagrams / ER models /
flowcharts / use cases / or anything else you consider appropriate. Please create the outline of a Spring based
Java project. You may include classes, interfaces, stub methods, mock objects, unit tests to demonstrate your
technical approach for the solution. It is not expected that the project should be detailed or compile. We don't
need actual implementation so please just use comments wh erever appropriate. This would just be used to understand
your approach towards problem solving and demonstrate Java coding practices.

## Summary
I've created a basic Spring boot project with a small approach to the Refund task. Some of the classes are dummy classes
like for example the repository classes (they should connect to a DB and do the job but here basically, they create
a refund or an object as a new instance).

I spent a little more than 2 hours in creating it. I didn't try to run any code or the application.

I added comments in the code to explain what I have been trying to do in each part.

There are some basic unit tests dummy samples. The name of the tests are related with the task they should check.

## Assumptions
- I assumed the order process is already defined, but I needed a basic order as each refund is based in a previous order.
There is a basic order service for get a created order.
- I tried to make the classes immutables, to prevent their status could change once they are created. The only way to
modify their value currently is using the constructor.
- Refund controller is the basic rest service with two basic methods (get the active refunds and create a new refund).
- All the classes are managed in model package. They are basic classes and I thought it could be good to add an extra
 enum class to check if the item is a book or an e-book.
- Service package is the one managing the creation of a Refund and it is the main class of the project. Before creating
a new refund, it is ensuring there is not an error in the refund or the order:
   - The id of the order linked to the refund should be a valid order.
   - If there is not any refund for the order yet, for each item present in the refund I ensure the quantity is less or equal
   than the quantity bought in the order.
   - If there is already a partial refund for an order. For any item X defined in the order, the sum of the item quantity
   of previous refund + the item quantity of the current reund should not be bigger than the item quantity bought originally.
- If there is any error creating a refund an exception is thrown. All the basic exceptions are defined in exception package.

## Improvements
- There is not any code to mange the order creation at all (controller or methods in a service).
- There is not a way to save the items currently, everything is mocked. Ideally they should be saved in a database or
in a dummy array list or file just to test.
- It's a basic application, and it is not thread safe. Some of them could be managed by the database.