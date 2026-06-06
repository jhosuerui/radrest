FreeRADIUS PostgreSQL Manager API
=================================

License: Apache 2.0 Spring Boot PostgreSQL

A powerful and easy-to-use REST API built with **Spring Boot** designed to manage the PostgreSQL backend database of a **FreeRADIUS** server. This tool simplifies AAA (Authentication, Authorization, and Accounting) administration through intuitive HTTP endpoints.

* * *

## Features
------------

*   **User Management:** Effortlessly create, update, and delete RADIUS users (radcheck/radreply).
*   **NAS Management:** Register and manage Network Access Servers (nas table).
*   **Group Management:** Organize users into groups and manage group profiles (radusergroup/radgroupcheck).
*   **Navigation & Access Restrictions:** Assign bandwidth limits, time-based restrictions, and data caps.
*   **Network Accounting:** Real-time tracking and querying of active sessions and historical device connections (radacct).
*   **Environment-Based Configuration:** Multi-profile system (Development/Production) powered securely by environment variables.

## Prerequisites
-----------------

*   Java 21 or higher
*   PostgreSQL Database (with the FreeRADIUS schema applied)
*   FreeRADIUS Server (configured to use the PostgreSQL backend)
*   Maven 3.8+

## Configuration & Environment Setup
------------------------------------

The application utilizes Spring Boot profiles (`application-dev.properties` and `application-prod.properties`) combined with a `.env` file to manage sensitive credentials securely without hardcoding them.

### Setup your Environment Variables

Before running the application, you must create your local environment file using the provided skeleton template.

1. **Copy the template file:**
   In the root directory of the project, duplicate `.env-skell`[cite: 2] to create your active `.env` file:
```bash
   cp .env-skell .env

2. **Configure your credentials:
    Open the newly created .env file and fill in your local database credentials and server settings:

    DEV_DB_URL=jdbc:postgresql://localhost:5432/radius_dev
    DEV_DB_USER=your_local_user
    DEV_DB_PASS=your_local_password
```

⚠️ CRITICAL SECURITY NOTE: The .env file contains sensitive local credentials and is automatically excluded via .gitignore. Never commit this file to version control.

\## Getting Started
-------------------

### 1\. Clone the repository

    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name
    
### 2\. Prepare Environment

Follow the instructions in the Configuration & Environment Setup section above to create your .env file.

### 3\. Build the project

    mvn clean package
    

### 4\. Run the application

    java -jar target/freeradius-manager-api-1.0.0.jar
    

\## Running in Production Mode:
--------------------------------

To run the application in Production, switch the active profile. In production environments, credentials should be injected directly via system environment variables rather than a .env file.

java -jar target/radrest-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

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
