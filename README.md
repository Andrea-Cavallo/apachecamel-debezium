# Overview

This project aims to leverage the powerful Apache Camel framework, not only to consume transaction logs in real-time using CDC Debezium but also to capture potential queues coming from Artemis. Once serialized and processed, everything is sent to a specific topic using Kafka.

## Techologies Used:

-Apache Camel
-Spring Boot
-Apache Kafka
-Artemis
-SqlServer
-Docker
-Maven


## First Microservice [artemis-producer]
This is a simple microservice designed to simulate Artemis queues that will later be consumed.

## Second Microservice [camel debezium artemis connector]
This microservice contains all the core business logic.

## OPTIONAL: Third Microservice [camel-kafka-connect-source/connector] 
is to utilize Kafka Connect Source, targeting a SQL Server database, to continuously poll and detect any changes. This is useful for comparing this connector with the Debezium connector, and to perform tests, stress tests for performance, and other evaluations.

## How to Use

To try the complete flow and test everything locally, you will need Docker. 

After that, you will need to bring up all the images using docker-compose up. 

Once this is done, you will need to set up a connection to a SQL Server database. In this case, I used Azure, and you can follow a simple guide here: https://www.youtube.com/watch?v=kMCNTLnna04&ab_channel=SQLServer101. 

Once you have db set up and the images are up using Docker Compose, you will also have access to Kafka UI, which was chosen for monitoring various topics, messages, and other details accessible in this case via localhost:8080.
On port 8161, you can also view the Artemis UI. Log in using the same credentials you'll find in the Docker Compose file.

To fully test the project with Apache Camel, you'll need to:

1)Populate the SQL Server Database: You can download another project I created https://github.com/Andrea-Cavallo/cdc-debeziumembedded-sqlserver-mongodb-kafka/tree/master/crud_sqlserver_ms that exposes a REST API for this purpose, or you can manually set up your database and populate your schema. 

2)Start the artemis-producer Microservice: Use the REST call v1/produce to generate some queues. Once this step is completed, you'll have everything you need to test the Apache Camel microservice and will be ready to capture all changes.

I used SpringToolSuite as my IDE and Java 11 with Lombok and Maven.

## Note 
- The broker.xml was only used by me to override the Artemis configuration.
- what I used for Debezium in Apache Camel is the embedded mode.

## Links
https://medium.com/@andreacavallo
https://camel.apache.org/components/3.21.x/debezium-sqlserver-component.html


## What is Missing? 
What needs to be added is data validation and topic validation. Indeed, once the data is consumed from multiple sources before being sent to a specific topic, it's a good idea to introduce the concept of a schema registry and validate, perhaps by changing the serialization to Avro instead of String as done in this example for simplicity. 

Another thing that's missing is secure credentials management, which can be done, for example, through:

- Spring Cloud Config Server:
Pros: Centralizes configurations for multiple microservices. Supports configuration encryption.
Cons: Introduces another component into your system. Might be overkill if you have just a simple application.

- HashiCorp Vault:
Pros: Extremely secure and designed specifically for secret management. Can store, manage, and control access to tokens, passwords, certificates, encryption keys, and other secrets.
Cons: Requires separate understanding and maintenance. Can be complex to configure correctly.

- Environment Variables:
Pros: Simple to implement and doesn’t require additional dependencies.
Cons: Less secure compared to other options if the hosting system is compromised. Difficult to manage in environments with many variables.

- Jasypt:
Pros: Provides simple, integrated encryption for Spring properties.
Cons: While Jasypt makes it harder for someone to read your credentials, it isn’t invulnerable. Determined attackers could still decrypt your credentials if they gain access to the encryption key.


## Autore
Andrea Cavallo
