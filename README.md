# ComputerShop

FRONT-END: https://github.com/mmpodkanski/computer-shop-front

AWS: NOT_WORKING

Simple ecommerce shop, whereby you are able to buy some computer components.

This project uses:

- **CQRS**, **Facade** design patterns
- **JPA Projections**,
- Stripe framework to handle payments

Now I'm working on:

- possibilty to collect invoice pdf file(OpenPDF framework)

## Screenshots

## Technologies and tools

- Java 11
- Spring
- Maven
- Hibernate
- Stripe
- JUnit
- MySQL
- IntelliJ IDEA

## Endpoints (examples)

| Value     |        Endpoint         |       Access |
| :-------- | :---------------------: | -----------: | --- | --- |
| GET       |     /products?page/     | not required |
| GET       |   /products?category/   | not required |
| GET       |     /products?code/     | not required |
| GET       |     /products/:id/      | not required |
| POST      |       /products/        |        admin |
| PATCH     | /products/:id?increase/ |        admin |
| PATCH     | /products/:id?increase/ |        admin |
| DELETE    |     /products/:id/      |        admin |
|           |                         |              |     |     |
| GET, POST |         /cart/          |     customer |
| UPDATE    |       /cart/:id/        |     customer |
| DELETE    |          /cart          |     customer |
|           |                         |              |     |     |
| GET, POST |        /orders/         |     customer |
| GET       |      /orders/:id/       |     customer |
| POST      |  /orders/checkout=:id/  |     customer |
| DELETE    |      /orders/:id/       |     customer |
|           |                         |              |     |     |
| GET, POST |       /customer/        |     customer |
| PUT       |    /customer?details    |     customer |
| PUT       |     /customer?login     |     customer |
| PUT       |   /customer?register    |     customer |

## Installation

1. Clone the Project using link https://github.com/VivekPandey09032002/computer-shop-back or Download the zip
2. Open project in IntelliJ:

- File->New->Project from Version Control then past clone link
- File->Open then find and open downloaded zip

3. Run java application

OR

You can run application with maven wrapper:

```
- mvnw clean install
- mvnw spring-boot:run
```

## Contact

Created by [@mmpodkanski](https://github.com/mmpodkanski/)
