
# Shopping server implementation

> A back-end server implementation for shopping with SOA.
---

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Testing](#testing)
- [Documentation](#documentation)

---

## Features
It is a sample rest API implementation with the features below;
- Mysql
- H2 DB used for unit tests
- Docker is used for easy setup
- CRUD operations
- Service-Oriented Architecture
- JPA
- Maven checkstyle Plugin
- Postman
- SpringBoot
- Data import via CSV
- Sample study database for products

---

## Installation

- Create a project from the version control tab.

---

### Clone

- Clone this repo to your local machine using `https://github.com/moezguer/swagger-codegen-shopping-service.git`

---

### Setup

From the terminal of the IDE, to have the docker containers to be installed, 
first, run the command
`cd shop server`
then
`docker-compose up`

Now the app is running. Open your browser and call the address below:
`http://localhost:8080`
With the help of the getAllProducts request, the sample products database can be accessed.

---

**Note:** All the functions can be used via Postman collection in the project folder

---

### Closing the docker images

From the terminal of the IDE, to have the docker containers to be installed, run the command
`docker-compose down`

---

## Testing
The controller method can be studied via Postman. Please refer to the postman collection and env. variable JSON in the project.

Unit tests are also included in the project folder for testing and functional flow details.

---

## Documentation
API documentation has been done via Postman collection. Please refer to the collection given in the project folder.

---

## Verdict

1) Using the Swagger Codegen might seem a big advantage due to the nature of design first and implement later, unfortunately, generated code is restricting the project structure tremendously. 

2) The business layer and service layer cannot be separated via the YAML file used in the design therefore, the numerous classes are meant to be created from scratch.

3) Auto-generated code comes in to project as a "great dependency" which cause multiple setting and manual checks in case of any change in the design YAML.

4) Project Lombok is a great tool for simplifying the classes but unfortunately not used by Swagger Codegen. All the setters and getters are written explicitly. For the aforementioned generated classes, using Lombok will disrupt the integrity of the code since the getters and setters will not be explicit.

5) Although not implemented yet (will be done later), Swagger Inspector might be a good alternative for the documentation. Since the YAML files could be easily transferred between Swagger and Postman, Postman collections can be easily created via YAML files that are created with the help of annotations.

