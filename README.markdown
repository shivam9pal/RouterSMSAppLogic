# RouteFinder: Bus Route Navigation Backend

RouteFinder is a cloud-native backend system deployed on AWS that powers bus route navigation for public transit users. Hosted within a secure AWS Virtual Private Cloud (VPC), the system processes user requests to find the shortest bus routes using a Breadth-First Search (BFS) algorithm, leveraging an Ubuntu EC2 instance, MySQL RDS, and AWS Lambda APIs. Designed for scalability and security, RouteFinder integrates a graph-based transit network model with real-time database interactions to deliver accurate route instructions.

## Features

- **AWS Deployment**: Runs on an Ubuntu EC2 instance within a VPC, with MySQL RDS for persistent storage and AWS Lambda APIs for secure communication.
- **Route-Finding Logic**: Implements BFS to compute the shortest bus route (direct or single-transfer) between two stops, modeled as an undirected graph.
- **Database Integration**: Fetches bus routes and user requests from MySQL RDS, storing replies for client delivery.
- **Real-Time Processing**: Polls unprocessed user requests every 500ms, computes routes, and stores replies in the database.
- **VPC Security**: Restricts public access to Lambda APIs, with EC2 and RDS isolated within the VPC for enhanced security.

## Architecture

### AWS Infrastructure

- **Virtual Private Cloud (VPC)**: Hosts all components securely, with private subnets for EC2 and RDS and public access limited to Lambda APIs.
- **EC2 Instance**: Runs an Ubuntu server executing the Java backend (`Main.java`), responsible for route computation and database interactions.
- **MySQL RDS**: Stores:
  - `busroutes`: Bus routes with `route_code` (bus name) and `route_details` (stops, e.g., "StopA-StopB-StopC").
  - `messages`: User requests with `id`, `source`, `destination`, `sender_mob`, and `is_processed` flag.
  - `reply`: Route responses with `sender_mob` ,`reply` , is_replied flag.
- **AWS Lambda APIs**:
  - **API 1**: Saves user requests (source, destination, mobile number) to the `messages` table.
  - **API 2**: Retrieves replies from the `reply` table for client delivery.
  - **API 3**: Marks replies as processed to avoid duplicate processing.

### Server Logic

- **Graph Model**: The `BusNetworkMap` class represents the transit network as an undirected graph (`Map<String, List<Neighbour>>`), where stops are nodes and edges are bus connections.
- **Route Finding**: The `RouteFinder` class uses BFS to find the shortest path between source and destination stops, supporting direct routes or single transfers. It formats results as human-readable instructions (e.g., "Take bus B1 from StopA to StopB").
- **Database Operations**: The `DBManager` class handles JDBC interactions with MySQL RDS, fetching routes/messages and inserting replies.
- **Message Processing**: The `Main` class runs a polling loop to fetch unprocessed messages, compute routes, and store replies, ensuring real-time operation.

### Workflow

1. User requests (source, destination, mobile number) are saved to the `messages` table via API 1.
2. The EC2 instance polls the `messages` table, uses BFS to compute the route, and inserts the result into the `reply` table.
3. A client (e.g., Android app) fetches the reply via API 2, delivers it, and marks it as processed via API 3.
4. All components communicate securely within the VPC, with Lambda APIs as the only public interface.

## Getting Started

### Prerequisites

- AWS account with VPC, EC2, RDS, and Lambda configured.
- Java 21 for the backend.
- MySQL database with `busroutes`, `messages`, and `reply` tables.
- JDBC driver for MySQL connectivity.

### Setup

1. **Configure AWS**:
   - Set up a VPC with private subnets for EC2 and RDS.
   - Launch an Ubuntu EC2 instance and deploy the Java application.
   - Create a MySQL RDS instance and initialize the database schema.
   - Deploy three Lambda APIs with API Gateway for public access.
2. **Database Configuration**:
   - Update `DBManager.java` with RDS endpoint, username, and password.
   - Populate the `busroutes` table with bus route data.
3. **Run the Backend**:
   - Compile and run `Main.java` on the EC2 instance.
   - Ensure the application polls the `messages` table and processes requests.
4. **Test APIs**:
   - Use tools like Postman to test the Lambda APIs for saving requests, fetching replies, and marking replies as processed.

### Database Schema

- **busroutes**: `route_code (VARCHAR)`, `route_details (VARCHAR, stops separated by '-')`.
- **messages**: `id (INT, PK)`, `source (VARCHAR)`, `destination (VARCHAR)`, `sender_mob (VARCHAR)`, `is_processed (BOOLEAN)`.
- **reply**: `sender_mob (VARCHAR)`, `reply (VARCHAR) and is_replied(BOOLEAN) `.

## Code Structure

- `Models.java`: Defines data models (`BusRoute`, `Message`, `RouteNode`, `RouteSegment`).
- `Main.java`: Entry point, runs the polling loop.
- `BusNetworkMap.java`: Builds and queries the transit graph.
- `RouteFinder.java`: Implements BFS for route finding.
- `DBManager.java`: Handles database operations.
- `MessageProcessor.java`: Processes user requests.
- `ReplySender.java`: Inserts replies and marks messages as processed.
- `MapInitializer.java`: Initializes the bus network from database data.

## 

## 