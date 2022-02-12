# Let Sleeping Cats Lie
---

## Description
---
Let Sleeping Cats Lie is a web application used to test the CRUD operations
provided by the custom ORM. The two models provided make use of the custom
annotations that tell the ORM how to create the database tables.

## Technologies Used

* PostgreSQL JDBC Driver - Version 42.3.1
* JUnit - Version 5.8.2
* Mockito - Version 4.2.0
* Mockito JUnit - Version 4.3.1
* Log4j - Version 2.14.1
* Java Servlet API - 4.0.1
* GSON - Version 2.8.6

## Features
---
Completed feature list:
* Servlets to receive and respond to requests from a client
* Controllers to process requests, call the appropriate service method and set status code of the response
* Service layer classes to call appropriate CRUD operation method provided by the custom ORM

## Getting Started
---
First clone this repository's main branch using:
```
git clone https://github.com/redn6gx/simpson_davis_let_sleeping_cats_lie.git
```
Then navigate to the project folder and run maven install:
```
mvn install
```
You should now have a generated war file called LetSleepingCatsLie that you can
use to deploy on an application server.

## Usage
---
With the application deployed and running, you can now send requests to query the
database. The following use cases demonstrate how to send requests on a server that is
running on a local machine.
* POST and GET http requests to read and manipulate cat data can be sent via this uri:
  * http://localhost:8080/LetSleepingCatsLie/cats/
* To perform PUT and DELETE requests simply append the id to the end of the uri:
  * http://localhost:8080/LetSleepingCatsLie/cats/id
* To perform the same http crud operations on furniture data, replace the cats collection
  with the furniture collection in the path variable as shown below:
* http://localhost:8080/LetSleepingCatsLie/furniture/
* http://localhost:8080/LetSleepingCatsLie/furniture/id

## Contributors
---
* Richard Simpson - https://github.com/RichardSimpson235
* Robert Davis - https://github.com/redn6gx
