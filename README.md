# Overview


## Kafka and Docker Compose
To simulate the distributed environment, we have incorporated Kafka and a Kafka UI interface, both running through Docker Compose. 


## First Microservice [artemis-producer]


## Second Microservice [camel-kafka-connect source]


## Third Microservice [camel debezium connector]


## How to Use

To try the complete flow and test everything locally, you will need Docker. 

After that, you will need to bring up all the images using docker-compose up. 

Once this is done, you will need to set up a connection to a SQL Server database. In this case, I used Azure, and you can follow a simple guide here: https://www.youtube.com/watch?v=kMCNTLnna04&ab_channel=SQLServer101. 

Once you have db set up and the images are up using Docker Compose, you will also have access to Kafka UI, which was chosen for monitoring various topics, messages, and other details accessible in this case via localhost:8080. 

I used SpringToolSuite as my IDE and Java 11 with Lombok and Maven.

Links
https://medium.com/@andreacavallo

## Autore
Andrea Cavallo