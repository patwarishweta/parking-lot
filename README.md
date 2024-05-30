# Headless Parking System using Spring Boot

#### This is a headless Parking System implemented using Spring Boot. The system allows users to view available parking slots, book parking slots for 2 hours, and provides the admin with the ability to view the full status of the parking station. The system is designed to handle requests in bulk and incorporates rate limiting to ensure fair usage and prevent server overload.

## Features
#### * View available parking slots
#### * Book a parking slot for 2 hours
#### * Expire the booking after 2 hours
#### * Admin can view the full status of the parking station
#### * Rate limiting to handle bulk requests
#### * Asynchronous booking to improve performance

## Assumptions
#### * A single server will handle the load.
#### * In-memory data storage is used (no external database).

## Technologies Used
#### * Spring Boot
#### * Spring Web
#### * Spring Scheduling
#### * Bucket4j (Rate Limiting)
#### * Java Concurrency

## Getting Started
#### Prerequisites
##### * Java 11 or higher
##### * Maven

#### Installation
##### 1. Clone the repository: git clone https://github.com/patwarishweta/parking-lot.git
##### 2. Build the application: mvn clean install
##### 3. Run the application: mvn spring-boot:run

## Usage
### API Endpoints
#### 1. Get Available Slots: GET /api/parking/available-slots - Returns a list of available parking slots that are not currently booked.
#### 2. Book Slots: POST /api/parking/book-slots - Books the specified parking slots if they are available. Returns a success message with the booked slots or an error message if the booking fails.
#### 3. Get All Slots: GET /api/parking/all-slots - Returns a list of all parking slots with their status (booked or available).
