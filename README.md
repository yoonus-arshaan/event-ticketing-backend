# Event Ticketing System

## Introduction
The **Event Ticketing System** is a full-stack application that allows vendors to manage event tickets and customers to purchase them in real-time. The system includes a **frontend** built with **Angular**, a **backend** using **Spring Boot** with **PostgresSQL**, and a **CLI** for simulating customer and vendor interactions.

---

## Table of Contents

- [Tools Used](#tools-used)
- [Setup Instructions](#setup-instructions)
  - [Frontend Setup](#frontend-setup)
  - [Backend Setup](#backend-setup)
  - [CLI Setup](#cli-setup)
- [Additional Details](#additional-details)
- [Contributing](#contributing)
- [License](#license)

---

## Tools Used

- **Angular**
- **Spring Boot**
- **Java**
- **PostgresSQL**
- **Jackson** for JSON processing
- **SLF4j** for logging

---

## Setup Instructions

### Project Setup

For detailed setup instructions for the project, refer to the [project README]().


### CLI Setup

The **CLI** simulates real-time interactions between customers and vendors.

#### Prerequisites

- **Java 11+** installed.
- Backend service must be running.

#### Setup Instructions

1. Clone the project repository:
   ```bash
   
Navigate to the CLI class and run the simulation:
bash
Copy code
java -cp target/event-ticketing-backend.jar com.example.CLI
Usage
The CLI presents a menu with the following options:

plaintext
Copy code
1) Start simulation of ticket purchases
2) Print current configuration settings
3) Edit configuration settings
4) Print customer and vendor details
0) Quit
Start simulation: Simulate ticket transactions between customers and vendors.
Print settings: View current configuration (ticket pool size, release rate, etc.).
Edit settings: Modify system configurations and save them.
Print details: Display information about simulated customers and vendors.
Ensure the backend is running before using the CLI.

Additional Details
Jackson handles JSON serialization in the backend.
SLF4j is used for logging throughout the system.
For further assistance, contact yoonusarshaan@gmail.com.

Contributing
To contribute, fork the repository which contains both frontend and backend, clone it, create a new branch for your changes, commit them, and submit a pull request.

License
This project is licensed under the MIT License.
