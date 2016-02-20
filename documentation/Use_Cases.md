# Use Cases


**Proposal of Possible use cases**


* Machine Installation
* Vending Machine _Business Setup_
* Restock / Refill -> Beneficiary
* Purchase -> Users

* AlertControl _Account Imballance_-> 
* AlertControl _Temperature Imballance_ 
* AlertControl _Vandalism_         // Accelerometer report data higher than specified // 
* AlertControl _System Fault_      // Ambient light sensor reports data lower than specified  
* AlertControl _Weight Imbalance_  // current actual weights in each slots does not correspond with current estimation of overall weight in each slot  


**Machine Instalation:**

**Actor: **Machine Provider 
**Action: **Setup

**Machine Provider**

* Installs Vending Machine
* Enters (Default Password,Default Username,Location,ID)
* Enables remote access and monitoring for beneficiary

**System Response**

* Validate and accept credentials
* Send new credentials to remote servers
* System operates and is accessible locally and/or remotely by beneficiary  

**Alternative Route**
 _Credential submission not accepted_
_Network error prevents sending new credentials to remote servers_

**Non Functional Requirements** 

Login credentials should stored in a save storage area on the Vending Machine
and encrypted.

**Machine Instalation:**

**Actor:** Beneficiary
**Action:**Login setup

**Beneficiary**

* Selects change login from Managment Interface
* enters new username in provided Input Box
* enters new password in provided Input Box
* presses save 

**System Response**

** Validates new credentials
**Stores new credentials 

**Alternative Route**

_Beneficiary supplies password not adhering to required format_
_Vending Machine does not accept input and alerts beneficiary to his error_
 
**Non Functional Requirements**

**Security**

New username and password must be stored in a save location and encrypted

**Machine Instalation:**

**Actor:** Beneficiary
**Action:**Login

**Beneficiary**

* Enters his username
* Enters his password

**System Response**

* Validate credentials
* Allow access to Managment interface

**Alternative Route**

_Machine Provider supplies wrong username or password_
_Vending Machine denies access to managment Interface_
 
**Non Functional Requirements**

* The Managment Interface  should load in a timely fashion within 1 to 2 seconds
* The Managment Interface should be easy to use and functions should be displayed in a disambiguateded manner

**Vending Machine Business Setup:**

**Actor:** Beneficiary
**Action:** Business Setup

**Beneficiary**

* Chooses Product_Mapping from Management Interface
* Enters product name into corresponding input box  and clicks OK button.
* Selects a Product name from a dropdown list
* Selects a desired slot location from a dropdown Menu.
* Enters a desired price into corresponding input box and clicks OK button.
* Selects A desired Price from dropdown Menu
** 1.) Selects a desired temperature range for the current product from a dropdown menu.
** 2.) Selects N/A from temperature dropdown to indicate no temperature needed.
* Selects a Quantity for product from a dropdown Menu
* Presses Save on Managment Interface

**System Response**

* validates and accepts price input
* validates and and accepts slot allocation
* accpets temperature range
* accepts and stores created configurations.

**Alternative Route**

The Beneficiary enters wrong format for price:

* Beneficiary is alerted to his error and machine does not accept input

Beneficiary provides wrong quantity for product

* System fails to calculate the individual weight of product. 


**Non Functional Requirements**

**Performance**

_The System should process given configuration in not more than 5 seconds

**Extensibility** 

_The System should allow the Machine Provider to modify and extend the list of selections in the Managment Inteface dropdown lists


**Usability**

The Machine Provider must inform possible suppliers about changes made in the Vending Machine Business Setup.

**Vending Machine stock & refill:**

**Actor:** Supplier
**Action:** stock / refill

**Supplier**

* opens Machines door
* places goods into machine
* locks the door

**System Response**
 
* System responds to supplier locking the door
** System Calculates individual weight for products in allocated slots
** System initializes AlertControl according to configuration
** System calculates an estimate of overall product value.
** System calculates offset against current available money in the Machine

**Alternative Route**

Supplier places products in the wrong slots
System fails to estimate true overall estimate of current money in the system

**Non Functional Requirements**

**Extensibility**

_The Internal process of allocating and specifying products should be modifyable by the Beneficiary_

**Performance**

_The System should update the current product and financial state in not less 30 seconds


**Purchase Items from Vending Machine**

**Actor**: Users
**Action**: Purchase items

 **Users**

 **Select a prefer item by pressing a button
 **Insert cash or card for payment
 **Get or pick up item from the from base of the machine
  
**System Response**

 **Vending Machine reads and calculate the prefer item details (Type, slot-line, numbers, amount)
 **Vending Machine display amount due and receive the proportionate cash value
 **Vending Machine dispense or drop the item selected for user to pick it up

**Alternate route**
 _Actor Action
 *Users select item and did not insert cash or payment
 **Users select wrong item and insert cash
 
 _System Response
 **Vending Machine refuse to to dispense item
 **Vending Machine refuse to dispense item and refund cash.
 
**Non Functional Requirement**:-    *Performance*
 
 _The response from Vending machine is faster and correct_
 
 
*AlertControl use case*
 
**Actor**: Temperature
*Action*: Reads temperatures

_Temperatures_
*Sense the ambient environ for change in temperatures proportionate to the value set for the environ

**System Response**
 *Adjust the degree of coldness relatively.
 
**Alternate Route**
  *Temperature sensor not reading correctly the set-up temperature environ
  *Temperature function refuse to work.
  
       
       
