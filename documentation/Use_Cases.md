# Proposal of Possible use cases 





## Use Cases

1. Machine Installation
2. Vending Machine _Business Setup_
3. Restock  _Refill Beneficiary_
4. Purchase _User_
5. AlertControl _Account Imballance_ 
6. AlertControl _Temperature Imballance_
7. AlertControl _Vandalism_ 
8. AlertControl _Vandalism_   
9. AlertControl _Weight Imbalance_   


##Machine Instalation:

**Actor:**Machine Provider

**Action:**Setup

###Machine Provider

* Installs Vending Machine
* Enters (Default Password,Default Username,Location,ID)
* Enables remote access and monitoring for beneficiary

###System Response

* Validate and accept credentials
* Send new credentials to remote servers
* System operates and is accessible locally and/or remotely by beneficiary  

###Alternative Route
Credential submission not accepted_
Network error prevents sending new credentials to remote servers_

###Non Functional Requirements 

####Security

_Login credentials should stored in a save storage area on the Vending Machine
and encrypted._

##Machine Setup

**Actor:**Beneficiary

**Action:**Login setup

###Beneficiary

* Selects change login from Main Menu
* enters new username in provided Input Box
* enters new password in provided Input Box
* presses save 

###System Response

* Validate new credentials
* Stores new credentials 

###Alternative Route

Beneficiary supplies password not adhering to required format
Vending Machine does not accept input and alerts beneficiary to his error
 
###Non Functional Requirements

####Security

_New username and password must be stored in a save location and encrypted_

##Login:

**Actor:**Beneficiary

**Action:**Login

###Beneficiary

* Enters his username
* Enters his password

###System Response

* Validate credentials
* Allow access to Managment interface

###Alternative Route

* Machine Provider supplies wrong username or password
* Vending Machine denies access to managment Interface

###Non Functional Requirements

####Performance

* The Managment Interface  should load in a timely fashion within 1 to 2 seconds
* The Managment Interface should be easy to use and functions should be displayed in a disambiguateded manner

##Vending Machine Business Setup:

**Actor:** Beneficiary

**Action:** Business Setup

###Beneficiary

* Chooses Product_Mapping from Main Menu
* Enters product name into corresponding input box  and clicks OK button.
* Selects a Product name from a dropdown list
* Selects a desired slot location from a dropdown Menu.
* Enters a desired price into corresponding input box and clicks OK button.
* Selects A desired Price from dropdown Menu
** 1.) Selects a desired temperature range for the current product from a dropdown menu.
** 2.) Selects N/A from temperature dropdown to indicate no temperature needed.
* Selects a Quantity for product from a dropdown Menu
* Presses Save on Managment Interface

###System Response

* validates and accepts price input
* validates and and accepts slot allocation
* accpets temperature range
* accepts and stores created configurations.

###Alternative Route

* The Beneficiary enters wrong format for price:
* Beneficiary is alerted to his error and machine does not accept input
* Beneficiary provides wrong quantity for product
* System fails to calculate the individual weight of product. 

###Non Functional Requirements

####Performance

_The System should process given configuration in not more than 5 seconds_

####Extensibility 

_The System should allow the Machine Provider to modify and extend the list of selections in the Managment Inteface dropdown lists_


####Usability

_The Machine Provider must inform possible suppliers about changes made in the Vending Machine Business Setup._

##Vending Machine stock & refill:

**Actor:** Supplier

**Action:** stock / refill

###Supplier

* opens Machines door
* places goods into machine
* locks the door

###System Response
 
* System responds to supplier locking the door
* System Calculates individual weight for products in allocated slots
 1. System initializes AlertControl according to configuration
 2. System calculates an estimate of overall product value.
 3. System calculates offset against current available money in the Machine

###Alternative Route

* Supplier places products in the wrong slots
* System fails to estimate true overall estimate of current money in the system

###Non Functional Requirements

####Extensibility

_The Internal process of allocating and specifying products should be modifyable by the Beneficiary_

###Performance

_The System should update the current product and financial state in not less 30 seconds_


##Purchase Items from Vending Machine

**Actor**: Users

**Action**: Purchase items

###Users

 * Select a prefer item by pressing a button
 * Insert cash or card for payment
 * Get or pick up item from the from base of the machine

###System Response

 * Vending Machine reads and calculate the prefer item details (Type, slot-line, numbers, amount)
 * Vending Machine reads and calculate the prefer item details (Type, slot-line, numbers, amount)
 * Vending Machine display amount due and receive the proportionate cash value
 * Vending Machine dispense or drop the item selected for user to pick it up

###Alternate route

**Actor:** Action
 
 * Users select item and did not insert cash or payment
 * Users select wrong item and insert cash

###System Response

 * Vending Machine refuse to to dispense item
 * Vending Machine refuse to dispense item and refund cash.

###Non Functional Requirement

####Performance
 
 _The response from Vending machine is faster and correct_
 
 
##AlertControl use case
 
**Actor**: Temperature

**Action**: Reads temperatures

###Temperatures

* Sense the ambient environ for change in temperatures proportionate to the value set for the environ

###System Response
* Adjust the degree of coldness relatively.

###Alternate Route

* Temperature sensor not reading correctly the set-up temperature environ
* Temperature function refuse to work.

##Vending Machine AlertControl Vandalism

**Actor:**  User

**Action:** Vandalism 

###User

* Handles Machine with agressive and malicious intent

###System Response
 
*  Internal Accelero meters report data higher than the specified threshold.
 1. The System sends alert report and corresponding logs to remote servers.
 2. The System sends an alert email to the Beneficiary.
 3. The System sends a text Message to the Beneficiary.

###Alternative Route

* The Internal Accelerometers may be faulty.
 1. System is not able to detect possible vandalism.
 2. System interpretates a normal purchase as vandalism.  

###Non Functional Requirements

###Extensability

_The System should allow System wide recalibration of sensors._ 


##Vending Machine AlertControl Weight Imballance

**Actor:**  Weight Sensor

**Action:** Report weight data  

###Weight Sensor

* Reports data from sensors that does not correspond to overall estimation of current weight

###System Response
 
*  The System checks if a purchase was made .
 1. The System checks what was purchased.
 2. The System caculates weight imballance against the individual weight of product thas was purchased.
 3. The System determines if the weigth ballance corresponds to a possible product that was not dispensed due to error. 
 4. The System validates should a refund be issued
 5. The System reports weight imballance and actions taken to the remote servers and logs

###Alternative Route

System fails to determine if current weight imballance was related to a foregoing purchase and cannot decide wether it should issue a refund 

###Non Functional Requirements

####Performance

_The response to a weight imballance and the decisive action to take should be performed in no more than 4 seconds_ 

####Security

_The manipulation of weigth sensors to invoke a refund without cause should be prevented by precise and efficent calculation_
