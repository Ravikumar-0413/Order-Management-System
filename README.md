# 🛒 Order Management System (Spring Boot)

## 📌 Project Overview

This project is a backend Order Management System built using **Java and Spring Boot**.
It allows customers to place orders with multiple products, validates stock availability, calculates total amount, and updates inventory accordingly.

---

## 🚀 Features

* Customer Management (Create, Read, Update, Delete)
* Product Management (Add, Update, Delete, View)
* Place Order with Multiple Products
* Stock Validation before Order Placement
* Automatic Stock Reduction after Order
* Total Amount Calculation
* Meaningful Error Handling (Stock, Product Not Found, Duplicate Email)

---

## 🧱 Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL
* Maven

---

## 🗂️ Database Design

### 1. Customer

* id
* name
* email (unique)

### 2. Product

* id
* name
* price
* stockQuantity

### 3. Orders

* id
* customer_id
* orderDate
* totalAmount
* status

### 4. OrderItems

* id
* order_id
* product_id
* quantity
* price
* subtotal

---

## 🔗 Relationships

* One Customer → Many Orders
* One Order → Many OrderItems
* One OrderItem → One Product

---

## ⚙️ Order Flow Logic

1. Fetch Customer
2. Fetch Products
3. Validate Stock Availability
4. Calculate Subtotal for each item
5. Calculate Total Order Amount
6. Reduce Product Stock
7. Save Order and OrderItems

---

## 🌐 API Endpoints

### 🧑 Customer APIs

* `POST /customers` → Create customer
* `GET /customers` → Get all customers
* `GET /customers/{id}` → Get customer by ID
* `PUT /customers/{id}` → Update customer
* `DELETE /customers/{id}` → Delete customer

---

### 📦 Product APIs

* `POST /products` → Add product
* `GET /products` → Get all products
* `GET /products/{id}` → Get product
* `PUT /products/{id}` → Update product
* `DELETE /products/{id}` → Delete product

---

### 🔥 Order APIs

* `POST /orders/place?customerId=1` → Place order
* `GET /orders` → Get all orders
* `GET /orders/{id}` → Get order by ID
* `DELETE /orders/{id}` → Delete order

---

## 🧪 Sample Request (Place Order)

```
POST /orders/place?customerId=1
```

```json
[
  {
    "product": { "id": 1 },
    "quantity": 1
  },
  {
    "product": { "id": 2 },
    "quantity": 3
  }
]
```

---

## ⚠️ Error Handling

* Invalid quantity → 400 Bad Request
* Insufficient stock → 400 Bad Request
* Product not found → 404 Not Found
* Duplicate email → 400 Bad Request

---

## 🧠 Key Design Decisions

* Used **OrderItem** to handle multiple products per order
* Stored **price in OrderItem** to maintain price history
* Used **lazy loading + JSON ignore** to prevent serialization issues
* Enforced **unique email constraint** for customers

---

## 🔧 Setup Instructions

1. Clone the repository

```
git clone <your-repo-url>
```

2. Configure MySQL in `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/oms_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

3. Run the application

4. Test APIs using Postman

---

## 🚧 Limitations

* No transaction management
* No DTO layer (direct entity exposure)
* No authentication/authorization

---

## 🚀 Future Improvements

* Add Transaction Management
* Implement DTO Layer
* Add Order Cancellation (restore stock)
* Add Authentication (JWT)
* Improve Error Handling using Global Exception Handler

---

## 👨‍💻 Author

Developed as a backend learning project using Spring Boot.
