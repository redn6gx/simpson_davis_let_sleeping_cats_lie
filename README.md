# Let Sleeping Cats Lie

Revature Project 1 Java Servlet web application with custom ORM (called Catnap)

## Servlets 
This layer simply processes the url data to call the corresponding method in the controller layer.

Hosts two endpoints, ie two Servlets:
- CatServlet
  - This servlet manages the endpoint to retrieve and modify cat data. You can perform GET, POST, PUT, and DELETE operations the cat data.
- FurnitureServlet
  - This servlet manages the endpoint to retrieve and modify furniture data. You can perform GET, POST, PUT, and DELETE operations the furniture data.

## Controller Layer
This layer extracts relevant data from the request object and calls the corresponding service to perform the operation.

The following are the two controllers:
- CatController
  - Interacts with CatService to retrieve and modify cat data
- FurnitureController
  - Interacts with FurnitureService to retrieve and modify furniture data

## Service Layer
This layer uses Catnap to obtain data on the corresponding entity.

The following are the two services:
- CatService
  - This class uses Catnap to perform manipulations on the cat data in the database.
- FurnitureService
  - This class uses Catnap to perform manipulations on the furniture data in the database.