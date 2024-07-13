### **Order Creation Process**

The creation order service involves the following steps:

    Customer Validation: Verify the customer ID by referencing the Customer microservice.
    Product Purchase: Invoke the purchase products API in the Product microservice.
    Order Persistence: Save the order details.
    Order Lines Persistence: Save the order line items.
    Payment Processing: Initiate the payment process with the Payment microservice.
    Order Confirmation: Send the order confirmation to our notification service, which utilizes Kafka.
