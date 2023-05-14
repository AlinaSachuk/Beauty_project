# Beauty_project application

This project is a simple version of the beauty saloon service. It provides an opportunity to collect information about 
procedures, cosmetic products, employees. Also, this project provides an opportunity to collect information about 
customers with giving discount for them in the future.  

## Database

Application use PostgreSQL database. For start the application you need Postgres server (jdbc:postgresql://localhost:
5432/beauty_db) with created database 'beauty_db'. Database contains eight tables.

* Table _customer_table_ - contains personal information, authorization information and roles of beauty saloon 
customers;
* Table _employee_table_ - contains information about beauty saloon staff;
* Table _procedures_ - contains information about the procedures carried out in the beauty saloon;
* Table _cosmetic_products_ - contains information about the cosmetic products used and sold in the beauty saloon;
* Table _l_proced_prod_ - link table between procedures and cosmetic_products;
* Table _status_table_ - contains information about discounts valid in the beauty saloon;
* Table _visit_table_ - contains information about customer`s visits to the beauty saloon.

## Available endpoints for all users

* http://localhost:8080/customer/registration - POST method to register in Beauty_project application as a customer.

* http://localhost:8080/product - GET method, shows information about all cosmetic products presented in beauty saloon;
* http://localhost:8080/product/{id} - GET method, shows actual information about cosmetic product by ID;

* http://localhost:8080/procedure - GET method, shows information about all procedures carried out in the beauty saloon;
* http://localhost:8080/procedure/{id} - GET method, shows actual information about certain procedure by ID;

* http://localhost:8080/employee - GET method, shows information about all employees in the beauty saloon;
* http://localhost:8080/employee/{id} - GET method, shows information about certain employee by ID;

* http://localhost:8080/status - GET method, shows information about all statuses in the beauty saloon;
* http://localhost:8080/status/{id} - GET method, shows information about certain status by ID;

## Available endpoints for customers

* http://localhost:8080/product - GET method, shows information about all cosmetic products presented in beauty saloon;
* http://localhost:8080/product/{id} - GET method, shows actual information about cosmetic product by ID;

* http://localhost:8080/procedure - GET method, shows information about all procedures carried out in the beauty saloon;
* http://localhost:8080/procedure/{id} - GET method, shows actual information about certain procedure by ID;

* http://localhost:8080/employee - GET method, shows information about all employees in the beauty saloon;
* http://localhost:8080/employee/{id} - GET method, shows information about certain employee by ID;

* http://localhost:8080/status - GET method, shows information about all statuses in the beauty saloon;
* http://localhost:8080/status/{id} - GET method, shows information about certain status by ID;

* http://localhost:8080/customer/{id} - GET method, shows information about certain customer by ID;
* http://localhost:8080/customer/getVisits/{id} - GET method, shows information about all visits of customer by ID; 
* http://localhost:8080/customer/registration - POST method, create new customer;
* http://localhost:8080/customer/updateInfo - PUT method, change information about certain customer.

## Available endpoints for admin

* http://localhost:8080/product - GET method, shows information about all cosmetic products presented in beauty saloon;
* http://localhost:8080/product/{id} - GET method, shows actual information about cosmetic product by ID;
* http://localhost:8080/product - POST method, create cosmetic product;
* http://localhost:8080/product - PUT method, change information about certain cosmetic product;
* http://localhost:8080/product/{id} - DELETE method, delete cosmetic product by ID;

* http://localhost:8080/procedure - GET method, shows information about all procedures carried out in the beauty saloon;
* http://localhost:8080/procedure/{id} - GET method, shows actual information about certain procedure by ID;
* http://localhost:8080/procedure - POST method, create procedure;
* http://localhost:8080/procedure - PUT method, change information about certain procedure;
* http://localhost:8080/procedure/{id} - DELETE method, delete procedure by ID;

* http://localhost:8080/employee - GET method, shows information about all employees in the beauty saloon;
* http://localhost:8080/employee/{id} - GET method, shows information about certain employee by ID;
* http://localhost:8080/employee - POST method, create employee;
* http://localhost:8080/employee - PUT method, change information about certain employee;
* http://localhost:8080/employee/{id} - DELETE method, delete employee by ID;

* http://localhost:8080/status - GET method, shows information about all statuses in the beauty saloon;
* http://localhost:8080/status/{id} - GET method, shows information about certain status by ID;
* http://localhost:8080/status - POST method, create status;
* http://localhost:8080/status - PUT method, change information about certain status;
* http://localhost:8080/status/{id} - DELETE method, delete status by ID;

* http://localhost:8080/visit/getAll - GET method, shows information about all visits in the beauty saloon;
* http://localhost:8080/visit/{id} - GET method, shows information about certain visit by ID;
* http://localhost:8080/visit - POST method, create visit;
* http://localhost:8080/visit - PUT method, change information about certain visit;
* http://localhost:8080/visit/{id} - DELETE method, delete visit by ID;

* http://localhost:8080/customer/getAll - GET method, shows information about all customers in the beauty saloon;
* http://localhost:8080/customer/{id} - GET method, shows information about certain customer by ID;
* http://localhost:8080/customer/getVisits/{id} - GET method, shows information about all visits of customer by ID;
* http://localhost:8080/customer/registration - POST method, create new customer;
* http://localhost:8080/customer/updateStatus - PUT method, change status for customer;
* http://localhost:8080/customer/updateInfo - PUT method, change information about certain customer;
* http://localhost:8080/customer/{id} - DELETE method, delete customer by ID;

* http://localhost:8080/admin/registration - POST method, create admin in customer table.