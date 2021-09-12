<img alt="cool logo" src="https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3/blob/main/images/LBL-Logo-01.svg">

LBL Trucking Company sells fleets of Trucks to large companies all over the world. They need a new CRM system to manage prospective and live customers.


Use, Class and EER Diagrams
===========================

<img alt="use diagram" src="https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3/blob/main/images/Class_diagram_LBL-Page-2.png">

The use diagram reflects the project from the Sales Associate's perspective, defined as the primary user of the CRM system.
The functionality of the system is presented in the diagram below.

<img alt="class diagram" src="https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3/blob/Natalia/images/Class_diagram_LBL-Page-1.png">


Based on the use and class diagrams, an updatable database has been created with the following schema:

<img alt="eer diagram" src="https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3/blob/Natalia/images/EER%20diagram.png">

Menu and Commands
==========================

<img alt="menu screenshot" src="https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3/blob/main/images/menu%20screen.png">

Our user friendly menu provides instructions for using the system and covers a number of important requirements:
1. Track Leads, Opportunities, SalesReps and Accounts;
2. Convert Leads into Opportunities;
3. Associate an Opportunity with an Account.
4. Associate Contacts with an Opportunity.
5. Associate SalesReps with Leads and Opportunities.
6. Choose to create a new Accounts or assign an existing one.
7. Generate various reports

Information Display
==========================

<img alt="information display example" src="https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3/blob/main/images/output%20screen.png">

Our uniform UI throughout allows the user to easily find and understand information. 
We also introduced various colours to allow the user to separate blocks of information from one another. 
For example, all error methods are displayed in red colour.

Input Validation
==========================

In addition to the core search functionality, a number of input validations has been introduced to ensure correct and uniform input. 
Email, name and city inputs need to follow specific pattern.
Country input is checked against the *ISO list of countries* (in English only for simplicity and uniform input). 
Product, status and industry are checked against a set list of options organised in enums.
Think of this as a *'drop down'* list implemented in a console.
What's more, we don't let users go wild and put all sorts of things in our beautiful CRM. You'll be told off until you stop joking around.

Reports Navigation
==========================

Our Reporting section is split into various submenus grouped by reporting category. We thought it would make it easier to read and navigate through. The menu will guide through various options available.

How to
==========================

You will need to run the CRM from ***Main***.

Firstly, you will need to log on. If you want to have full functionality, use ***Admin*** as username and ***admin*** as password.
Alternatively, if you just want to have a little nosey, you can use username ***Guest*** and password ***guest***. 
The *guest* access will allow you to view the information stored but won't allow you to change anything. We don't want random curiosity mess up our CRM :P

Please, pay attention dual screen users! The login window tends to pop up in the second screen...

Once you're in, the system is a blank canvas! You're welcome to start adding entries to the database. However, if you don't want to start from scratch, use the command ***populate***. 

**The code** is split in various sections. In addition, we addressed the feedback for the previous homework and split the code into smaller chunks, so it's easier to review. 

Exceptions are handled in each class as well as in the MainMenu. A number of tests has been created to test inputs, validations and methods.

MySQL setup
==========================

create database ironhackhomework3;

use ironhackhomework3;

CREATE USER 'ironhacker'@'localhost' IDENTIFIED BY '1r0nH@ck3r';

GRANT ALL PRIVILEGES ON \*.\* TO 'ironhacker'@'localhost';

FLUSH PRIVILEGES;


***Enjoy!***
