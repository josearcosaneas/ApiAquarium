# Aquarium 


## Introduction

Api paths:
- /fish     GET | DELETE | PUT Get,  Put or Delete all fishes.
- /tank     GET | DELETE | PUT Get , Put or Delete all tanks.
- /tank/{id}    GET Get tank with id value
- /tank/{id_tank}/fish/{id_fish}    GET Get fish with id id_fish in tank with id_tank 
- /fish/{id}    GET Get fish with id value

## Run


```shell
> unzip aquarium-1.0.zip 
> cd aquarium-1.0/bin/
> ./aquarium
....
Apr 04, 2017 10:21:30 PM org.glassfish.jersey.server.ApplicationHandler initialize
INFO: Initiating Jersey application, version Jersey: 2.7 2014-03-12 18:11:31...
Apr 04, 2017 10:21:30 PM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:8080]
Apr 04, 2017 10:21:30 PM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Press enter to stop the server...
```


## Frameworks and libraries selected:


- **Gradle**: Can automate the building, testing, publishing, deployment
- **JUnit**: Framework to write repeatable tests.
- **JSONAssert**: Creation of JSON tests.
- **Hibernate**: Idiomatic persistence for Java and relational databases.
- **HSQLDB**:  Fast multithreaded and transactional database engine with in-memory and disk-based table.For the sake of fast execution of tests we’ll use in-memory database, Production version of the application should change the mode to file or even switch to a different DB. HSQLDB supports embedded and server modes.
- **Grizzly**: Helps to build scalable and robust servers using NIO 
- **Hamcrest**: Library of matcher objects.
- **Jersey**: Development of RESTful Web Services in Java.

