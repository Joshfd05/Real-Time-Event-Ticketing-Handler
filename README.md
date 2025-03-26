
# Real-Time Event Ticketing System (Angular & Springboot)

This project implements the Real-Time Event Ticketing System using advanced producer-consumer patterns. The system is designed in such a way that it can handle the concurrency of ticket releases by vendors and ticket purchases by customers while ensuring efficient resource management and concurrency control. The system uses multi-threading to simulate real-time ticket availability, ensuring the scalability and maintainability of the architecture.

The course work will deepen your understanding of Object-Oriented Programming (OOP), multi-threading, and concurrent systems in line with industry practices. Implementation will be done using Spring Boot for the backend and Angular for the frontend to ensure a full-stack development approach.




## Tech Stack

**Frontnd:** Angular 18 (Standalone components)

**Backend:** Spring Boot (Java 17)

**Database:** H2

**Build Tools:** Maven

**Dependencies:** 

    Spring Boot Starter Web
    Spring Boot Starter Data JPA
    Spring Boot DevTools
    H2 Database



## Setup Instructions

    JDK 17+: Required for running the backend with Spring Boot.
    Node.js: Required for building and running the Angular frontend.
    Maven: Required for managing backend dependencies and running the Spring Boot application.
    Database: H2 (in-memory database) is configured for development.

- Setup

    Download the ZIP file containing the project.
        
        Click the "Download ZIP" button to get the project.

    Extract the ZIP file:

        unzip ticketing-system.zip
        cd ticketing-system

    Build the project:

        mvn clean install

    Run the backend application:
    
        mvn spring-boot:run

    Go to the weblink:

        http://localhost:8080/
## Features

- **Multithreaded Ticket Management**: Implement producers (vendors) and consumers (customers) working concurrently.

- **Concurrent Control**: Synchronizes vendors and customers to avoid race conditions and ensure the maximum utilization of resources.

- **Logging and Error Handling**: Log important system events; handle errors gracefully.

- **System Configuration and Control**: APIs to start, stop, and monitor the system at runtime.