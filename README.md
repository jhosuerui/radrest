FreeRADIUS PostgreSQL Manager API
=================================

License: Apache 2.0 Spring Boot PostgreSQL

A powerful and easy-to-use REST API built with **Spring Boot** designed to manage the PostgreSQL backend database of a **FreeRADIUS** server. This tool simplifies AAA (Authentication, Authorization, and Accounting) administration through intuitive HTTP endpoints.

* * *

\## Features
------------

*   **User Management:** Effortlessly create, update, and delete RADIUS users (radcheck/radreply).
*   **NAS Management:** Register and manage Network Access Servers (nas table).
*   **Group Management:** Organize users into groups and manage group profiles (radusergroup/radgroupcheck).
*   **Navigation & Access Restrictions:** Assign bandwidth limits, time-based restrictions, and data caps.
*   **Network Accounting:** Real-time tracking and querying of active sessions and historical device connections (radacct).

\## Prerequisites
-----------------

*   Java 17 or higher
*   PostgreSQL Database (with the FreeRADIUS schema applied)
*   FreeRADIUS Server (configured to use the PostgreSQL backend)
*   Maven 3.8+

\## Configuration
-----------------

Configure your database connection in the `src/main/resources/application.properties` file:

    spring.datasource.url=jdbc:postgresql://your-server-ip:5432/radius
    spring.datasource.username=your_radius_db_user
    spring.datasource.password=your_radius_db_password
    spring.jpa.hibernate.ddl-auto=validate
    

\## Getting Started
-------------------

### 1\. Clone the repository

    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name
    

### 2\. Build the project

    mvn clean package
    

### 3\. Run the application

    java -jar target/freeradius-manager-api-1.0.0.jar
    

\## API Endpoint Quick Reference
--------------------------------

Method   

Endpoint

Description

`POST`

/api/v1/users

Create a new RADIUS user

`POST`

/api/v1/nas

Register a new Network Access Server

`POST`

/api/v1/groups/assign

Assign a user to a specific profile/group

`GET`

/api/v1/accounting/active

Retrieve all currently connected devices

\## License
-----------

This project is licensed under the **Apache License 2.0**. See the [LICENSE](LICENSE) file for details.
