# Use Cases


**Proposal of  of Possible use cases**


* Machine Installation
* Vending Machine _Business Setup_
* Restock / Refill -> Beneficiary
* Purchase -> Users

* AlertControl _Account Imballance_ 
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

* Validates new credentials
* Stores new credentials 

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

_The System should process given configuration in not more than 3 seconds

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


**Vending Machine AlertControl Vandalism**

**Actor:**  User
**Action:** Vandalism 

**User**

* Handles Machine with agressive and malicious intent

**System Response**
 
*  Internal Accelero meters report data higher than the specified threshold.
** The System sends alert report and corresponding logs to remote servers.
** The System sends an alert email to the Beneficiary.
** The System sends a text Message to the Beneficiary.

**Alternative Route**

The Internal Accelerometers may be faulty.
 * a) System is not able to detect possible vandalism.
 * b) System interpretates a normal purchase as vandalism.  

**Non Functional Requirements**

_The System should allow System wide recalibration of sensors._ 


**Vending Machine AlertControl Weight Imballance**

**Actor:**  Weight Sensor
**Action:** Report weight data  

**Weight Sensor**

* Reports data from sensors that does not correspond to overall estimation of current weight

**System Response**
 
*  The System checks if a purchase was made .
** The System checks what was purchased.
** The System caculates weight imballance against the individual weight of product thas was purchased.
** The System determines if the weigth ballance corresponds to a possible product that was not dispensed due to error. 
** The System validates should a refund be issued
** The System reports weight imballance and actions taken to the remote servers and logs

**Alternative Route**


 System fails to determine if current weight imballance was related to a foregoing purchase and cannot decide wether it should issue a refund 

**Non Functional Requirements**

**Performance**

_The response to a weight imballance and the decisive action to take should be performed in no more than 4 seconds_ 

**Security**

_The manipulation of weigth sensors to invoke a refund without cause should be prevented by precise and efficent calculations_



