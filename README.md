# Notification System README

## Introduction

This project is a notification application developed as part of a challenge. Its goal is to create a system that manages the sending of messages to users through different notification channels (SMS, Email, Push Notification) based on specific categories (Sports, Finance, Movies). The application is built using Spring Boot and Java 17, following domain-driven design principles and best development practices.

## Project Structure

### Main Packages

- **applog**
    - **application**: Contains business logic and use cases for application logging.
        - `AppLogUseCases.java`: Use cases related to application logging.
        - `Notification.java`: Data model for notifications.
    - **domain**: Defines domain models used in application logging.
        - `AppLog.java`: Entity for application logs.
        - `NotificationDelivery.java`: Entity managing notification delivery.
    - **infrastructure**: Configurations and infrastructure implementations.
        - **config**: Configuration for handling logs using RabbitMQ.
            - `AppLogRabbitConfig.java`: Configuration class for RabbitMQ.
        - **persistence**: Repositories for persistent data storage.
            - `AppLogRepository.java`: CRUD operations for application logs.

- **common**
    - Contains common classes and configurations for the entire application.
        - `CommonConfig.java`: General application configuration.
        - `RestSecurityConfig.java`: Security configuration for the REST API.
        - `UseCase.java`: Base class for use cases.

- **delivery**
    - Module for managing notification delivery.
    - **application**: Use cases related to notification delivery.
        - `BaseDeliveryUseCases.java`: Base class for delivery use cases.
        - `DeliveryUseCases.java`: Specific use cases for delivery.
        - `DeliveryProducer.java`: Message producer for delivery.
    - **domain**: Models and entities for delivery.
        - `Delivery.java`: Entity representing a delivery.
        - `Notifier.java`: Interface for notification delivery mechanisms.
    - **infrastructure**: Delivery-related configurations and implementations.
        - **config**: Configuration classes for delivery mechanisms.
        - **httpclient**: Implementations for different notification channels.
            - `EmailNotifier.java`: Class for email notifications.
            - `PushNotifier.java`: Class for push notifications.
            - `SmsNotifier.java`: Class for SMS notifications.

- **notification**
    - Main module for managing notifications.
    - **application**: Use cases for notifications.
        - `NotificationUseCases.java`: Specific use cases for managing notifications.
    - **domain**: Domain models related to notifications.
        - `Notification.java`: Entity for notifications.
        - `NotificationCategory.java`: Enum for notification categories.
        - `NotificationChannel.java`: Enum for notification channels.
    - **infrastructure**: Configuration and persistence for notifications.
        - **config**: Configuration classes for notifications.
        - **persistence**: Repositories for notification data.
            - `NotificationMongoRepository.java`: MongoDB repository for notifications.

- **users**
    - Module for user management.
    - **application**: Logic for user operations.
        - `UserApplicationService.java`: Service for user-related operations.
    - **domain**: Domain models for users.
        - `User.java`: Entity representing a user.
    - **infrastructure**: Web layer for user management.
        - **web**: REST controller for user-related endpoints.
            - `UserRestController.java`: Controller for managing user requests.

## Features

- **Message Categories:** Supports three message categories: Sports, Finance, and Movies.
- **Notification Channels:** Includes SMS, Email, and Push Notification, each handled by its own class.
- **Error Handling:** Implements validations and effectively manages exceptions.
- **Scalability:** Designed to be scalable and adaptable to future requirements. The modules are independent and can be separated into microservices, allowing for enhanced flexibility and scalability.

## Installation

1. Clone this repository.
2. Set up the Maven dependencies.
3. Run the application with `./mvnw spring-boot:run`.

## Contributions

Contributions are welcome. Please open an issue or submit a pull request if you would like to collaborate.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
