# E-commerce-App

![e-commerce](https://github.com/fatemehSalem/E-commerce-App/assets/42536170/3699cf3e-3d20-44a3-86c8-c6ce399ea94d)

**Customer:** This represents the initiating party who places an order through the e-commerce platform.

**Product:** This likely represents a product catalog or database that stores information about the products available for purchase.

**Payment:** This represents a payment processing service that handles customer payments.

**Message Broker - Kafka :** This is an intermediary service that facilitates communication between different microservices. It acts as a central hub for messages, allowing services to send and receive messages asynchronously (without waiting for a response).

**API Gateway:** This is a single entry point for all API requests coming from the outside world (e.g., the customer interface). It routes incoming requests to the appropriate microservices based on their functionality.

**Order:** This represents an order processing microservice that handles tasks related to order creation, management, and fulfillment.

**Discovery Server:** This service helps microservices discover each other within the network.  When a microservice needs to interact with another, it queries the discovery server to find the location (address) of the target service.

**Config Server:** This service provides a centralized location to manage configuration settings for all the microservices. This ensures consistent configurations across all services and simplifies deployments.

The diagram outlines the following data flow:

    Customer Initiates Order: The customer interacts with the e-commerce platform to purchase products. This likely involves selecting products, providing shipping information, and making a payment.

    Order to API Gateway: The e-commerce platform sends an order request to the API Gateway.

    API Gateway Routes Request: The API Gateway routes the request to the appropriate microservice, likely the "Order" service in this case.

    Order Service: The Order service processes the order details, which might involve:

Communicating with the Product service to retrieve product information.

Interacting with the Payment service to verify and process the payment.

    Order Confirmation: Once the order is processed, the Order service sends confirmation messages to the customer.

