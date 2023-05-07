# Beauty_project application

This project is a simple version of the beauty saloon service. It provides an opportunity to collect information about 
procedures, cosmetic products, employees. Also, this project provides an opportunity to collect information about 
customers with giving discount for them in the future.  

## Database

Application use PostgreSQL database. For start the application you need Postgres server (jdbc:postgresql://localhost:
5432/beauty_db) with created database 'beauty_db'. Database contains eight tables.

* Table _admin_table_ - contains authorization information and roles for administrators;
* Table _customer_table_ - contains personal information, authorization information and roles of beauty saloon customers;
* Table _employee_table_ - contains information about beauty saloon staff;
* Table _procedures_ - contains information about the procedures carried out in the beauty saloon;
* Table _cosmetic_products_ - contains information about the cosmetic products used and sold in the beauty saloon;
* Table _l_proced_prod_ - link table between procedures and cosmetic_products;
* Table _status_table_ - contains information about discounts valid in the beauty saloon;
* Table _visit_table_ - contains information about visit of customer to the beauty saloon.

## Available endpoints for all users


## Available endpoints for customer


## Available endpoints for admin