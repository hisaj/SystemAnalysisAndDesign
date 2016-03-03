# Proposal of Possible use cases 





## Use Cases

1. Machine Installation
2. Vending Machine _Business Setup_
3. Restock  _Refill Beneficiary_
4. Purchase _User_
5. AlertControl _Account Imballance_ 
6. AlertControl _Temperature Imballance_
7. AlertControl _Vandalism_ 
8. AlertControl _Weight Imbalance_   


##Machine Instalation:

**Actor:**Technician

**Action:**Login Setup

###Technician

* Installs Vending Machine
* Enters (Default Password,Default Username,Location,ID)
* Selects change login from Main Menu
* enters  username in provided Input Box
* enters  password in provided Input Box
* presses save 
* Enables remote access and monitoring for beneficiary

###System Response

* Validate and accept credentials
* Send credentials to remote servers
* System operates and is accessible locally and/or remotely by technician 

###Alternative Route
Credential submission not accepted
Network error prevents sending new credentials to remote servers

###Non Functional Requirements 

####Security

_Login credentials should be encrypted when send over network_

##Login:

**Actor:**Technician

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

##Vending Machine Operations Setup:

**Actor:** Technician

**Action:** Operations Setup

###Technician

* Chooses Product_Mapping from Main Menu
* Enters product name into corresponding input box  and clicks OK button.
* Selects a product name from dropdown list
* Selects a desired slot location from dropdown Menu.
* Enters a desired price into corresponding input box and clicks OK button.
* Selects  desired Price from dropdown Menu
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

_The System should process given configuration in not more than 0.5 seconds_


####Usability

_Possible suppliers must knowe about changes made in the Vending Machine operations Setup._

##Reorder:

**Actor:** Supplier

**Action:** Reorder

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

###Performance

_The System should reorder in not less 1 second_

##Dispense

**Actor**: Users

**Action**: Dispense

###Users

 * Select a prefer item by pressing a button
 * Insert cash or card for payment
 * Get or pick up item from the from base of the machine

###System Response

 * System displays price of item 
 * System veryfies payment method.
 * Accepts payments
 * Displays amounts due.
 * Dispenses item selected.

###Alternate route

**Actor:** Action
 
 * Users select item and did not insert cash or payment

###System Response

 * Vending Machine refuse to to dispense item

###Non Functional Requirement

####Performance
 
 _mnachine should process payments and dispense items in not more than 0.5 seconds_
 
 
##AlertControl Temperature Imballance
 
**Actor**: Control

**Action**: Reads temperature

###Control

* Read temperature Sensors

###System Response

* Adjust Temperature according to configuration.
* Prevent dispense

###Alternate Route

* Temperature sensor faulty

##Alert Control Vandalism

**Actor:**  Control

**Action:** reads Acceleration 

###Control

*  Reads Accelero meters data, that is higher than the specified threshold.

###System Response
 
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

**Actor:**  Control

**Action:** reads weight data  

###Control

* Reads data from sensors that does not correspond to overall estimation of current weight

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
