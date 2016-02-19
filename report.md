# Narrative

**Introduction**

The European Vending Association estimates that every day 82 million food and drink items are purchased from a vending machine, by European customers (including Russia, Turkey, and Ukraine). 
In 2012 the field of operating machines has remained almost stable with 3.77 million operating machines. 80% of these machines where located in Europe’s 6 big leading markets the big 6 (Italy, France, Germany, the Netherlands, Spain and the United Kingdom.).
An Interesting fact according to the European Vending Association is that 60% of these operating machines are hot drinks vending machines whereas the remaining 40% can be evenly divided between cold drinks and a mix of both hot drinks and cold drinks vending machines.
The total turnover from vending machines in 2012 was  €11.3 billion.
The total number of items sold through vending machines in 2012 was 30 billion units, of which 24 billion items were dispensed in the Big Six.  

![GitHub Logo](http://2.bp.blogspot.com/-2DWjLZRjXP8/U1Z3GEuWYpI/AAAAAAAABb8/U4K7rEvQ4Xg/s1600/State+Design+Pattern.PNG)
Format: ![Alt Text](url)


**EasyVending** 



![Github Logo](http://lh5.ggpht.com/_xHVsCVak-6A/TAs9k37WaII/AAAAAAAARzM/fHrEPpsvHW8/image_thumb%5B41%5D.png?imgmax=800)

(We can change this name later)

We are proposing a system hereafter **EasyVending** to Vending Machine Manufacturers (VMMs), that promises to understand easy interfacing" and making it the core and heart of every vending machine.

**EasyVending's** interfaces can be broken down into 7 categories 

* Administrative Setup
* Accounting
* Alert Control
* Inventory Control
* Pricing
* Order(Products)
* Shelve Product Mapping Internal 
* Reports (Data accumulated from sensors i.e. temperature etc.)

With the advent of increasing technology and embedded systems growing with computational capabilities, EasyVending will make use of these advances and interface with the vending machines main business operations allowing customers to manage their machines without prior programming or technological background.
VMM's who are using EasyVending promise their customers full control and maintainability. Most vending machines that are currently operating have limited interfacing options which makes the customer often depend on repair services usually conducted by individuals not associated with the customer’s main business that produces extra cost which in most cases can be avoided. 
Additionally VMM's that use EasyVending provide customers access to their vending machines through interfacing locally and/or remotely, add or remove products, change prices, adjust currency settings, generate sell reports, manage temperature settings, keep records of temperatures within the machines which has become a key factor for customers that use their vending machines to sell hot beverages.

The Administrative setup creates an Administrator for the vending machine taking in business related customers details and access information such as username and password. An Administrator can share some of his duties by creating a secondary Administrator hereinafter Manager. 
Managers fall under a system restriction and can only access:
* Inventory Control
* shelve mapping 
* Alert Control.

**Accounting** 

"Northbound interface" is tracking all sales conducted on the machine and keeps track of transaction details, "Southbound Interface" allows the Administrators to see current account details, set remote bank accounts,
And approve outstanding and upcoming transactions.

**AlertControl**

The "Northbound" Interface of Alert Control is concerned to keep an eye on the internal stability of the system, i.e. make sure that temperature sensors don't report values that exceed the required ranges, the account balance corresponds with the current product sales, no error reporting within the Vending machines Operating System and possible vandalism.
The "Southbound" Interface of Alert control is concerned with collecting recording and corresponding to severity sending reports out the Administrators and Managers. 

**Inventory Control** 

Allows the Administrators and Managers to keep track of outgoing and incoming new sales products. Outgoing products are registered and recorded immediately once a sale has been initiated, the Administrators and Managers are responsible to make sure that the Vending machine has records of these products by supplying product data either through csv, or son files or manually.

**Pricing** 

Administrators can set and change pricing policies for individual products, refer to machines Order recommendations and Order outstanding products by simply clicking a button.

**Shelve Product Mapping** 

Allows Administrators to correctly map and locate their products within the machine. 

**EasyVending's** revenue stream will come from VMM's who are using EasyVending as their solution, in the form of sale commission and/or a one-time payment for EasyVending.
