# E-Commerce API

A comprehensive RESTful API for an e-commerce platform.

## Features

- Product catalog management
- Shopping cart
- Order processing
- User authentication
- Payment integration (stripe/paypal)
- Inventory management
- Product reviews and ratings

## API Endpoints

### Products
- `GET /api/products` - Get all products (with pagination, filters)
- `GET /api/products/{id}` - Get product details
- `POST /api/products` - Create product (admin)
- `PUT /api/products/{id}` - Update product (admin)
- `DELETE /api/products/{id}` - Delete product (admin)

### Cart
- `GET /api/cart` - Get user's cart
- `POST /api/cart/items` - Add item to cart
- `PUT /api/cart/items/{id}` - Update cart item
- `DELETE /api/cart/items/{id}` - Remove item from cart
- `DELETE /api/cart` - Clear cart

### Orders
- `POST /api/orders` - Create order from cart
- `GET /api/orders` - Get user's orders
- `GET /api/orders/{id}` - Get order details
- `PUT /api/orders/{id}/status` - Update order status (admin)

### Payments
- `POST /api/payments/process` - Process payment
- `GET /api/payments/{id}` - Get payment status

## Data Models

### Product
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "stock": 50,
  "category": "Electronics",
  "images": ["url1", "url2"]
}
```

### Cart Item
```json
{
  "productId": 1,
  "quantity": 2,
  "price": 999.99
}
```

### Order
```json
{
  "id": 1,
  "items": [...],
  "total": 1999.98,
  "status": "PENDING",
  "shippingAddress": "...",
  "createdAt": "2024-01-17T10:00:00"
}
```
