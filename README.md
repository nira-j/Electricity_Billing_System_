# Electricity_Billing_System_
 
A simple REST API-based electricity billing management system that will calculate the <br>
units consumed within specified time duration and accordingly calculate the amount of money<br>
to be paid for those units.<br>
There are two users to this system. The meter reading person and the house owner. The meter<br>
reading person reads the meter and updates the system.<br>
The house owner should be able to get the monthly billing details from the system.<br>

## Used Tools
1) MySQL Server(8.0.17)
2) Eclipse IDE
3) SqlYog as a client of MySQL server
4) JDBC(version 8.0.13) to establish connection between database and application
5) Servlet for backend server side code
6) Tomcat server(version 9.0)
7) Postman as a client to send request to server

## Required .jar file
1) gson-2.8.2.jar
2) mysql-connector-java-8.0.13.jar
3) servlet-api.jar

## Database Table Required <br>
Two tables are required <br>
one for user data entry and another for electricity bill record entry.<br> 

## Implemented endpoints for API <br>
method-----------url--------------------------------------------Description<br>
POST------------/meter/add-user-------------------------To add a new user<br>
request: {"username": "xyz", "address": "banglore"}<br><br>
method-----------url--------------------------------------------Description<br>
POST------------/meter/add-readings---------------------To add energy consumption data<br>
request: {"userId": 1, "meterReading": 112, "date": "30/12/2020"}<br><br>
method-----------url--------------------------------------------Description<br>
GET-------------/meter/get-bill/{userId}/{month}---------To get generated bill according to month name<br>
In above get request we will pass month name like:-january or febuary or...<br><br>
method-----------url--------------------------------------------Description<br>
DELETE----------/meter/remove-user/{userid}-----------To remove a user from database <br>




