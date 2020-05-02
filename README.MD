This is a sample Spring Boot application which is integrated with application insights. 

Set the Environment variable APPLICATION_INSIGHTS_KEY with instrumentation key of application insights. 

/snat URL takes in two Query Parameters: url and count. The API sends HTTP GET method to the uri "count" number of times. 

The Controller also has an endpoint /mysql which taken a query param "count" and sends that number of insert queries in a User table. 

To run the application, you would need to set the following environment variables as well: 

CONNECTION_STRING=jdbc:mysql://<dbserver>.azure.com:3306/db_example?serverTimezone=UTC 
DB_PASSWORD=YourPassword 
